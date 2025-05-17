package transport.core;

import java.time.*;
// Classe Employé

public class Employe extends Personne {
    private final String matricule;
    private final Fonction fonction;

    public Employe(String nom, String prenom, LocalDate dateNaissance, boolean handicap, String matricule, Fonction fonction){
        super(nom, prenom, dateNaissance, handicap);
        this.matricule=matricule;
        this.fonction = fonction;
    }
    public String getMatricule() { return matricule; }
    public Fonction getFonction() { return fonction; }
    
}