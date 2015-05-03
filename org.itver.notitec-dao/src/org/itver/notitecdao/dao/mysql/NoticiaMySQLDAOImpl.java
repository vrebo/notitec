/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itver.notitecdao.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itver.notitecdao.dao.GenericDAO;
import org.itver.notitecdao.dao.MySQLDatabaseHelper;
import org.itver.notitecentidadbd.entidad.Noticia;

/**
 * Clase que realiza operaciones CRUD (Create, Read, Update, Delete) en un
 * servidor MySQL con instancias de la clase <code>Noticia</code>.
 *
 * @author vrebo
 */
public class NoticiaMySQLDAOImpl implements GenericDAO<Long, Noticia> {

    public static final String NOMBRE_TABLA;
    public static final String ID_NOTICIA;
    public static final String TITULO;
    public static final String CONTENIDO;
    public static final String FECHA_PUBLICACION;
    public static final String ID_AUTOR;

    static {
        NOMBRE_TABLA = "noticia";
        ID_NOTICIA = "id_noticia";
        TITULO = "titulo";
        CONTENIDO = "contenido";
        FECHA_PUBLICACION = "fecha_publicacion";
        ID_AUTOR = "id_autor";
    }

    @Override
    public boolean persiste(Noticia entidad) {
        String sql = "INSERT INTO noticia (titulo, contenido, fecha_publicacion, id_autor) VALUES (?, ?, ?, ?);";
        Connection con = MySQLDatabaseHelper.creaConexion();
        PreparedStatement ps;

        if (con == null) {
            return false;
        }

        try {

            ps = con.prepareStatement(sql);
            if (ps == null) {
                return false;
            }

            ps.setString(1, entidad.getTitulo());
            ps.setString(2, entidad.getContenido());
            ps.setTimestamp(3, new java.sql.Timestamp(entidad.getFechaPublicacion()
                    .getTime()));
            ps.setString(4, entidad.getAutor());
            ps.execute();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(NoticiaMySQLDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public Noticia buscaPorId(Long id) {
        String query = "SELECT * FROM noticia WHERE noticia.id_noticia = ?;";
        Noticia noticia = null;
        Connection con = MySQLDatabaseHelper.creaConexion();
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = con.prepareStatement(query);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            String titulo, contenido, autor;
            Timestamp fechaPublicacion;

            rs.next();
            titulo = rs.getString(TITULO);
            contenido = rs.getString(CONTENIDO);
            fechaPublicacion = rs.getTimestamp(FECHA_PUBLICACION);
            autor = rs.getString(ID_AUTOR);
            noticia = new Noticia(id, titulo, contenido, fechaPublicacion, autor);

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(NoticiaMySQLDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return noticia;
    }

    @Override
    public List<Noticia> buscaTodos() {
        String query = "SELECT * FROM noticia ORDER BY fecha_publicacion DESC;";
        List<Noticia> listaNoticias = null;
        Connection con = MySQLDatabaseHelper.creaConexion();
        Statement stm;
        ResultSet rs;

        if (con == null) {
            return null;
        }

        try {
            stm = con.createStatement();
            rs = stm.executeQuery(query);

            listaNoticias = new ArrayList<>();
            long id;
            String titulo, contenido, autor;
            Timestamp fechaPublicacion;
            while (rs.next()) {
                id = rs.getLong(ID_NOTICIA);
                titulo = rs.getString(TITULO);
                contenido = rs.getString(CONTENIDO);
                fechaPublicacion = rs.getTimestamp(FECHA_PUBLICACION);
                autor = rs.getString(ID_AUTOR);
                listaNoticias.add(
                        new Noticia(id, titulo, contenido, fechaPublicacion, autor));
            }
            rs.close();
            stm.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(NoticiaMySQLDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return listaNoticias;
    }

    @Override
    public boolean salva(Noticia entidad) {
        String sql = "UPDATE noticia SET titulo = ?, contenido = ?, id_autor = ?  WHERE id_noticia = ?;";
        Connection con = MySQLDatabaseHelper.creaConexion();
        PreparedStatement ps;

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, entidad.getTitulo());
            ps.setString(2, entidad.getContenido());
            ps.setString(3, entidad.getAutor());
            ps.setLong(4, entidad.getId());
            ps.execute();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(NoticiaMySQLDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean elimina(Long id) {
        String sql = "DELETE FROM noticia WHERE id_noticia = ?;";
        Connection con = MySQLDatabaseHelper.creaConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.execute();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(NoticiaMySQLDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean elimina(Noticia entidad) {
        String sql = "DELETE FROM noticia WHERE id_noticia = ?;";
        Connection con = MySQLDatabaseHelper.creaConexion();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, entidad.getId());
            ps.execute();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(NoticiaMySQLDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

}
