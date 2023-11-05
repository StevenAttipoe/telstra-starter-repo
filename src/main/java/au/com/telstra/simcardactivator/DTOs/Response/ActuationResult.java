package au.com.telstra.simcardactivator.DTOs.Response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActuationResult {
    private boolean status;

    public ActuationResult(@JsonProperty("success") boolean status) {
        this.status = status;
    }


    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

}
