package au.com.telstra.simcardactivator.Controllers;

import au.com.telstra.simcardactivator.DTOs.Requests.ActivateSIMCard;
import au.com.telstra.simcardactivator.Handlers.SimCardActuationHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class SIMController {
    private final SimCardActuationHandler simCardActuationHandler;

    public SIMController(SimCardActuationHandler simCardActuationHandler) {
        this.simCardActuationHandler = simCardActuationHandler;
    }

    @PostMapping("/activate")
    public void activateSIMCard(@RequestBody ActivateSIMCard simCard){
        var actuationResult = simCardActuationHandler.actuate(simCard);
        System.out.println(actuationResult);
    }
}
