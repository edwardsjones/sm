import java.util.List;

public interface Meter {

    void switchTo(PowerCompany pc, Tariff newTariff);

    void sendReading();

    void sendAlert(Alert alert);

    List<Reading> getHistory();

}
