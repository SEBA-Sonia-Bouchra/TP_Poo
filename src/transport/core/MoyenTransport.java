package transport.core;

import java.io.Serializable;

public class MoyenTransport implements Suspendable, Serializable{
    private static final long serialVersionUID = 1L;
    private String identifiant;
    private boolean suspendu;

    public MoyenTransport(String identifiant){
        this.identifiant = identifiant;
    }

    public String getIdentifiant() { return identifiant; }    
    public String toString() { return ("Moyen de transport" + identifiant) ;}
  
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