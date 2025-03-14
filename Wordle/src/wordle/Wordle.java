package wordle;
import java.util.List;
public class Wordle {

    
    public static void main(String[] args) {
        /*En el main no hay lógica de ejecución como tal. Me limito a crear los
        objetos que necesito para el juego.*/
        
       //Creamos un nuevo objeto del fileManager
       WordleFileManager archivo=new WordleFileManager();
       
       //Al objeto que gestiona el archivo le asigno el método de lectura
       //para poder extraer una palabra del archivo .txt
       List<String> palabras=archivo.lecturaFile();
       
       //Creo el array fileWords para pasarle el valor al juego
       String [] fileWords=palabras.toArray(new String[0]);
       
       //Creo un nuevo objeto de juego a partir del array de String
       WordleGame juego =new WordleGame(fileWords);
       
       //aplico la selección aleatoria al array creado desde la lista
        juego.selectRandomWord(fileWords); 
        
        //Inicio el juego
        System.out.println("Bienvenido a Wordlo(marca registrada)");
        juego.start();
    }
    
}
