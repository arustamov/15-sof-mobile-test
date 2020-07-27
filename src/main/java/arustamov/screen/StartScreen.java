package arustamov.screen;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;

public class StartScreen extends MobilePageObject {

    private static final String CONTINUE_BUTTON_ID = "submitBtn";
    private static final String TERMS_AND_PRIVACY_LINK_ID = "termsAndPrivacyTxt";
    private static final String TERMS_OF_SERVICE_TEXT = "Terms of Service";
    private static final String PRIVACY_POLICY_TEXT = "Privacy Policy";

    public void tapContinue() {
        find(
            By.id(CONTINUE_BUTTON_ID)
        )
            .click();
    }

    public void tapTermsOfService() {
        final WebElementFacade termsAndPrivacyElement = getTermsAndPrivacyElement();
        final Rectangle rectangle = termsAndPrivacyElement.getRect();
        String text = termsAndPrivacyElement.getText();
        Integer textLength = text.length();

        Integer termsOfServiceIndex = text.indexOf(TERMS_OF_SERVICE_TEXT);

        tapCoordinates(
            rectangle.getX() + rectangle.getWidth() * termsOfServiceIndex / textLength,
            rectangle.getY()
        );

    }

    public void tapPrivacyPolicy() {
        final WebElementFacade termsAndPrivacyElement = getTermsAndPrivacyElement();
        final Rectangle rectangle = termsAndPrivacyElement.getRect();
        String text = termsAndPrivacyElement.getText();
        Integer textLength = text.length();

        Integer privacyPolicyIndex = text.indexOf(PRIVACY_POLICY_TEXT);

        tapCoordinates(
            rectangle.getX() + rectangle.getWidth() * privacyPolicyIndex / textLength,
            rectangle.getY()
        );
    }

    private WebElementFacade getTermsAndPrivacyElement() {
        return find(
            By.id(TERMS_AND_PRIVACY_LINK_ID)
        );
    }
}
