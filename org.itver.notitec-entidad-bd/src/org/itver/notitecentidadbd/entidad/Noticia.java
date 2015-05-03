package org.itver.notitecentidadbd.entidad;

import java.util.Date;



/**
 *
 * @author vrebo
 */
public class Noticia extends Entidad<Long> {

    private String titulo;
    private String contenido;
    private Date fechaPublicacion;
    private String autor;

    public Noticia() {
    }

    public Noticia(Long id, String titulo, String contenido, Date fechaPublicacion, String autor) {
        this(titulo, contenido, fechaPublicacion, autor);
        this.id = id;
    }

    public Noticia(String titulo, String contenido, Date fechaPublicacion, String autor) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaPublicacion = fechaPublicacion;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

}
