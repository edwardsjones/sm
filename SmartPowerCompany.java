import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.Random;

public class SmartPowerCompany extends UnicastRemoteObject implements PowerCompany {

    private ConcurrentHashMap<String, Tariff> customers;
    private final Tariff tariff;

    public SmartPowerCompany(int dayRate, int nightRate) throws RemoteException {
        customers = new ConcurrentHashMap<String, Tariff>();
        tariff = new Tariff(dayRate, nightRate); 
    }

    // Possible to implement some sort of tariff determining algorithm, just not relevant.
    public Tariff getTariff(List<Reading> history) throws RemoteException {
        Random rng = new Random();
        int dayRate = rng.nextInt(21) + 15;
        int nightRate = rng.nextInt(11) + 5;
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
        System.out.println("Yo.");
    }

    public void read(Reading latest) throws RemoteException {
    }

    public void command(Meter m, int action) {
    }

}
