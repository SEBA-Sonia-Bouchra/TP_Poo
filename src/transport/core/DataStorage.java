package transport.core;
import java.io.*;

// a utility class used for data storage
public class DataStorage {
    public static void saveState( GuichetStation guichet, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeInt(TitreTransport.getCurrentIdInc()); // save static counter
            out.writeObject(guichet); // save guichet context
            System.out.println("application state saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GuichetStation loadState(String filename) {
        int savedIdInc;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            savedIdInc = in.readInt(); // load static counter
            TitreTransport.setIdInc(savedIdInc);
            return (GuichetStation) in.readObject(); // load guichet
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved state found. Starting new instance.");
            return new GuichetStation();
        }
    }
}
