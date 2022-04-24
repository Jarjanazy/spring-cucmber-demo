package jalil.springcucumberdemo.glue;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import jalil.springcucumberdemo.CatService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class StepDefinition {
    String port;
    ResponseEntity<String> response;

    CatService catService;
    public StepDefinition(Environment environment) {
        this.port = environment.getProperty("local.server.port");
    }

    @Given("I have a cat:")
    public void wantToGo(DataTable table){
        String catName = table.asMap().get("name");
        catService.addCat(catName);
    }

    @Then("I can go")
    public void canGo(){
        response = new RestTemplate().getForEntity(format("http://localhost:%s/test", port), String.class);

        String result = response.getBody();
        assertThat(result).isEqualTo("hey");
    }
}
