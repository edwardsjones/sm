import java.rmi.*;
import java.rmi.server.*;
import java.util.List;
import java.util.ArrayList;

public class BrokerMain {

    public static void main(String[] args) throws Exception {

        String registry = args[0];
        String brokerName = args[1];


        while (true) {

            try {

                List<PowerCompany> companies = new ArrayList<PowerCompany>();
                for (int i = 2; i < args.length; i++) {
                    String companyName = args[i];
                    PowerCompany company = (PowerCompany) Naming.lookup("rmi://" + registry + "/" + companyName);
                    companies.add(company); 
                }
                
                Broker b = new ListBroker(companies);
                Naming.rebind("rmi://" + registry + "/" + brokerName, b); 

            } catch (RemoteException e) {
                System.out.println("Connection lost.");
            } catch (NotBoundException e) {
                System.out.println("Power Company is down; attempting to reconnect.");
            }

            Thread.sleep(5000);

        }

    }

}
