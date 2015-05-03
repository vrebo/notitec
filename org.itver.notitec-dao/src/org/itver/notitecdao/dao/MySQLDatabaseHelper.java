package org.itver.notitecdao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que provee de conexiones con el servidor a las instancias de DAO
 * mediante el método éstatico <code>crearConexion()</code>.
 *
 * @author vrebo
 */
public class MySQLDatabaseHelper {

    /*
     Atributos de clase usados para crear conexiones para los DAO
     */
    private static final String host;
    private static final String puerto;
    private static final String usuario;
    private static final String contraseña;
    private static final String baseDatos;

    static {
        host = "jdbc:mysql://localhost";
        puerto = "3306";
        usuario = "root";
        contraseña = "";
        baseDatos = "notitec";
    }

    /**
     * Crea una instancia de <code>Connection</code> que inicia una conexión con
     * el servidor de la base de datos.
     *
     * @return Una instancia de Connection. En caso de excepción devolverá
     * <code>null</code>.
     */
    public static Connection creaConexion() {
        try {
            String url = host + ":" + puerto + "/" + baseDatos;
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            return DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException ex) {
            System.err.println(ex);
            return null;
        }
    }

    /**
     * No se permite crear instancias de esta clase
     */
    private MySQLDatabaseHelper() {
    }

}
