package au.com.telstra.simcardactivator.Controllers;

import au.com.telstra.simcardactivator.DTOs.Requests.ActivateSIMCard;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class SIMController {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public SIMController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/activate")
    public void activateSIMCard(@RequestBody ActivateSIMCard simCard){
        String iccid = simCard.getIccid();
        String activationUrl = "http://localhost:8444/actuate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("iccid", iccid);

        try {
            String jsonBody = objectMapper.writeValueAsString(requestBody);
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

            String response = restTemplate.postForObject(activationUrl, requestEntity, String.class);
            System.out.println("SIM Card Activation Response: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
