import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;

public class SmartPowerCompany extends UnicastRemoteObject implements PowerCompany {

    private ConcurrentHashMap<String, Tariff> customers;
    private final Tariff tariff;
    private final String name;

    public SmartPowerCompany(int dayRate, int nightRate, String name) throws RemoteException {
        customers = new ConcurrentHashMap<String, Tariff>();
        tariff = new Tariff(dayRate, nightRate); 
        this.name = name;
    }

    // Possible to implement some sort of tariff determining algorithm, just not relevant.
    public Tariff getTariff(List<Reading> history) throws RemoteException {
        return tariff;
    }

    public void register(Meter m, Tariff tariff) throws RemoteException {
        customers.put(m.getId(), tariff);
        System.out.println("Registered " + m.getId());
    }

    public void unregister(Meter m) throws RemoteException {
        customers.remove(m.getId());
        System.out.println("Unregistered " + m.getId());
    }

    public void alert(Alert a) throws RemoteException {
        System.out.println("Alert from " + a.getSourceID() + ".");
    }

    public void read(Reading latest) throws RemoteException {
        System.out.println("Reading from " + latest.getId() + "."); 
    }
    
    public void command(Meter m, int code) throws RemoteException {
        if (customers.containsKey(m))
            m.command(code);
    }

    public String getName() throws RemoteException {
        return name;
    }

}
