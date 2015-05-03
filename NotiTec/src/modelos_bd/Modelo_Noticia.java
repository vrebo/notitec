package modelos_bd;

import java.util.List;

import modelos_bd.Modelo_Usuario.Atributos_Usuarios;
import static modelos_bd.Modelo_Usuario.TABLE_NAME_USUARIO;

public interface Modelo_Noticia {

	public List<String> getNoticias(int inicio, int fin);

	public List<String> getNoticias(int fin);

	public void agregaNoticia(String autor, String titulo, String fecha,
			String cuerpo);

	// Nombre de la tabla.
	public static final String TABLE_NAME_NOTICIA = "Noticia";

	// Atributos de la tabla Noticias
	public static class Atributos_Noticias {

		public static final String ID_NOTICIA = "idNoticia";
		public static final String TITULO_NOTICIA = "Titulo_Noticia";
		public static final String CONTENIDO_NOTICIA = "Contenido_Noticia";
		public static final String AUTOR_NOTICIA = "Autor_Noticia";
		public static final String FECHA_NOTICIA = "Fecha_Noticia";
	}

	public static final String FORMATO_FECHA_CONSULTA = "DATE_FORMAT("
			+ Atributos_Noticias.FECHA_NOTICIA + ", '%d %m %Y %h:%i %p') AS fecha";

	// public static final String RETORNAR_FULL_NOTICIAS =
	// "select "+Atributos_Noticias.ID_NOTICIA+", "+Atributos_Noticias.AUTOR_NOTICIA+" , "+Atributos_Noticias.TITULO_NOTICIA+" , "+Atributos_Noticias.FECHA_NOTICIA+" , "+Atributos_Noticias.CONTENIDO_NOTICIA
	// +
	// " from "+TABLE_NAME_NOTICIA+" order by "+Atributos_Noticias.FECHA_NOTICIA+" desc ";

	public static final String RETORNAR_FULL_NOTICIAS = "select "
			+ Atributos_Noticias.ID_NOTICIA + ", "
			+ Modelo_Usuario.NOMBRE_COMPLETO + " , "
			+ Atributos_Noticias.TITULO_NOTICIA + " , "
			+ FORMATO_FECHA_CONSULTA + " , "
			+ Atributos_Noticias.CONTENIDO_NOTICIA + " from "
			+ TABLE_NAME_NOTICIA + " , " + TABLE_NAME_USUARIO + " WHERE "
			+ TABLE_NAME_USUARIO + "." + Atributos_Usuarios.ID_USUARIO + " = "
			+ TABLE_NAME_NOTICIA + "." + Atributos_Noticias.AUTOR_NOTICIA
			+ " order by " + Atributos_Noticias.FECHA_NOTICIA + " desc ";

	public static final String INSERTAR_NOTICIA = "insert into "
			+ TABLE_NAME_NOTICIA + " ( " + Atributos_Noticias.AUTOR_NOTICIA
			+ " , " + Atributos_Noticias.TITULO_NOTICIA + " , "
			+ Atributos_Noticias.FECHA_NOTICIA + " , "
			+ Atributos_Noticias.CONTENIDO_NOTICIA + ") values ( ";

	public static final String[] ARRAY_ATRIBUTOS_NOTICIA = {
			Atributos_Noticias.ID_NOTICIA, Atributos_Noticias.TITULO_NOTICIA,
			Atributos_Noticias.CONTENIDO_NOTICIA,
			Atributos_Noticias.AUTOR_NOTICIA, Atributos_Noticias.FECHA_NOTICIA };
}
