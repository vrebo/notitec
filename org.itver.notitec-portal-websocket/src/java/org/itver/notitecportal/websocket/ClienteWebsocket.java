/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itver.notitecportal.websocket;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import org.itver.notitecentidadbd.entidad.Noticia;

/**
 *
 * @author vrebo
 */
@ClientEndpoint
public class ClienteWebsocket {

    private Noticia noticia;

    public ClienteWebsocket(Noticia noticia) {
        this.noticia = noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    @OnOpen
    public void onOpen(Session session) {
        try {
            JsonProvider provider = JsonProvider.provider();
            JsonObject addMessage = provider.createObjectBuilder()
                    .add("titulo", noticia.getTitulo())
                    .add("contenido", noticia.getContenido())
                    .add("autor", noticia.getAutor())
                    .build();
            session.getBasicRemote().sendText(addMessage.toString());
        } catch (IOException ex) {
            Logger.getLogger(ClienteWebsocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Mensaje enviado");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
    }
}
