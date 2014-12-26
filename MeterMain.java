public class MeterMain {

    public static void main(String[] args) {

        PowerCompany pc = new FakePowerCompany();
        Meter m = new FakeMeter(pc);
        Broker b = new FakeBroker();

        Deal betterDeal = b.getBetterDeal(m);
        betterDeal.accept();

    }

}
