package wordle;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WordleGame {
    
    //ATRIBUTOS
    Scanner scan=new Scanner(System.in);
    private final int maxTries=6; //Los intentos llegan hasta 6
    private final int wordLength=5; //serán siempre 5 letras
    private int remainAttemp;//es un contador que va -- pero no resta de maxtries

    private String [] fileWords; //un array de palabras q se enlazará al fichero
    private String secretWord; //una palabra sacada de fileWords
    
    private ArrayList<String> triesHistory;
    //construyo arrayList por no limitar el tamañó de entrada de un array
    
    
    //MÉTODOS    
    public WordleGame (String [] fileWords){
        this.fileWords=fileWords; //instantcio el fileWords
        this.secretWord=selectRandomWord(fileWords); 
        //la palabra secreta se escoge aleatoriamente de fileWords
        this.remainAttemp = maxTries; //remainattemps valdrá 6 e ira bajando
        this.triesHistory=new ArrayList<>(); 
        //una lista que irá almacenando los intentos
    }
    
    
    public void start(){
        boolean acertar=false;
        String intentoUser; // esto será la entrada del usuario
        WordleFileManager save=new WordleFileManager(); //creo el objeto para almacenar intentos
        
        System.out.println("Ingresa tu intento");
        
        //Inicio una entrada en el fichero de salida
        String fecha=new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());
            try{
                FileWriter fichero=new FileWriter("intentos.txt", true);
                PrintWriter pw=new PrintWriter(fichero);
                pw.println("=====Nueva partida.("+fecha+")=====\n");
                pw.close();
            }//Sólo imprimo en fichero un separador, los intentos se manejan desde otra clase
            catch (IOException e){
                System.out.println("Error en fichero de escritura"+e.toString());
            }
        
        //Mantener un BUCLE  que permita realizar intentos hasta maxTries i<=maxTries
        while(!acertar && remainAttemp!=0){
            System.out.println("Tienes "+remainAttemp+" intentos");
            showTriesHistory(triesHistory); //saco por pantalla los intentos almacenados
            System.out.println(" ");
            intentoUser=getUserInput(scan);
            
            
            //Si la palabra intentada es igual a la secreta seleccionada
            if(intentoUser.equals(secretWord)){
                triesHistory.add(intentoUser);
                System.out.println("acertaste la palabra secreta!!");
                showTriesHistory(triesHistory); //muestro el feedback igual
                acertar=true; //cambio una condición de bucle para salir
            }
            else{ //si no acierta
                remainAttemp--; //se reducen los intentos
                //Almaceno el intento en triesHistory para luego sacarlo por pantalla
                triesHistory.add(intentoUser);
            }
            //Llamo al método que almacene la información que le hemos dado
            save.saveTries(intentoUser);
            
        }
        if (remainAttemp==0){
            System.out.println("Se agotaron los intentos. La palabra era:");
            System.out.println(secretWord);
        }
    }
    
    
    //Creo un método que tras finalizar el juego permita guardar la partida
    // el método no devuelve nada, solo almacena

  
        //método para seleccionar palabra aleatoria
   public String selectRandomWord (String[] words){
       Random random=new Random();//creo un objeto random que seleccione
       return words[random.nextInt(words.length)];
} 
    //Método showTriesHistory para mostrar el historial
    public void showTriesHistory(ArrayList <String> triesHsitory){
        WordleFeedBack fb=new WordleFeedBack();
        
        for (String intento:triesHistory){
            fb.feedBackString(intento, secretWord);
            System.out.println(" ");
        }
    }
    
    
    //Método getUserInput para obtener palabra del usuario
    public String getUserInput(Scanner scan){
        boolean longitud=true;
        String intento;
        String error="Palabra no válida";
        
        while(longitud=true){
            intento=scan.nextLine();
            
            if (intento.length()==wordLength){
                return intento;
            }
            else{
                longitud=false;
                System.out.println("La palabra debe ser de "+wordLength+" letras");
                }
            } return error;
        }
    }

        
    
    
    

