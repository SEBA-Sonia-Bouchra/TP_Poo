package transport.core;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class GuichetStation implements Serializable{
    private static final long serialVersionUID = 1L;
    private static GuichetStation instance;
    List<Personne> listPersonnes = new ArrayList<>(); 
    ServiceReclamation service = new ServiceReclamation();

    public void ajouterPersonne (Personne personne){
        listPersonnes.add(personne);
    }

    public static GuichetStation getInstance() {
        if (instance == null) {
            instance = DataStorage.loadState("State.ser"); // Load from file or create new
        }
        return instance;
    }

    public static void saveInstance() {
        if (instance != null) {
            DataStorage.saveState(instance, "State.ser");
        }
    }

    public void acheterTitreTransport(Personne personne, TitreTransport titre){
        personne.ajouterTitre(titre);
        System.out.println("Titre ajouter avec succes\n"+ personne.toString() + "\nPrix: " + titre.prix + "\nValable le: " + titre.dateAchat);
    }
    public List<TitreTransport> afficherTitreTransport() {
        List<TitreTransport> titreTransports = new ArrayList<>();
        for (Personne p : listPersonnes) {
            titreTransports.addAll(p.getTitreTransport());
        }
        Collections.reverse(titreTransports);
        return titreTransports;
    }
    public TitreTransport chercherTitreParId(int id) throws TitreNonTrouverException {
        for (Personne p : listPersonnes) {
            for (TitreTransport titre : p.getTitreTransport()) {
                if (titre.getId() == id) {
                    return titre;
                }
            }
        }
        throw new TitreNonTrouverException();
    }
    public void verifierTitre(int id){
            try{
                TitreTransport titre = chercherTitreParId(id);
                if (titre.estValide(LocalDate.now())) {
                    System.out.println("Le titre est valide");
                }
            }catch(TitreNonTrouverException err){
                System.out.println("error: " + err.getMessage());
            }catch(TitreNonValideException err){
                System.out.println("error: " + err.getMessage());
            }
    }
    public void ajouterReclamation(Personne personne, TypeReclamation type, Suspendable cible, String description){
        Reclamation reclamation = new Reclamation(personne, type, cible, description, LocalDate.now());
        service.soumettre(reclamation);
    }
    public TreeSet<Reclamation> afficherReclamations(){
        return (service.afficherTousReclamations());
    }
    public void afficherPersonnes(){
        for (Personne p : listPersonnes) {
            System.out.println(p.toString());
        }
    }
}
