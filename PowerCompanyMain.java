import java.io.Console;
import java.rmi.*;
import java.rmi.server.*;

public class PowerCompanyMain {

    public static void main(String[] args) throws Exception {

        String registry = args[0];
        String companyName = args[1];
        int dayRate = Integer.parseInt(args[2]); 
        int nightRate = Integer.parseInt(args[3]); 

        int tries = 0;
        int maxTries = 5;

        PowerCompany pc = new SmartPowerCompany(dayRate, nightRate, companyName);
        Naming.rebind("rmi://" + registry + "/" + companyName, pc);

        while (true) {

            try {

                Console console = System.console();
                String answer = console.readLine("Would you like to send a command to a meter? y/n\n");
                if (answer.equals("y")) {
                    String meterId = console.readLine("Please enter the meter ID you wish to command.\n");
                    Meter m = (Meter) Naming.lookup("rmi://" + registry + "/meters/" + meterId);
                    pc.command(m, 0);
                } 

            } catch (RemoteException e) {
                System.out.println("Connection lost. Attempting to reconnect...");
                if (++tries == maxTries) 
                    System.out.println("Meter unavailable. Contact customer.");
            } catch (NotBoundException e) {
                System.out.println("The entity you have specified does not exist.");
                System.out.println("Please try again.");
            } catch (Exception e) {
                throw e;
            }

        }

    }

}
