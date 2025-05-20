package test;

import java.time.*;

import transport.core.CartePersonnelle;
import transport.core.Fonction;
import transport.core.GuichetStation;
import transport.core.MoyenTransport;
import transport.core.Personne;
import transport.core.ReductionImpossibleException;
import transport.core.Station;
import transport.core.Suspendable;
import transport.core.Ticket;
import transport.core.TitreTransport;
import transport.core.TypeReclamation;
import transport.core.Usager;
import transport.core.DataStorage;

public class TestTransport {

  public static void main(String[] args) {
       String saveFile = "State.ser";
       GuichetStation guichet = DataStorage.loadState(saveFile);
       
        // 1. Ajouter Usager et Employe
      //   guichet.ajouterUsager("Doe", "John", LocalDate.of(2000, 1, 15), false);
      //   guichet.ajouterEmploye("Smith", "Alice", LocalDate.of(1975, 6, 20), true, "EMP123", Fonction.AGENT);

        // 2. Créer un usager et un titre de transport
     //    Personne usager = new Usager("Lee", "Sonia", LocalDate.of(2006, 3, 1), true);
     //    TitreTransport ticket = new Ticket();

     //    guichet.acheterTitreTransport(usager, ticket);

     //    try {
          //   TitreTransport carte = new CartePersonnelle(usager);
          //   guichet.acheterTitreTransport(usager, carte);
     //    } catch (ReductionImpossibleException e) {
          //   System.out.println("Erreur lors de la création de la carte personnelle : " + e.getMessage());
     //    }

        // 3. Valider un titre
     //    guichet.verifierTitre(ticket.getId());
        System.out.println(guichet.afficherTitreTransport().toString());;

        // 4. Ajouter des réclamations
      //   Suspendable station = new Station("El Harrach"); 
      //   Suspendable bus = new MoyenTransport("AOF8"); 
      //   Personne reclamateur = new Usager("Ben", "Ali", LocalDate.of(1990, 5, 10), false);

      //   guichet.ajouterReclamation(reclamateur, TypeReclamation.SERVICE, station, "Le bus est en retard.");
      //   guichet.ajouterReclamation(reclamateur, TypeReclamation.SERVICE, station, "La station est sale.");
      //   guichet.ajouterReclamation(reclamateur, TypeReclamation.TECHNIQUE, bus, "Bus tambe en panne.");
      //   guichet.ajouterReclamation(reclamateur, TypeReclamation.SERVICE, station, "Comportement inacceptable.");

        // 5. Afficher les réclamations
        guichet.afficherReclamations();

        guichet.afficherPersonnes();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            DataStorage.saveState(guichet, saveFile);
        }));
    }
}