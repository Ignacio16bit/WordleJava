package wordle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

//Esta clase es usada en MAIN para obtener las palabras del juego.
public class WordleFileManager {
    //Hay que crear los métodos de lectura del fichero y el propio fichero
    //funcionalidad extra para guardar partidas realizadas por el usuario
    
    
    //ATRIBUTOS
    private File archivo;
    private BufferedReader br;
    private FileReader fr;
    private String linea;
    
    
    //MÉTODOS
    public List<String> lecturaFile(){ //Se cargan PALABRAS desde un fichero
        List<String> palabras=new ArrayList<>(); 
        //Aquí almaceno las palabras del archivo
        archivo= new File("diccionario.txt");
        
        //Bloque de lectura de archivo
        try { 
        fr = new FileReader(archivo);
        br = new BufferedReader(fr);
        //String linea almacenará el contenido de BufferedReader
        while ((linea = br.readLine()) != null) {
            palabras.add(linea); //Añado las palabras al ArrayList palabras
        }
    /*BufferedReader lee las palabras del archivo y las va pasando
    una a una a String linea. "Linea" ira almacenando las mismas
    en el ArrayList palabras.*/
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        //Devuelvo la lista de palabras al main. Desde allí se selecciona
        return palabras;
    }
    
    //Todo lo escrito en consola deberá guardarse en un fichero aparte
        public void saveTries(String intento){
            String fecha=new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());
            try{
                FileWriter fichero=new FileWriter("intentos.txt", true);
                PrintWriter pw=new PrintWriter(fichero);
                pw.println(intento);
                pw.close();
            }
            catch (IOException e){
                System.out.println("Error en fichero de escritura"+e.toString());
            }
    }
    
}
