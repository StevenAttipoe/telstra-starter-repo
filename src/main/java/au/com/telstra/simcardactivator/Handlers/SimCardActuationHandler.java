package au.com.telstra.simcardactivator.Handlers;

import au.com.telstra.simcardactivator.DTOs.Requests.ActivateSIMCard;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SimCardActuationHandler {
    private final RestTemplate restTemplate;
    private final String incentiveApiUrl;

    public SimCardActuationHandler(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.incentiveApiUrl = "http://localhost:8444/actuate";
    }

    public Object actuate(ActivateSIMCard simCard) {
        return restTemplate.postForObject(incentiveApiUrl, simCard, Object.class);
    }
}
