package transport.core;

public class Station implements Suspendable{
    private static final long serialVersionUID = 1L;
    private String nom;
    private boolean suspendu;
    public Station(String nom) {
        this.nom = nom;
        suspendu = false;
    }

    public String getNom() { return nom;  }

    @Override
    public String toString() { return nom ;}
   
    public void suspendre() {suspendu=true;}
    public void reactiver() {suspendu=false;}
    public boolean estSuspendu() {return suspendu;}
    public String getEtat(){
        if (this.estSuspendu()){
            return "suspendu";
        } else {
            return  "actif";
        }
    }
    
}
