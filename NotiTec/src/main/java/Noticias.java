import java.util.List;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class Noticias {
	//public Vector<String> noticias= new Vector<String>();
	
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
        }catch(FileNotFoundException e){
            System.out.println("Diccionario no encontrado\n"+e);
            System.exit(-1);
        }catch(IOException ex){
            System.out.println("Error: " +  ex.toString());
        }
		return noticias;
	}
	public List<String> getNoticias(int n){
		Vector<String> noticias= getNoticias();
		System.out.println("getNoticias(n)");
		return noticias.subList(0, Math.min(noticias.size(), n));
	}
	public List<String> getNoticiasRango(int inicio, int fin){
		if(inicio>fin){
			int aux=inicio;
			inicio=fin;
			fin=aux;
		} 
		Vector<String> noticias= getNoticias();
		System.out.println("getNoticias("+inicio+", "+fin+");");
		return noticias.subList(Math.max(0, inicio-1), Math.min(noticias.size()-1, fin));
	}
	
}
