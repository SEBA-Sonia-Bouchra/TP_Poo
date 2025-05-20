package transport.core;

public class TitreNonTrouverException extends Exception{
    public String getMessage(){
        return ("Identifiant du titre de transport non trouver, ce titre n'existe pas");
    }
}
