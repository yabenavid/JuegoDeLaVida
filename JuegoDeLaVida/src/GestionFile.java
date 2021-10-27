import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

public class GestionFile {
	
	private ArrayList<Integer> lista;

	public GestionFile() {
		super();
		this.lista = new ArrayList<Integer>();
	}
	
	public GestionFile(ArrayList<Integer> lista) {
		super();
		this.lista = lista;
	}
	
	public ArrayList<Integer> readNums() throws IOException{
		FileReader reader = null;
		BufferedReader br = null;
		
		try {
			System.out.println("Entering try statement\nLeyendo archivo txt");
            reader = new FileReader("Patron.txt");
            br = new BufferedReader(reader);
            String cd;
            int n,na;
            String[] tmp = new String[8];
            
            while ((cd = br.readLine()) != null) {
            	//System.out.println("entrando while");
            	
                cd = cd.replaceAll("\\s","-");
                //System.out.println(cd);
                tmp = cd.split("-");
                for(int i=0; i<tmp.length; i++) {
                	lista.add(Integer.parseInt(tmp[i]));
                }
                
            }
            
		}catch (FileNotFoundException e) {
	        System.err.println("Caught FileNotFoundException " + e.getMessage());
	
	    } catch (IOException e) {
	        System.err.println("Caught IOException " + e.getMessage());
	
	    } finally {
	        if (br != null) {
	            System.out.println("Closing FileReader");
	            reader.close();
	            br.close();
	            return lista;
	
	        } else {
	            System.out.println("hubo un error al leer");
	            return null;
	        }
	    }
	}
	
	public ArrayList<Integer> getLista(){
		return lista;
	}
	

}
