package modelos_bd;

public interface Modelo_Usuario {

	public void insertarUsuario(String id, String nombre, String apPaterno, String apMaterno, String clave);

	public void editarUsuario (String id , String nombre , String apPat , String apMat , String telefono , String correo , String clave , String imagen);

	public boolean comprobarDatos(String id, String clave);

	// Nombre de la tabla.
	public static final String TABLE_NAME_USUARIO = "Usuarios";

	// Atributos de la tabla Usuarios
	public static class Atributos_Usuarios {

		public static final String ID_USUARIO = "idUsuario";
		public static final String NOMBRE_USUARIO = "Nombre_Usuario";
		public static final String AP_USUARIO_PATERNO = "ApellidoP_Usuario";
		public static final String AP_USUARIO_MATERNO = "ApellidoM_Usuario";
		public static final String TELEFONO_USUARIO = "Telefono_Usuario";
		public static final String CORREO_USUARIO = "Correo_Usuario";
		public static final String CLAVE = "Clave_Usuario";
		public static final String IMAGEN_PERFIL_USUARIO = "Imagen_Perfil_Usuario";

	}

	public static final String NOMBRE_COMPLETO = " CONCAT("+Atributos_Usuarios.NOMBRE_USUARIO+", ' ', "+Atributos_Usuarios.AP_USUARIO_PATERNO+", ' ',"+Atributos_Usuarios.AP_USUARIO_MATERNO+") ";

	public static String INSERCION_ID_NAME_APPAT_APMAT_LAVE = "INSERT INTO "
			+ TABLE_NAME_USUARIO + " ( " + Atributos_Usuarios.ID_USUARIO + " ,"
			+ Atributos_Usuarios.NOMBRE_USUARIO + " , "
			+ Atributos_Usuarios.AP_USUARIO_PATERNO + " , "
			+ Atributos_Usuarios.AP_USUARIO_MATERNO + " , "
			+ Atributos_Usuarios.CLAVE + " )";

	public static String INSERTAR_USUARIO = "insert into " + TABLE_NAME_USUARIO
			+ " (" + Atributos_Usuarios.ID_USUARIO + ","
			+ Atributos_Usuarios.NOMBRE_USUARIO + ","
			+ Atributos_Usuarios.AP_USUARIO_PATERNO + ","
			+ Atributos_Usuarios.AP_USUARIO_MATERNO + ","
			+ Atributos_Usuarios.TELEFONO_USUARIO + ","
			+ Atributos_Usuarios.CORREO_USUARIO + ","
			+ Atributos_Usuarios.CLAVE + ") VALUES (";

	public static final String ACTUALIZAR_USUARIO = "update "+TABLE_NAME_USUARIO+" set ";
	
	public static final String [] ARRAY_ATRIBUTOS_USUARIO = {	Atributos_Usuarios.ID_USUARIO, Atributos_Usuarios.NOMBRE_USUARIO , Atributos_Usuarios.AP_USUARIO_PATERNO , Atributos_Usuarios.AP_USUARIO_MATERNO , Atributos_Usuarios.TELEFONO_USUARIO ,Atributos_Usuarios.CORREO_USUARIO , Atributos_Usuarios.CLAVE , Atributos_Usuarios.IMAGEN_PERFIL_USUARIO};
	
	public static final String [] ARRAY_ATRIBUTOS_USUARIO_UPDATE = { Atributos_Usuarios.NOMBRE_USUARIO , Atributos_Usuarios.AP_USUARIO_PATERNO , Atributos_Usuarios.AP_USUARIO_MATERNO , Atributos_Usuarios.TELEFONO_USUARIO ,Atributos_Usuarios.CORREO_USUARIO , Atributos_Usuarios.CLAVE , Atributos_Usuarios.IMAGEN_PERFIL_USUARIO};
}
