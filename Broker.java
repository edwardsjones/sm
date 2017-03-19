import java.rmi.*;

public interface Broker extends Remote {

    Deal getBetterDeal(Meter m) throws RemoteException;

}
