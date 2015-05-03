/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;
import org.example.model.Device;

/**
 *
 * @author vrebo
 */
public class DeviceSessionHandler {

    private int deviceId = 0;
    private final Set<Session> sessions = new HashSet<>();
    private final Set<Device> devices = new HashSet<>();

    /**
     * Agrega la sesión nueva al conjunto de sesiones y le envía a esta sesión
     * todos los dispositivos contenidos en el conjunto de dispositivos.
     *
     * @param session Sesión a agregar al conjunto de sesiones.
     */
    public void addSession(Session session) {
        sessions.add(session);        
        for (Device device : devices) {
            JsonObject addMessage = createAddMessage(device);
            sendToSession(session, addMessage);
        }
    }

    /**
     * Elimina la sesión del conjunto de sesiones.
     *
     * @param session Sesión a eliminar
     */
    public void removeSession(Session session) {
        sessions.remove(session);
    }

    /**
     *
     * @return La lista de dispositivos contenidos hasta el momento
     */
    public List getDevices() {
        return new ArrayList<>(devices);
    }

    /**
     * Agrega el dispositivo al conjunto de dispositivos, crea un objeto JSON de
     * dicho dispositivo y hace un broadcasting de este a todas las sesiones del
     * conjunto de sesiones.
     *
     * @param device
     */
    public void addDevice(Device device) {
        System.out.println("device = " + device);
        device.setId(deviceId);
        System.out.println("dispositivo agregado = " + devices.add(device));
        deviceId++;
        JsonObject addMessage = createAddMessage(device);
        sendToAllConnectedSessions(addMessage);
    }

    /**
     * Elimina la sesión con el <code>id</code> especificado del conjunto de
     * sesiones y posteriormente envia el mensaje a todas las sesiones de los
     * clientes para que eliminen dicho dispositivo.
     *
     * @param id
     */
    public void removeDevice(int id) {
        Device device = getDeviceById(id);
        if (device != null) {
            devices.remove(device);
            JsonProvider provider = JsonProvider.provider();
            JsonObject removeMessage = provider.createObjectBuilder()
                    .add("action", "remove")
                    .add("id", id)
                    .build();
            sendToAllConnectedSessions(removeMessage);
        }
    }

    /**
     * Obtiene el dispositivo con el <code>id</code> especificado y despues
     * togglea su estado (On/Off), posteriormente crea un objeto JSON y los
     * transmite a las sesiones de clientes para que estos togglen también dicho
     * dispositivo.
     *
     * @param id
     */
    public void toggleDevice(int id) {
        JsonProvider provider = JsonProvider.provider();
        Device device = getDeviceById(id);
        if (device != null) {
            if ("On".equals(device.getStatus())) {
                device.setStatus("Off");
            } else {
                device.setStatus("On");
            }
            JsonObject updateDevMessage = provider.createObjectBuilder()
                    .add("action", "toggle")
                    .add("id", device.getId())
                    .add("status", device.getStatus())
                    .build();
            sendToAllConnectedSessions(updateDevMessage);
        }
    }

    /**
     * Obtiene el <code>Device</code> con el <code>id</code> especificado del
     * conjunto de dispositivos.
     *
     * @param id
     * @return
     */
    private Device getDeviceById(int id) {
        for (Device device : devices) {
            if (device.getId() == id) {
                return device;
            }
        }
        return null;
    }

    /**
     * Crea un objeto JSON del argumento <code>Device</code>.
     *
     * @param device
     * @return
     */
    private JsonObject createAddMessage(Device device) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "add")
                .add("id", device.getId())
                .add("name", device.getName())
                .add("type", device.getType())
                .add("status", device.getStatus())
                .add("description", device.getDescription())
                .build();
        return addMessage;
    }

    /**
     * Realiza un broadcasting a las sesiones del conjunto de sesiones
     * enviandoles el objeto JSON de argumento.
     *
     * @param message
     */
    private void sendToAllConnectedSessions(JsonObject message) {
        for (Session session : sessions) {
            sendToSession(session, message);
        }
    }

    /**
     * Envia un objeto JSON a una sesión específica.
     *
     * @param session
     * @param message
     */
    private void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(DeviceSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
