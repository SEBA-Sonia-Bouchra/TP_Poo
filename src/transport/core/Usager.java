package transport.core;

import java.time.*;
// Classe Usager

public class Usager extends Personne {
    private static final long serialVersionUID = 1L;
    
    public Usager(String nom, String prenom, LocalDate dateNaissance, boolean handicap){
        super(nom, prenom, dateNaissance, handicap);
    }

  
}