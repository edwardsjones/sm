import java.util.ArrayList;
import java.util.List;

public class FakeMeter implements Meter {

    public void registerWithCompany(PowerCompany pc) {

        

    }

    public void unregisterWithCompany() {}

    public void sendReadings() {}

    public void sendAlert() {}

    public List<Reading> getHistory() {

        return new ArrayList<Reading>();

    }

}
