package arustamov.step.definition;

import arustamov.context.SpringContextAware;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import java.time.Instant;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Hook implements SpringContextAware {

    private final Logger LOGGER = LoggerFactory.getLogger(Hook.class);

    @Value("${appium.start.service}")
    private Boolean startAppiumService;

    @Managed
    public WebDriver driver;

    @Autowired(required = false)
    private AppiumDriverLocalService appiumService;

    @Before
    public void setUp() {
        LOGGER.info("##################################");
        LOGGER.info("Test start time: {}", Instant.now());

        if (startAppiumService) {
            appiumService.start();
        }
    }

    @After
    public void tearDown() {
        LOGGER.info("################################");
        LOGGER.info("Test end time: {}", Instant.now());

        if (startAppiumService) {
            appiumService.stop();
        }
    }
}
