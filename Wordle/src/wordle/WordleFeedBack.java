package wordle;


public class WordleFeedBack {
    
    private static final int wordLength=5;
    
    public static final String ANSI_RESET="\u001B[0m";
    public static final String ANSI_BLACK="\u001B[30m";
    public static final String ANSI_RED="\u001B[31m";
    public static final String ANSI_GREEN="\u001B[32m";
    public static final String ANSI_YELLOW="\u001B[33m";
    
    
    //MÉTODO AUXILIAR PARA APLCIAR COLOR
   /* private static String applyColor(String letter, String color){
        
    }*/
    
    public static String feedBackString (String guess, String secretWord){
        
        StringBuilder fGuess=new StringBuilder(guess);
        StringBuilder fSecret=new StringBuilder(secretWord);
        StringBuilder resultado=new StringBuilder();
        
        for (int i=0; i<wordLength; i++)
        //SI LA PALABRA COINCIDE EN SITIO Y GRAMMAR = VERDE
        if(fGuess.substring(i, i+1).equals(fSecret.substring(i, i+1))){ 
           //Saco por pantalla la letra del color correcto
           System.out.print(ANSI_GREEN+guess.substring(i, i+1)+ANSI_RESET);
           //aplico solo color
           
        } 
       //Los índices de substring son de i hasta el siguiente (i+1) mínimo límite
       //SI LA PALABRA ESTÁ, PERO NO ES SU SITIO = AMARILLO
        else if(fSecret.indexOf(fGuess.substring(i, i+1))> -1){
           //indexOf porque en esta condición sé que no está en su lugar
           //En esta condición quiero saber si aparece simplemente
           System.out.print(ANSI_YELLOW+guess.substring(i, i+1)+ANSI_RESET);
        }
       // LA PALABRA NO ESTÁ = BLACK
        else {
           System.out.print(ANSI_RED+fGuess.subSequence(i, i+1));
        }
        return guess;
    }


    
}
