package arustamov;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    plugin = {"pretty", "json:target/cucumber/report/sort.json", "html:target/cucumber"},
    glue = "arustamov.step.definition",
    features = "classpath:features/sort.feature"
)
public class SortFeature {
}
