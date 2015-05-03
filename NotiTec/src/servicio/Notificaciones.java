package servicio;

import java.util.ArrayList;


public class Notificaciones {
	//private String nombreUsuario;
	//private String password;
	//private String claveAutenticacion;
	private int noticiasSinLeer;
	private int mensajesSinLeer;
	
	private ArrayList<String> conversaciones;
	
	public Notificaciones(){
		//nombreUsuario=nombre;
		//password=pass;
		//claveAutenticacion=null;
		conversaciones=new ArrayList<String>();
		noticiasSinLeer=mensajesSinLeer=0;
	}
	public void addNoticiaSinLeer(){
		noticiasSinLeer++;
	}
	public void addMensajeSinLeer(String conversacion){
		if(conversaciones.contains(conversacion)){
			mensajesSinLeer++;
		}else 
			conversaciones.add(conversacion);
	}
	public String getNotificaciones(){
		StringBuilder notificacion= new StringBuilder();
		/*if(mensajesSinLeer>0){
			notificacion.append(mensajesSinLeer).append(" mensajes de ").append(conversaciones.size());
			notificacion.append("conversacion");
			if(conversaciones.size()>1){
				notificacion.append("es");
			}
		}
		if(noticiasSinLeer>0){
			if(mensajesSinLeer>0)
				notificacion.append(" y ");
			notificacion.append(noticiasSinLeer).append(" noticia"); 
			if(noticiasSinLeer>1)notificacion.append("s");
		notificacion.append(" sin leer");
		}*/
		notificacion.append(mensajesSinLeer).append(",").append(conversaciones.size());
		notificacion.append(",").append(noticiasSinLeer);
		if(mensajesSinLeer>0){
			notificacion.append("\n");
			notificacion.append(conversaciones.get(0));
			for (int i = 1; i < conversaciones.size(); i++) {
				notificacion.append("%fin%").append(conversaciones.get(i));
			}
		}
		conversaciones.clear();
		noticiasSinLeer= mensajesSinLeer=0;
		return notificacion.toString();
	}
	/*public boolean autentica(String pass){
		return password.equals(pass);
	}*/
}