package modelos;

public class Noticia {

	private long id_Noticia;
	private Usuario autor;
	private String titulo;
	private String contenido;
	private String fecha;

	public Noticia(long id, Usuario writer, String title, String date,
			String message) {
		id_Noticia = id;
		autor = writer;
		titulo = title;
		fecha = date;
		contenido = message;
	}

	public Noticia(Usuario writer, String title, String date, String message) {
		this(0, writer, title, date, message);
	}

	public Noticia(String writer, String title, String date, String message) {
		this(new Usuario(writer), title, date, message);
	}

	public Noticia(long id, String writer, String title, String date,
			String message) {
		this(id, new Usuario(writer), title, date, message);
	}

	@Override
	public String toString() {
		return "Noticia{" + "autor=" + autor.getNombreUsuario()
				+ ", titulo_Noticia=" + titulo + ", fecha=" + fecha
				+ ", cuerpo_Mensaje=" + contenido + '}';
	}

	public long getId_Noticia() {
		return id_Noticia;
	}

	public void setId_Noticia(long id_Noticia) {
		this.id_Noticia = id_Noticia;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}