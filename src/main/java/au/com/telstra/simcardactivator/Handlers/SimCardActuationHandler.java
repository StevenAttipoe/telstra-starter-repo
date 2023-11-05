package au.com.telstra.simcardactivator.Handlers;

import au.com.telstra.simcardactivator.DTOs.Requests.ActivateSIMCard;
import au.com.telstra.simcardactivator.DTOs.Response.ActuationResult;
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

    public ActuationResult actuate(ActivateSIMCard simCard) {
        return restTemplate.postForObject(incentiveApiUrl, simCard, ActuationResult.class);
    }
}
