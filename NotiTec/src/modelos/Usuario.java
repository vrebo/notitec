package modelos;

public class Usuario {

	private String id_Usuario;
	private String clave;
	private String nombreUsuario;
	private String apPat;
	private String apMat;
	private boolean estado_Sesion;

	public Usuario(String id, String name) {
		this(id, name, "", "", "");
	}

	public Usuario(String id, String nombre, String apPat, String apMat,
			String clave) {
		this.id_Usuario = id;
		this.nombreUsuario = nombre;
		this.apPat = apPat;
		this.apMat = apMat;
		this.clave = clave;
	}

	public Usuario(String name) {
		this("", name);
	}

	public String getId_Usuario() {
		return id_Usuario;
	}

	public void setId_Usuario(String id_Usuario) {
		this.id_Usuario = id_Usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getApPat() {
		return apPat;
	}

	public void setApPat(String apPat) {
		this.apPat = apPat;
	}

	public String getApMat() {
		return apMat;
	}

	public void setApMat(String apMat) {
		this.apMat = apMat;
	}

	public boolean isEstado_Sesion() {
		return estado_Sesion;
	}

	public void setEstado_Sesion(boolean estado_Sesion) {
		this.estado_Sesion = estado_Sesion;
	}
}
