package transport.core;
public class TitreNonValideException extends Exception{
    public String getMessage(){
        return ("Ticket non valide");
    }
}
