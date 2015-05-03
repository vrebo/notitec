package servicio;

import java.util.ArrayList;


public class Cliente {
	//private String nombreUsuario;
	private String password;
	//private String claveAutenticacion;
	private int noticiasSinLeer;
	private int mensajesSinLeer;
	
	private ArrayList<String> conversaciones;
	
	public Cliente(String nombre, String pass){
		//nombreUsuario=nombre;
		password=pass;
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
		if(mensajesSinLeer>0){
			notificacion.append(mensajesSinLeer).append(" mensajes de ").append(conversaciones.size());
			notificacion.append("conversacion");
			if(conversaciones.size()>1){
				notificacion.append("es");
			}
		}
		if(noticiasSinLeer>0){
			if(mensajesSinLeer>0)
				notificacion.append(" y ");
			notificacion.append(noticiasSinLeer).append(" noticias sin leer");
		} 
		if(mensajesSinLeer>0){
			notificacion.append("\n");
			notificacion.append(conversaciones.get(0));
			for (int i = 1; i < conversaciones.size(); i++) {
				notificacion.append(", ").append(conversaciones.get(i));
			}
		}
		conversaciones.clear();
		noticiasSinLeer= mensajesSinLeer=0;
		return notificacion.toString();
	}
	public boolean autentica(String pass){
		return password.equals(pass);
	}
}