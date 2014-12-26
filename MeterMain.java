public class MeterMain {

    public static void main(String[] args) {

        Meter m = new FakeMeter();
        Broker b = new FakeBroker();

        Deal betterDeal = b.getBetterDeal(m);
        betterDeal.accept();

    }

}
