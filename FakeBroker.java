public class FakeBroker implements Broker {

    public Deal getBetterDeal(Meter m) {

        return new Deal();

    }

}
