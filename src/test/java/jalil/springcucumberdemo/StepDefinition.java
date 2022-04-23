package jalil.springcucumberdemo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.fail;


@Log4j2
public class StepDefinition{
    @Given("I want to go")
    public void wantToGo(){
        System.out.println("hey");
    }

    @Then("I can go")
    public void canGo(){

    }
}
