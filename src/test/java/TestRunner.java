import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"html:target/carCheck-report.html", "json:target/cucumber.json"},
        features = {"src/test/resources/features"},
        glue = {"com.car.qa.stepDefs"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
