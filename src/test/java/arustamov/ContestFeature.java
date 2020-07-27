package arustamov;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    plugin = {"pretty", "json:target/cucumber/report/contest.json", "html:target/cucumber"},
    glue = "arustamov.step.definition",
    features = "classpath:features/contest.feature"
)
public class ContestFeature {
}
