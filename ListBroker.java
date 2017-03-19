import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.List;

public class ListBroker extends UnicastRemoteObject implements Broker {

    private ArrayList<PowerCompany> companies;

    public ListBroker(List<PowerCompany> companies) throws RemoteException {
        this.companies = new ArrayList<PowerCompany>(companies);
    }

    public Deal getBetterDeal(Meter m) throws RemoteException {

        List<Reading> history = m.getHistory();
        Tariff bestTariff = null;
        PowerCompany bestCompany = null;

        for (PowerCompany company : companies) {
            Tariff tariff = company.getTariff(history);
            if (bestTariff == null || bestTariff.getDayRate() > tariff.getDayRate()) {
                bestTariff = tariff;
                bestCompany = company;
            }
        }

        return new Deal(m, bestCompany, bestTariff);

    }

}
