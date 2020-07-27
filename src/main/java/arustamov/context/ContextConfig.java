package arustamov.context;

import arustamov.context.condition.StartAppiumServiceCondition;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("arustamov")
@PropertySource("classpath:context-config.properties")
public class ContextConfig {

    @Bean
    @Conditional(StartAppiumServiceCondition.class)
    public AppiumDriverLocalService appiumService() {
        return AppiumDriverLocalService.buildDefaultService();
    }
}

