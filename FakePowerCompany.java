import java.util.List;

public class FakePowerCompany implements PowerCompany {

    public Tariff getTariff(List<Reading> history) {

        return new Tariff(10, 5);

    }

    public void register(Meter m, Tariff t) {}

    public void unregister(Meter m) {}

    public void alert(Alert a) {}

    public void read(Reading latest) {}

}
