public class Tariff {

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
