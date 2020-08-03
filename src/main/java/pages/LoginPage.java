package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

  private By usernameInputField = By.id("username");
  private By passwordInputField = By.id("password");
  private By signInButton = By.cssSelector("button[type=submit]");
  private By userInfo = By.id("adf-userinfo-ecm-name-display");

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public boolean isUsernamePlaceHolderDisplayed() {
   return webElementInteraction.isElementDisplayed(usernameInputField);
  }

  private void enterUsername(String username) {
    webElementInteraction.waitUntilElementIsPresent(usernameInputField);
    webElementInteraction.sendKeys(usernameInputField, username);
  }

  private void enterPassword(String password) {
    webElementInteraction.waitUntilElementIsPresent(passwordInputField);
    webElementInteraction.sendKeys(passwordInputField, password);
  }

  private void clickSignInButton() {
    webElementInteraction.waitUntilElementIsPresent(signInButton);
    webElementInteraction.click(signInButton);
  }

  // needs to be refactored/repair login form issue
  public void login(String username, String password) {
    enterUsername(username);
    enterPassword(password);
    clickSignInButton();
    while (!webElementInteraction.isElementDisplayed(userInfo)) {
      enterUsername(username);
      enterPassword(password);
      clickSignInButton();
    }
  }
}
