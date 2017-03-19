import java.util.List;
import java.rmi.*;

public interface Meter extends Remote {

    void switchTo(PowerCompany pc, Tariff newTariff) throws RemoteException;

    void sendReading() throws RemoteException;

    void sendAlert(Alert alert) throws RemoteException;

    List<Reading> getHistory() throws RemoteException;

    String getId() throws RemoteException;

    void command(int code) throws RemoteException;

}
