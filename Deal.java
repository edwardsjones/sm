import java.io.Serializable;
import java.rmi.*;

public class Deal implements Serializable {

    private final Meter m;
    private final PowerCompany pc;
    private final Tariff tariff;

    public Deal(Meter m, PowerCompany pc, Tariff tariff) {
        this.m = m;
        this.pc = pc;
        this.tariff = tariff;
    }

    public void accept() throws RemoteException {
        m.switchTo(pc, tariff);
    }

    public Tariff getTariff() {
        return tariff;
    }

    public PowerCompany getPowerCompany() {
        return pc;
    }

}
