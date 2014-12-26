public class FakeBroker implements Broker {

    public Deal getBetterDeal(Meter m) {

        return new Deal(m, new FakePowerCompany(), new Tariff(10, 5));

    }

}
