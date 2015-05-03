package principal;

import java.util.List;

import conector.Conector_SQL;

public class Main {
	
	public static void main(String [] args){
		Conector_SQL c = new Conector_SQL();
//		c.insertarUsuario("E12020801", "Manuel", "Perez", "Garcia","4991");
		
//		c.agregaNoticia("E12020830", "Noticia # 1", "31-12-2014", "La noticia número 1 en la BD");
		c.mostrarUsuarios();
		
		imprimirLista(c.getNoticias(5));
		
		//c.getNoticias();
		//c.getNoticias(1, 5);
	}
	
	public static void imprimirLista(List <String> aux ){
		for (int i = 0; i < aux.size(); i++) {
			System.out.println(aux.get(i));
		}		
	}
}
