import java.io.Serializable;

public class Reading implements Serializable {

    private final int amount;
    private final String id;

    public Reading(int amount, String id) {
        this.amount = amount;
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

}
