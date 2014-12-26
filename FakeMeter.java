import java.util.ArrayList;
import java.util.List;

public class FakeMeter implements Meter {

    private PowerCompany currentCompany;

    public FakeMeter(PowerCompany pc) {
        currentCompany = pc;
    }

    public void switchTo(PowerCompany newCompany, Tariff newTariff) {

        currentCompany.unregister(this);
        newCompany.register(this, newTariff);        

    }

    public void sendReading() {
        currentCompany.read(new Reading());
    }

    public void sendAlert(Alert alert) {
        currentCompany.alert(alert);
    }

    public List<Reading> getHistory() {

        return new ArrayList<Reading>();

    }

}
