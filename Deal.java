public class Deal {

    private final Meter m;
    private final PowerCompany pc;

    public Deal(Meter m, PowerCompany pc) {
        this.m = m;
        this.pc = pc;
    }

    public void accept() {

        m.unregisterWithCompany();
        m.registerWithCompany(pc);

    }

}
