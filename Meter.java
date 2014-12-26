import java.util.List;

public interface Meter {

    void registerWithCompany(PowerCompany pc);

    void unregisterWithCompany();

    void sendReadings();

    void sendAlert();

    List<Reading> getHistory();

}
