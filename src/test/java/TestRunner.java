import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/carCheck-report.html", "json:target/cucumber.json"},
        features = {"src/test/resources/features"},
        glue = {"com.car.qa.stepDefs"}
)
public class TestRunner {

}
