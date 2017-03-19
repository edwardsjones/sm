import java.io.Console;
import java.rmi.*;
import java.rmi.server.*;
import java.util.List;

public class MeterMain {

    public static void main(String[] args) throws Exception {

        String registry = args[0];
        String companyName = args[1];
        String brokerName = args[2];

        int tries = 0;
        int maxTries = 5;

        PowerCompany pc = (PowerCompany) Naming.lookup("rmi://" + registry + "/" + companyName);
        Meter m = new FakeMeter(pc);
        Naming.rebind("rmi://" + registry + "/meters/" + m.getId(), m);
        Broker b = (Broker) Naming.lookup("rmi://" + registry + "/" + brokerName);

        boolean reconnecting = false;
        ReadingThread t = new ReadingThread(m, reconnecting);
        t.start();

        while (true) {

            try {

                if (reconnecting) {
                    Thread.sleep(10000);
                    pc = (PowerCompany) Naming.lookup("rmi://" + registry + "/" + companyName);
                    b = (Broker) Naming.lookup("rmi://" + registry + "/" + brokerName);
                    m.switchTo(pc, pc.getTariff(m.getHistory()));
                    t = new ReadingThread(m, reconnecting);
                    t.start();
                    reconnecting = false;
                }

                // Code to show brokers can talk to multiple meters simultaneously.
                /*
                while (true) {
                    Deal newDeal = b.getBetterDeal(m);
                    newDeal.accept();
                }

                // Code to show power companies can talk to multiple meters simultaneously.
                while (true) {
                    Alert a = new Alert(m.getId(), "Test.");
                    m.sendAlert(a);    
                }
                */

                Console console = System.console();
                String option = console.readLine("To look for a new deal, type 1.\nTo register with a new power company, type 2.\nTo change your broker, type 3.\nIf you wish to alert your provider about the state of your service/meter, type 4.\n");

                if (option.equals("1")) {

                    // For testing reconnectivity. 
                    Thread.sleep(8000);

                    Deal newDeal = b.getBetterDeal(m);
                    System.out.println("Found deal with rate: " + newDeal.getTariff().getDayRate() + ".\n");
                    String answer = console.readLine("Accept? y/n\n");

                    if (answer.equals("y")) {
                        newDeal.accept();
                        pc = newDeal.getPowerCompany();
                        System.out.println("Deal accepted.");
                    } else {
                        System.out.println("Deal declined.");
                    }

                } else if (option.equals("2")) {

                    String newCompany = console.readLine("Please type the name of the power company you wish to register with.\n");
                    pc = (PowerCompany) Naming.lookup("rmi://" + registry + "/" + newCompany);
                    m.switchTo(pc, pc.getTariff(m.getHistory()));
                    companyName = newCompany;
                    System.out.println("Registration was a success.");

                } else if (option.equals("3")) {

                    String newBroker = console.readLine("Please type the name of the broker you wish to use.\n");
                    b = (Broker) Naming.lookup("rmi://" + registry + "/" + newBroker);
                    brokerName = newBroker;
                    System.out.println("Broker changed.");

                } else if (option.equals("4")) {

                    String reason = console.readLine("Please explain the problem.\n");
                    Alert alert = new Alert(m.getId(), reason);
                    m.sendAlert(alert);
                    System.out.println("Alert sent.\n");

                }

            } catch (RemoteException e) {
                System.out.println("Your connection has been lost. Attempting to reconnect...");
                t.endThread();
                reconnecting = true;
                if (++tries == maxTries) throw e;
            } catch (NotBoundException e) {
                System.out.println("The entity you have specified does not exist.");
                System.out.println("Please try again.");
            } catch (Exception e) {
                throw e;
            }

        }

    }

}

class ReadingThread extends Thread {

    private Meter m;
    private boolean running, status;

    public ReadingThread(Meter m, boolean status) {
        this.m = m;
        running = true;
        this.status = status;
    }

    public void run() {

        while (running) {

            try {
                m.sendReading();
                Thread.sleep(10000);
            } catch (RemoteException e) {
                status = true;
                endThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    
    }

    public void endThread() {
        running = false;
    }

}
