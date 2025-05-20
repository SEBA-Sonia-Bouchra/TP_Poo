package transport.core;
import java.io.Serializable;
import java.time.*;

// Classe abstraite pour les titres de transport
public abstract class TitreTransport implements Serializable{
    private static final long serialVersionUID = 1L;
       
    protected int id;
    protected LocalDateTime dateAchat;
    protected double prix;
    private static int idInc = 1;

    public TitreTransport(){
        id = idInc;
        dateAchat = LocalDateTime.now();
        idInc++;
    }

    public LocalDateTime getDateAchat(){ return dateAchat;}
    public int getId(){ return id;}
    public double getPrix(){ return prix;}
    public abstract boolean estValide(LocalDate date) throws TitreNonValideException;

    public static int getCurrentIdInc() {return idInc;}
    public static void setIdInc(int nextId) {idInc = nextId;}
    
    
}