package jalil.springcucumberdemo.glue;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jalil.springcucumberdemo.Cat;
import jalil.springcucumberdemo.CatService;
import jalil.springcucumberdemo.NewCatDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Log4j2
public class StepDefinition {
    String port;
    ResponseEntity<Cat> response;

    @Autowired
    CatService catService;
    public StepDefinition(Environment environment) {
        this.port = environment.getProperty("local.server.port");
    }

    @Given("I add a cat:")
    public void addCat(DataTable table){

        String catName = table.asMap().get("name");

        NewCatDto catDto = new NewCatDto(catName);

        new RestTemplate()
                .postForEntity(format("http://localhost:%s/cat", port), catDto, Cat.class);

    }

    @Given("I have a cat:")
    public void haveCat(DataTable dataTable) {
        String name = dataTable.asMap().get("name");
        catService.addCat(name);
    }
    @When("I ask for {word}")
    public void askForCat(String name) {
        response = new RestTemplate()
                .getForEntity(format("http://localhost:%s/cat/%s", port, name), Cat.class);
    }

    @When("I delete {word}")
    public void deleteCat(String name) {
        catService.deleteByName(name);
    }

    @Then("I get {word}")
    public void getCat(String name){
        Cat cat = response.getBody();
        assertThat(cat.getName()).isEqualTo(name);
    }

    @Then("{word} is removed from the database")
    public void verifyCatDeletion(String name) {
        assertFalse(catService.findByName(name).isPresent());
    }
}
