import java.io.Serializable;

public class Alert implements Serializable{

    private final String message, sourceID;

    public Alert(String sourceID, String message) {
        this.sourceID = sourceID;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getSourceID() {
        return sourceID;
    }

}
