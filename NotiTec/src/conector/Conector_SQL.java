package conector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelos_bd.Modelo_Noticia;
import modelos_bd.Modelo_Usuario;

public class Conector_SQL implements Modelo_Noticia, Modelo_Usuario {

	private ResultSet res;
	public static Connection conexion;
	private static Statement st;

	public static final String url = "jdbc:mysql://216.218.192.170:3306/"
			+ "epinao_Prueba_Servidor";
	public static final String login = "epinao";
	public static final String password = "c4s4p1n4";

	public static final String separador = "%fin%";

	public Conector_SQL() {
		abrir();
	}
	public void abrir(){
		try {
			System.out.print("Estableciendo conexion...   ");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// URL || Usuario || Password
			conexion = DriverManager.getConnection(url, login, password);
			st = conexion.createStatement();
			System.out.println("conexion establecida");

		} catch (Exception ex) {
			System.out.println("Error en conexion ");
			ex.printStackTrace();
			System.exit(0);
		}
	}
	public void cerrar() {
		try {
			res.close();
			st.close();
			conexion.close();
			System.out.println("Cerrado");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void realizarActualizacion(String actualizacion) throws SQLException {
		System.out.println(actualizacion);
		st.executeUpdate(actualizacion);
	}

	public void realizarConsulta(String consulta) throws SQLException {
		System.out.println(consulta);
		res = st.executeQuery(consulta);
	}

	/* ************** USUARIOS ************* */

	public void insertarUsuario(String id, String nombre, String apPaterno,
			String apMaterno, String clave) {

		String consulta = Modelo_Usuario.INSERCION_ID_NAME_APPAT_APMAT_LAVE
				+ " values ('" + id + "','" + nombre + "','" + apPaterno
				+ "','" + apMaterno + "','" + clave + "')";
		int intentos=0;
		while(intentos<=3){
			try {
				realizarActualizacion(consulta);
				break;
			} catch (SQLException e) {
				System.out.print("Error: " +  e + "\nCerrando... ");
				cerrar();
				System.out.print("volviendo a abrir conexion... ");
				abrir(); 
				System.out.println("Conexion abierta otra vez");
				//e.printStackTrace();
			}
			intentos ++;
		}
	}

	public boolean comprobarDatos(String id, String clave) {
		String consulta = "SELECT count(*) as 'Encontrado' FROM "
				+ TABLE_NAME_USUARIO + " WHERE "
				+ Atributos_Usuarios.ID_USUARIO + " = '" + id
				+ "' AND Clave_Usuario = '" + clave + "';";
		String resultado = "";
		int intentos=0;
		while(intentos<3){
			try {
	
				realizarConsulta(consulta);
	
				res.first();
				resultado = res.getString(1); // id_User
				break;
			} catch (SQLException e) {
				//e.printStackTrace();
				System.out.print("Error: " +  e + "\nCerrando... ");
				cerrar();
				System.out.print("volviendo a abrir conexion... ");
				abrir(); 
				System.out.println("Conexion abierta otra vez");
			}
			intentos++;
		}

		return (resultado.compareTo("1") == 0) ? true : false;
	}

	public void editarUsuario(String id, String nombre, String apPat,
			String apMat, String telefono, String correo, String clave,
			String imagen) {

		String consulta = ACTUALIZAR_USUARIO;
		String[] atributos = ARRAY_ATRIBUTOS_USUARIO_UPDATE;
		String[] valores_Nuevos = { nombre, apPat, apMat, telefono, correo,
				clave, imagen };
		int tam = atributos.length - 1;

		for (int i = 0; i < tam; i++) {
			consulta += atributos[i];
			consulta += " = '" + valores_Nuevos[i] + "', ";
		}

		consulta += atributos[tam];
		consulta += " = '" + valores_Nuevos[tam] + "'";

		consulta += " where " + Atributos_Usuarios.ID_USUARIO + " = '" + id
				+ "'";

		try {
			realizarActualizacion(consulta);
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.print("Error: " +  e + "\nCerrando... ");
			cerrar();
			System.out.print("volviendo a abrir conexion... ");
			abrir(); 
			System.out.println("Conexion abierta otra vez");
		}
	}

	public List<String> mostrarUsuarios() {
		String consulta = "select * from Usuarios";

		List<String> usuarios = new ArrayList<String>();

		String[] datos = new String[5];
		int intentos=0;
		while(intentos<=3){
			try {
				realizarConsulta(consulta);
				while (res.next()) {
					datos[0] = res.getString(1); // ID_Usuario
					datos[1] = res.getString(2); // Nombre
					datos[2] = res.getString(3); // Ap_Pat
					datos[3] = res.getString(4); // Ap_Mat
					datos[4] = res.getString(7); // Clave = Contraseña
	
					String aux = "";
	
					for (int i = 0; i < datos.length; i++) {
						aux += datos[i] + separador;
					}
					usuarios.add(aux);
				}
				break;
			} catch (SQLException e) {
				//e.printStackTrace();
				System.out.print("Error: " +  e + "\nCerrando... ");
				cerrar();
				System.out.print("volviendo a abrir conexion... ");
				abrir(); 
				System.out.println("Conexion abierta otra vez");
				usuarios.clear();
			}
			intentos++;
		}
		return usuarios;
	}

	/* ************** NOTICIAS ************* */

	public List<String> retornarNoticias() throws SQLException {

		List<String> noticias = new ArrayList<String>();
		String[] noticia = new String[4];
		long id = 0;

		while (res.next()) {
			id = res.getLong(1); // id
			noticia[0] = res.getString(2); // Autor
			noticia[1] = res.getString(3); // Titulo
			noticia[2] = res.getString(4); // Fecha
			noticia[3] = res.getString(5); // Contenido

			noticias.add(id + separador + noticia[0] + separador + noticia[1]
					+ separador + noticia[2] + separador + noticia[3]);
		}

		return noticias;
	}

	public List<String> getNoticias(int inicio, int fin) {
		List<String> noticias = new ArrayList<String>();
		String consulta = Modelo_Noticia.RETORNAR_FULL_NOTICIAS;
		consulta += " LIMIT " + inicio + "," + fin + ";";
		int intentos=0;
		while(intentos<=3){
			try {
	
				realizarConsulta(consulta);
				noticias = retornarNoticias();
				break;
			} catch (SQLException e) {
				//e.printStackTrace();
				System.out.print("Error: " +  e + "\nCerrando... ");
				cerrar();
				System.out.print("volviendo a abrir conexion... ");
				abrir(); 
				System.out.println("Conexion abierta otra vez");
			}
			intentos++;
		}
		return noticias;
	}

	public List<String> getNoticias(int fin) {
		List<String> noticias = new ArrayList<String>();

		String consulta = Modelo_Noticia.RETORNAR_FULL_NOTICIAS;
		consulta += "LIMIT " + fin + ";";
		int intentos=0;
		while(intentos<=3){
			try {
	
				realizarConsulta(consulta);
	
				noticias = retornarNoticias();
				break;
			} catch (SQLException e) {
				//e.printStackTrace();
				System.out.print("Error: " +  e + "\nCerrando... ");
				cerrar();
				System.out.print("volviendo a abrir conexion... ");
				abrir(); 
				System.out.println("Conexion abierta otra vez");
			}
			intentos++;
		}

		return noticias;
	}

	public void agregaNoticia(String id_Autor, String titulo, String fecha,
			String cuerpo) {

		String[] formato_Fecha = fecha.split("[-]");
		String dia = formato_Fecha[0];
		String mes = formato_Fecha[1];
		String anio = formato_Fecha[2];

		fecha = anio + "-" + mes + "-" + dia;

		String consulta = Modelo_Noticia.INSERTAR_NOTICIA;
		consulta += "'" + id_Autor + "','" + titulo + "','" + fecha + "','"
				+ cuerpo + "')";
		int intentos=0;
		while(intentos<=3){
			try {
				realizarActualizacion(consulta);
				break;
			} catch (SQLException e) {
				//e.printStackTrace();
				System.out.print("Error: " +  e + "\nCerrando... ");
				cerrar();
				System.out.print("volviendo a abrir conexion... ");
				abrir(); 
				System.out.println("Conexion abierta otra vez");
			}
			intentos++;
		}
	}
public boolean editarNoticia(int id , String id_Autor, String titulo, String fecha, String cuerpo) {
		
		String consulta = "update "+TABLE_NAME_NOTICIA+" set ";
		String [] atributos = ARRAY_ATRIBUTOS_NOTICIA;
		
		String [] formato_Fecha = fecha.split("[-]");
		String dia = formato_Fecha[0];
		String mes = formato_Fecha[1];
		String anio = formato_Fecha[2];
		
		fecha = anio+"-"+mes+"-"+dia;
		
		String [] valores_Nuevos = {titulo , cuerpo , id_Autor,fecha};
		
		int tam = valores_Nuevos.length;
		
		for (int i = 1; i < tam; i++) {
			consulta += atributos[i];
			consulta += " = '"+valores_Nuevos[i-1]+"', ";
		}
		
		consulta += atributos[tam];
		consulta += " = '"+valores_Nuevos[tam-1]+"'";
		
		consulta += " where "+Atributos_Noticias.ID_NOTICIA+" = "+id+"";
		int intentos=0;
		while(intentos<3){
			try {
				realizarActualizacion(consulta);
				break;
			} catch (SQLException e) {
				System.out.print("Error: " +  e + "\nCerrando... ");
				cerrar();
				System.out.print("volviendo a abrir conexion... ");
				abrir(); 
				System.out.println("Conexion abierta otra vez");
			}
			intentos++;
		}
		if(intentos==3) return false;
		return true;
	}


}
