import java.rmi.*;
import java.util.List;

public interface PowerCompany extends Remote {

    Tariff getTariff(List<Reading> history) throws RemoteException; 

    void register(Meter m, Tariff tariff) throws RemoteException;

    void unregister(Meter m) throws RemoteException;

    void alert(Alert alert) throws RemoteException;
    
    void read(Reading latest) throws RemoteException;

    void command(Meter m, int code) throws RemoteException;

    String getName() throws RemoteException;
    
}
