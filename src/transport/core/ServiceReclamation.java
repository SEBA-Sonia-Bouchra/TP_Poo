package transport.core;

import java.util.*;

public class ServiceReclamation {
    private  final int SEUIL = 3;
    private  Map<TypeReclamation,TreeSet<Reclamation>> reclamationsParType = new TreeMap<>();
    private  Map<Personne, TreeSet<Reclamation>> reclamationsParPersonne = new HashMap<>();
    // private  Map<Suspendable, TreeSet<Reclamation>> reclamationsParSuspendable = new HashMap<>();
    private TreeSet<Reclamation> TreeReclamation = new TreeSet<>();

    public void soumettre(Reclamation R){
        TreeReclamation.add(R);
        reclamationsParType.put(R.getType(),TreeReclamation);
        reclamationsParPersonne.put(R.getPersonne(),TreeReclamation);

        int cibleNB= 0;
        for (Reclamation o : TreeReclamation){
            if (o.getCible() == R.getCible()){
                cibleNB++;
            }
        }   
        if (cibleNB >= SEUIL){
            R.getCible().suspendre();
        }
    }

    public void afficherReclamations(){
        for(TypeReclamation TR : reclamationsParType.keySet()){
            System.out.println("-----------------------Reclamations de type "+TR);
            for(Reclamation o : TreeReclamation){
                if (TR.equals(o.getType())){
                    System.out.println(o.toString());
                }
            }

        }
    }
    public void afficherReclamations(Personne person){
            for(Reclamation o : TreeReclamation){
                if (person.toString().equals(o.getPersonne().toString())){
                    System.out.println(o.toString());
                }
            }

        
    }
    public void afficherReclamations(Suspendable cible){
            for(Reclamation o : TreeReclamation){
                if (cible.toString().equals(o.getCible().toString()) ){
                    System.out.println(o.toString());
                }
            }

        
    }
    // public void resoudre(Reclamation R){
    //     if (){

    //     }
    // }
    
}