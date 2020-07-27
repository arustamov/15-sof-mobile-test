package arustamov;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    plugin = {"pretty", "json:target/cucumber/report/mobile/debug_suite.json", "html:target/cucumber", "rerun:target/failed.txt"},
    glue = "arustamov.step.definition",
    features = "classpath:features",
    tags = "@debug"
)
public class DebugSuite {
}
