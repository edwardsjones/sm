import java.io.Serializable;

public class Tariff implements Serializable {

    private final int dayRate, nightRate;

    public Tariff(int dayRate, int nightRate) {
        this.dayRate = dayRate;
        this.nightRate = nightRate;
    }

    public int getDayRate() {
        return dayRate;
    }    
    
    public int getNightRate() {
        return nightRate;
    }

}
