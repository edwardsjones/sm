import java.util.List;

public interface PowerCompany {

    Tariff getTariff(List<Reading> history);

    void register(Meter m, Tariff tariff);

    void unregister(Meter m);

    void alert(Alert alert);
    
    void read(Reading latest);

}
