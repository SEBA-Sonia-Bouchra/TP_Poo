package transport.core;
import java.io.Serializable;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

// Classe abstraite Personne
public abstract class Personne  implements Serializable{
    private static final long serialVersionUID = 1L;
    protected  String nom;
    protected  String prenom;
    protected  LocalDate dateNaissance;
    protected boolean handicap;
    protected List<TitreTransport> listTitreTransport = new ArrayList<>();

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
        return (prenom + " " + nom + " " + dateNaissance + " " + handicap);
    }
  
    public void ajouterTitre(TitreTransport titre){
        listTitreTransport.add(titre);
    }

     public List<TitreTransport> getTitreTransport() {
        return listTitreTransport;
    }
}

