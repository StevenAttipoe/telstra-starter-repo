package au.com.telstra.simcardactivator.Controllers;

import au.com.telstra.simcardactivator.DTOs.Requests.SimCard;
import au.com.telstra.simcardactivator.DTOs.Response.ActuationResult;
import au.com.telstra.simcardactivator.Handlers.SimCardActuationHandler;
import au.com.telstra.simcardactivator.Models.Activation;
import au.com.telstra.simcardactivator.Repositories.ActivationsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1")
public class SIMController {
    private final SimCardActuationHandler simCardActuationHandler;
    private final ActivationsRepository activationsRepository;

    public SIMController(SimCardActuationHandler simCardActuationHandler, ActivationsRepository activationsRepository) {
        this.simCardActuationHandler = simCardActuationHandler;
        this.activationsRepository = activationsRepository;
    }

    @PostMapping("/activate")
    public ResponseEntity<Activation> activateSIMCard(@RequestBody SimCard simCard){
        ActuationResult actuationResult = simCardActuationHandler.actuate(simCard);
        Activation entity = activationsRepository.save(new Activation(simCard.getIccid(),simCard.getCustomerEmail(), actuationResult.getStatus()));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/query")
    public ResponseEntity<Object> activateSIMCard(@RequestParam String simCardId){
        Optional<Activation> activationEntity =  activationsRepository.findByIccid(simCardId);

        if(activationEntity.isEmpty()){
            Map<String, String> response = new HashMap<>();
            response.put("Message", "Sim card not found");
            return  ResponseEntity.badRequest().body(response);
        }

        Activation activation = activationEntity.get();

        return ResponseEntity.ok().body(activation);
    }
}
