import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FakeMeter extends UnicastRemoteObject implements Meter {

    private PowerCompany currentCompany;
    private List<Reading> history;
    private String currentCompanyName;
    private final String id;
    private int amount;

    public FakeMeter(PowerCompany pc) throws RemoteException {
        history = new ArrayList<Reading>();
        amount = 0;
        id = UUID.randomUUID().toString();
        currentCompany = pc;
        currentCompanyName = pc.getName();
        currentCompany.register(this, currentCompany.getTariff(history));
    }

    public void switchTo(PowerCompany newCompany, Tariff newTariff) throws RemoteException {
        if (!currentCompanyName.equals(newCompany.getName()) || currentCompany.equals(newCompany)) 
            currentCompany.unregister(this);
        newCompany.register(this, newTariff);        
        currentCompany = newCompany;
        currentCompanyName = newCompany.getName();
    }

    public void sendReading() throws RemoteException {
        Reading current = new Reading(amount, id);
        history.add(current);
        currentCompany.read(current);
    }

    public void sendAlert(Alert alert) throws RemoteException {
        currentCompany.alert(alert);
    }

    public List<Reading> getHistory() throws RemoteException {
        return history;
    }

    public void command(int code) throws RemoteException {
        System.out.println("Carrying out command no. " + code + ".");
    }

    public String getId() throws RemoteException {
        return id;
    }

}
