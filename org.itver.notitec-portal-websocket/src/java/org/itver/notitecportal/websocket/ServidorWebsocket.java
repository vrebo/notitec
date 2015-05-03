package org.itver.notitecportal.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.itver.notitecentidadbd.entidad.Noticia;

/**
 *
 * @author vrebo
 */
@ServerEndpoint("/despachador")
public class ServidorWebsocket {

    private static final Set<Session> sessions = new HashSet<>();

    @OnOpen
    public void open(Session session) {
        System.out.println("Nueva sesión: " + session);
        sessions.add(session);
    }

    @OnClose
    public void close(Session session) {
        System.out.println("Sesión cerrada: " + session);
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable error) {
        System.out.println("Error: " + error);
        Logger.getLogger(ServidorWebsocket.class.getName()).log(Level.SEVERE, null, error);
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        System.out.println("Nueva noticia: " + message);
        sendToAllConnectedSessions(message);
    }

    /**
     * Realiza un broadcasting a las sesiones del conjunto de sesiones
     * enviandoles el mensaje en formato JSON.
     *
     * @param message el mensaje en formato JSON
     */

    private void sendToAllConnectedSessions(String message) {
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
        sendToSession(session, message.toString());
    }

    private void sendToSession(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(ServidorWebsocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
