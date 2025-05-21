package transport.core;
import java.io.*;

// a utility class used for data storage
public class DataStorage {
    public static void saveState( GuichetStation guichet, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeInt(Reclamation.getCurrentIdInc()); // save static counter for reclamation
            out.writeInt(TitreTransport.getCurrentIdInc()); // save static counter for titre transport
            out.writeObject(guichet); // save guichet context
            System.out.println("application state saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GuichetStation loadState(String filename) {
        int savedIdInc;
        int savedComp;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            savedComp= in.readInt(); // load static counter for reclamation
            Reclamation.setIdInc(savedComp);
            savedIdInc = in.readInt(); // load static counter for titre transport
            TitreTransport.setIdInc(savedIdInc);
            return (GuichetStation) in.readObject(); // load guichet
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("no saved state found. Starting new instance.");
            return new GuichetStation(); // incase no guichet found we instanciate a new one 
        }
    }
}
