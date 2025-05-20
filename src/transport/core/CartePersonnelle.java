package transport.core;

import java.io.Serializable;
import java.time.*;

public class CartePersonnelle extends TitreTransport implements Serializable{
    private static final long serialVersionUID = 1L;
    private Personne personne;
    private TypeCarte type;

    public boolean estValide(LocalDate date) throws TitreNonValideException{
        boolean valide=(Period.between(super.dateAchat.toLocalDate(), date).getYears()<1);
        if(valide==false){
            throw new TitreNonValideException();
        }
        return valide;
    }

    public CartePersonnelle (Personne personne) throws ReductionImpossibleException{
        super();
        this.personne = personne;
        super.prix=5000;
        setType();
        if(type==TypeCarte.NOREDUCTION){
            throw new ReductionImpossibleException();
        }
    }
    
    public TypeCarte getType() {
        return type;
    }

    public void setType(){
        int age=Period.between(personne.getDateNaissance(),LocalDate.now()).getYears();
        type=TypeCarte.NOREDUCTION;
        if(age > 65) {
            type=TypeCarte.SENIOR;
            super.prix=(super.prix)*(25.0/100);
        }
        if(age < 25){
            type=TypeCarte.JUNIOR;
            super.prix=(super.prix)*(30.0/100);
        }
        if(personne.getClass().toString()=="Employe"){
            type=TypeCarte.PARTENAIRE;
            super.prix=super.prix*(40.0/100);
        }
        if(personne.getHandicap()==true){
            type=TypeCarte.SOLIDARITE;
            super.prix=super.prix*(50.0/100);
        }
    }
}