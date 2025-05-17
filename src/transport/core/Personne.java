package transport.core;
import java.time.*;

// Classe abstraite Personne
public abstract class Personne {
    protected  String nom;
    protected  String prenom;
    protected  LocalDate dateNaissance;
    protected boolean handicap;

    public Personne(String nom, String prenom, LocalDate dateNaissance, boolean handicap){
        this.nom= nom;
        this.prenom= prenom;
        this.dateNaissance=dateNaissance;
        this.handicap=handicap;
    }

    public String getNom(){
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public boolean getHandicap() {
        return handicap;
    }
    
    @Override
    public String toString() {
        return prenom + " " + nom;
    }
  

}

