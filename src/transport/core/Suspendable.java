package transport.core;

import java.io.Serializable;

public interface Suspendable extends Serializable{
    
    void suspendre();
    void reactiver();
    public boolean estSuspendu();
    public String getEtat(); // retourne "suspendu" ou bien "actif"
    
}
