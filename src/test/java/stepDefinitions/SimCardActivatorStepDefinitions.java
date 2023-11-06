package stepDefinitions;

import au.com.telstra.simcardactivator.DTOs.Requests.SimCard;
import au.com.telstra.simcardactivator.Models.Activation;
import au.com.telstra.simcardactivator.SimCardActivator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_old.Ac;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {
    @Autowired
    private TestRestTemplate restTemplate;

    private SimCard simCard;


    @Given("a functional sim card")
    public void aFunctionalSimCard() {
        simCard = new SimCard("1255789453849037777", "mark.doe@mail.com");
    }

    @Given("a broken sim card")
    public void aBrokenSimCard() {
        simCard = new SimCard("8944500102198304826", "sam.grey@mail.com");
    }

    @When("a request to activate the sim card is submitted")
    public void aRequestToActivateTheSimCardIsSubmitted() {
        var response = this.restTemplate.postForObject("http://localhost:8080/api/v1/activate", simCard, SimCard.class);
    }

    @Then("the sim card is activated and its state is recorded to the database")
    public void theSimCardIsActivatedAndItsStateIsRecordedToTheDatabase() {
        Activation response = this.restTemplate.getForObject("http://localhost:8080/api/v1/query?simCardId={simCardId}", Activation.class, simCard.getIccid());
        assertTrue(response.getActive());
    }

    @Then("the sim card fails to activate and an error message is return")
    public void theSimCardFailsToActivateAndItsStateIsRecordedToTheDatabase() {
        Activation response = this.restTemplate.getForObject("http://localhost:8080/api/v1/query?simCardId={simCardId}", Activation.class, simCard.getIccid());
        assertFalse(response.getActive());

    }


}