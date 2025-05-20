package transport.core;

import java.io.Serializable;
import java.time.LocalDate;

public class Reclamation implements Comparable<Reclamation>, Serializable {
    private static final long serialVersionUID = 1L;
    private static int compteur = 1; 
    private int numero;
    private LocalDate date;
    private Personne personne;
    private TypeReclamation type;
    private Suspendable cible;
    private String description;


    public Reclamation(Personne personne, TypeReclamation type, Suspendable cible, String description, LocalDate date) {
        this.personne = personne;   
        this.type = type;
        this.cible = cible;
        this.description = description;
        this.date = date;
        this.numero = compteur;
        compteur++;
    }

    public Suspendable getCible() { return cible; }
    public int getNumero() { return numero; }
    public LocalDate getDate() { return date; }
    public Personne getPersonne() { return personne; }
    public String getDescription() { return description; }
    public TypeReclamation getType() {return type;}
    public String toString(){
        return ("Reclamation #"+numero + "\n"+"Date : "+date+ "| Type : "+ type + "| Cible : "+ cible + "| Description : "+ description + "| Soumise par : "+ personne.toString()); 
    }// à compléter

    
    public int compareTo(Reclamation R){
        if(R.numero == this.numero){
            return -1;
        }else{
            return 1;
        }
    } 
}
