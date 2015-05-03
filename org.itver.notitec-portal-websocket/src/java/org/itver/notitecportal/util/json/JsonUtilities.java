/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itver.notitecportal.util.json;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.itver.notitecentidadbd.entidad.Noticia;

/**
 *
 * @author vrebo
 */
public class JsonUtilities {

    public Noticia jsonToNoticia(String json) {
        JsonObject jsonMessage;
        try (JsonReader reader = Json.createReader(new StringReader(json))) {
            jsonMessage = reader.readObject();
        }
        return jsonToNoticia(jsonMessage);
    }

    public Noticia jsonToNoticia(JsonObject jsonMessage) {
        Noticia noticia = new Noticia();
        noticia.setTitulo(jsonMessage.getString("titulo"));
        noticia.setContenido(jsonMessage.getString("contenido"));
        noticia.setAutor(jsonMessage.getString("autor"));
        return noticia;
    }
}
