package jalil.springcucumberdemo.glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class StepDefinition {
    @Autowired
    Environment environment;

    ResponseEntity<String> response;
    @Given("I want to go")
    public void wantToGo(){
        String port = environment.getProperty("local.server.port");
        response = new RestTemplate().getForEntity(format("http://localhost:%s/test", port), String.class);
    }

    @Then("I can go")
    public void canGo(){
        String result = response.getBody();
        assertThat(result).isEqualTo("hey");
    }
}
