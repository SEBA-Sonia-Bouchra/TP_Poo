package transport.core;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GuichetStation implements Serializable{
    private static final long serialVersionUID = 1L;
    List<Personne> listPersonnes = new ArrayList<>(); 
    ServiceReclamation service = new ServiceReclamation();

     public GuichetStation() {
        // try {
        //     service = ServiceReclamation.charger(saveFile);
        // } catch (IOException | ClassNotFoundException e) {
        //     System.out.println("could not load reclamation file");
        //     service = new ServiceReclamation();
        // }
    }

    public void ajouterUsager (String nom, String prenom, LocalDate dateNaissance, boolean handicap){
        Usager usager = new Usager(nom, prenom, dateNaissance, handicap);
        listPersonnes.add(usager);
    }
    public void ajouterEmploye (String nom, String prenom, LocalDate dateNaissance, boolean handicap, String matricule, Fonction fonction){
        Employe employe = new Employe(nom, prenom, dateNaissance, handicap, matricule, fonction);
        listPersonnes.add(employe);
    }
    public void acheterTitreTransport(Personne personne, TitreTransport titre){
        personne.ajouterTitre(titre);
        listPersonnes.add(personne);
        System.out.println("Titre ajouter avec succes\n"+ personne.toString() + "\nPrix: " + titre.prix + "\nValable le: " + titre.dateAchat);
    }
    public List<TitreTransport> afficherTitreTransport() {
        List<TitreTransport> titreTransports = new ArrayList<>();
        for (Personne p : listPersonnes) {
            titreTransports.addAll(p.getTitreTransport());
        }
        titreTransports.sort((t1, t2) -> t2.getDateAchat().compareTo(t1.getDateAchat()));
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
    public void afficherReclamations(){
        service.afficherReclamations();
    }
    public void afficherPersonnes(){
        System.out.println(listPersonnes.toString());
    }
}
