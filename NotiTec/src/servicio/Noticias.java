package servicio;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import conector.Conector_SQL;

public class Noticias {
	//public static HashMap<String, String> usuarios= new HashMap<String,String>();
	/**
	 *  Guarda que usuario es de acuerdo a la clave. 
	 *  Se le pasa una clave como llave y devuelve el usuario correspondiente, si no hay un usuario con esa clave devuelve null
	 */
	public static HashMap<String, String> autenticaciones= new HashMap<String,String>(); 
	/**
	 * Guarda que clave tiene cada usuario, como llave se le pasa el usuario y devuelve su clave
	 */
	public static HashMap<String, String> claves= new HashMap<String,String>();
	public static HashMap<String, Notificaciones> notificaciones = new HashMap<String, Notificaciones>();
	public static Conector_SQL conector;
	public Noticias(){
		System.out.print("creando instancia de noticias... ");
		if(Conector_SQL.conexion==null){
			conector=new Conector_SQL();
			System.out.println(" conector creado.");
		}
		if(notificaciones.size()==0){
			System.out.print("Cargando lista de usuarios... ");
			List<String> users= conector.mostrarUsuarios();
			String camp[];
			for (int i = 0; i < users.size(); i++) {
				camp=users.get(i).split("%fin%");
				notificaciones.put(camp[0], new Notificaciones());
			}
			System.out.println("Lista de usuarios cargada");
		}
		/*try{
            BufferedReader bf= new BufferedReader(new InputStreamReader(new FileInputStream("Usuarios.txt")));                      
            String items[];
            while(bf.ready()){
            	items= bf.readLine().split(" ");
            	notificaciones.put(items[0], new Notificaciones());
            }
            bf.close();
        }catch(FileNotFoundException e){
            System.out.println("Diccionario no encontrado\n"+e);
            System.exit(-1);
        }catch(IOException ex){
            System.out.println("Error: " +  ex.toString());
        }*/
		//Cliente c=clientes.get("manuel");
		//c.addNotificacion("1 noticia nueva");
		//c.addNotificacion("1 mensaje nuevo");
	}
	private Vector<String> getNoticias(){
		Vector<String> noticias= new Vector<String>();
		try{
			/*FileWriter out;
            out= new FileWriter("output2.txt");  
            out.write("prueba2");
            out.close();*/
            BufferedReader bf= new BufferedReader(new InputStreamReader(new FileInputStream("Noticias.txt")));                      
            StringBuilder noticia=new StringBuilder();
            int c=0, id=1;
            while(bf.ready()){
            	if(c==0){
                	noticia.append(id).append("\n");
                }
            	noticia.append(bf.readLine()).append('\n');
                c++;
                if(c==4){
                	id++;
                	c=0;
                	noticias.add(noticia.toString());
                	noticia.delete(0, noticia.length());
                }
            }
            bf.close();
        }catch(FileNotFoundException e){
            System.out.println("Diccionario no encontrado\n"+e);
            System.exit(-1);
        }catch(IOException ex){
            System.out.println("Error: " +  ex.toString());
        }
		return noticias;
	}
	public List<String> getNoticias(int n){
		//Vector<String> noticias= getNoticias();
		//System.out.println("getNoticias(n)");
		//return noticias.subList(0, Math.min(noticias.size(), n));
		System.out.println("getNoticias(" + n +");");
		List<String> noticias=conector.getNoticias(n);
		for (String string : noticias) {
			System.out.println(string);
		}
		return noticias;
	}
	public List<String> getNoticiasRango(int inicio, int fin){
		/*if(inicio>fin){
			int aux=inicio;
			inicio=fin;
			fin=aux;
		} */
		//Vector<String> noticias= getNoticias();
		System.out.println("getNoticias("+inicio+", "+fin+");");
		//return noticias.subList(Math.max(0, inicio-1), Math.min(noticias.size()-1, fin));
		return conector.getNoticias(inicio, fin);
	}
	private boolean caracterValido(int x){
		if(x>=55296 && x<=57343)return false; //55494,57261,56990,55762, 55462, 57234,55958,55713,2686
		if(x<=41)return false;
		if(x>=65500)return false;
		int ilegales[]=new int[]{ 26381, 40097, 60,62,2686, 12399,'?',28};
		char aux= (char)x;
		if(aux=='<' || aux=='?' || aux=='>' || aux==' ')return false; 
		for (int i = 0; i < ilegales.length; i++) {
			if(x==ilegales[i])return false;
		}
		
		return true;
	}
	private String generaClave(String usuario){
		StringBuilder clave;
		int aux;
		char x;
		do{
			clave= new StringBuilder();
			for(int i=0;i<5;i++){
				aux=(int)(Math.random()*Character.MAX_VALUE); 
				x=(char)aux;
				while( !caracterValido(aux)){  //63404 caracteres permitidos en total
					aux=(int)(Math.random()*Character.MAX_VALUE);
					x=(char)aux;
				}
				clave.append(x);
			}
		}while(autenticaciones.containsKey(clave.toString()));
		String clav= clave.toString();
		autenticaciones.put(clav, usuario);
		claves.put(usuario, clav);
		
		System.out.println("se asigno la clave: " + clav + " al usuario: " + usuario);
		System.out.print("codigo ascii: ");
        for (int i = 0; i < clav.length(); i++) {
            System.out.print((int)clav.charAt(i)+" ");
        } System.out.println();
		return clav;
	}
	public String autenticar(String usuario, String pass){
		/*String aux= usuarios.get(usuario);  //medida de seguridad que se implementara despues
		if(aux!=null){
			if(aux.equals(pass)){
				System.out.println("Ya se habia logeado el usuario");
				return "true";
			}
		}*/
		//Notificaciones c=notificaciones.get(usuario);
		if(conector.comprobarDatos(usuario, pass)){
			//usuarios.put(usuario, pass);
    		String clave=claves.get(usuario);
    		autenticaciones.remove(clave);
    		return generaClave(usuario);
		}
		
		return "false";
	}
	public List<String> getConversaciones(String clave){
		Vector<String> conversaciones = new Vector<String>();
		String usuario= autenticaciones.get(clave);
		if(usuario==null){
			conversaciones.add("Error en la autenticacion");
			return conversaciones.subList(0, 1);
		}
		conversaciones.add("Jesús Manuel\nHola:)");
		conversaciones.add("Manuel Angel\nson las 3:30");
		conversaciones.add("Victor Daniel\nHola mundo");
		conversaciones.add("IvanRomero\nHola nuevo!!");
		conversaciones.add("Anilu del carmen\nHola!!");
		return conversaciones.subList(0, conversaciones.size());
	}
	public List<String> getGrupos(String clave){
		Vector<String> grupos= new Vector<String>();
		String usuario= autenticaciones.get(clave);
		if(usuario==null){
			grupos.add("Error en la autenticacion");
			return grupos.subList(0, 1);
		}
		grupos.add("Simulacion");
		grupos.add("Ingenieria de software");
		grupos.add("Desarrollo sustentable");
		grupos.add("POO");
		
		return grupos.subList(0, grupos.size());
	}
	public List<String> getIntegrantesGrupo(String clave, String grupo){
		Vector<String> integrantes= new Vector<String>();
		String usuario= autenticaciones.get(clave);
		if(usuario==null){
			integrantes.add("Error en la autenticacion");
			return integrantes.subList(0, 1);
		}
		integrantes.add("Chucho");
		integrantes.add("Juan");
		integrantes.add("Pablo");
		integrantes.add("Anacleto");
		
		return integrantes.subList(0, integrantes.size());
		
	}
	public String agregaNoticia(String autor, String titulo, String fecha, String cuerpo){
		System.out.println("llego noticia de: " +  autor + "\n" + titulo +"\n"+fecha + "\n" + cuerpo);
		//conector.agregaNoticia(autor, titulo, fecha, cuerpo);
		conector.agregaNoticia(autor, titulo, fecha, cuerpo);
		try{
			FileWriter out;
	        out= new FileWriter("Noticias.txt",true);  
	        out.write(autor+"\n");
	        out.write(titulo+"\n");
	        out.write(fecha+"\n");
	        out.write(cuerpo+"\n");
	        Collection<Notificaciones> c=notificaciones.values();
	        for (Notificaciones cliente : c) {
				cliente.addNoticiaSinLeer();
			}
	        out.close();
        }catch (IOException ex){
        	return "false";
        }
        return "true";
	}
	public String enviarMensaje(String clave, String destino, String mensaje){
		String emisor= autenticaciones.get(clave);
		if(emisor==null){
			return "Error en la autenticacion";
		}
		Notificaciones receptor=notificaciones.get(destino);
		receptor.addMensajeSinLeer(emisor);
		return "true";
	}
	/**
	 * Devuelve los ultimos n mensajes de la conversacion elegida
	 * @param clave clave de autenticacion, asi sabemos que usuario es
	 * @param conversacion el nombre de la conversacion
	 * @param n numero de mensajes a devolver
	 * @return
	 */
	public List<String> getMensajes(String clave, String conversacion, int n){
		Vector<String> mensajes= new Vector<String>();
		String emisor= autenticaciones.get(clave);
		if(emisor==null){
			mensajes.add("Error en la autenticacion");
			return mensajes.subList(0, mensajes.size());
		}
		mensajes.add("1\nJesus Manuel\nHola");
		mensajes.add("2\nManuel Angel\nQue onda");
		mensajes.add("3\nJesus Manuel\nOye pasame el modelo");
		mensajes.add("4\nManuel Angel\nAh simon, ahorita te lo paso");
		mensajes.add("5\nJesus Manuel\nchido");
		int max=Math.min(mensajes.size(), n+1);
		return mensajes.subList(0, max);
	}
	public List<String> getMensajesRango(String clave, String conversacion, int inicio, int fin){
		Vector<String> mensajes= new Vector<String>();
		String emisor= autenticaciones.get(clave);
		if(emisor==null){
			mensajes.add("Error en la autenticacion");
			return mensajes.subList(0, mensajes.size());
		}
		if(inicio>fin){
			int aux=inicio;
			inicio=fin;
			fin=aux;
		}
		mensajes.add("1\nJesus Manuel\nHola");
		mensajes.add("2\nManuel Angel\nQue onda");
		mensajes.add("3\nJesus Manuel\nOye pasame el modelo");
		mensajes.add("4\nManuel Angel\nAh simon, ahorita te lo paso");
		mensajes.add("5\nJesus Manuel\nchido");
		int max=Math.min(mensajes.size(), fin);
		return mensajes.subList(Math.max(0, inicio), max);
	}
	public String getNotificaciones(String clave){
		String usuario= autenticaciones.get(clave);
		if(usuario==null){
			return "Error en la autenticacion";
		}
		Notificaciones n=notificaciones.get(usuario);
		return n.getNotificaciones();
	}
	public String editarNoticia(int id , String id_Autor, String titulo, String fecha, String cuerpo) {
		if(conector.editarNoticia(id, id_Autor, titulo, fecha, cuerpo))return "true";
		return "false";
	}
}