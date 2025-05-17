package transport.core;
public class ReductionImpossibleException extends Exception{
    public String getMessage() {
        return "Creation de carte personelle refusee.";
    }
}