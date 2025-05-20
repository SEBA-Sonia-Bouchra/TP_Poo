package transport.core;
import java.io.Serializable;
import java.time.*;

public class Ticket extends TitreTransport implements Serializable{
    private static final long serialVersionUID = 1L;

    public Ticket(){
        super();
        super.prix=50;
    }

    public boolean estValide(LocalDate date) throws TitreNonValideException{
        boolean valide=date.isEqual(super.dateAchat.toLocalDate());
        if(valide==false){
            throw new TitreNonValideException();
        }
        return valide;
    }
}
