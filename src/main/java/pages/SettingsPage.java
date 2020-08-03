package pages;

import enums.Provider;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Slf4j
public class SettingsPage extends BasePage {

  private By dropDownArrow = By.cssSelector("span[class^='mat-select-value-text']");
  private By providerLocator = By.cssSelector("span:not([hidden])[class='mat-option-text']");
  private By applyButton = By.id("host-button");

  public SettingsPage(WebDriver driver) {
    super(driver);
  }

  public void openProvidersDropDown() {
    webElementInteraction.waitUntilElementIsPresent(dropDownArrow);
    webElementInteraction.click(dropDownArrow);
  }

  public void selectProvider(Provider provider) {
    webElementInteraction.waitUntilElementIsPresent(providerLocator);
    webElementInteraction.clickUsingJavaScript(searchProvider(provider.getValue()));
  }

  private WebElement searchProvider(String providerName) {
    List<WebElement> providerList = driver.findElements(providerLocator);

    for (WebElement searchedProvider : providerList) {
      if (searchedProvider.getText().equals(providerName)) {
        log.info("Choose provider: {}", searchedProvider.getText());
        return searchedProvider;
      }
    }
    return providerList.get(0);
  }

  public void clickApplyButton() {
    webElementInteraction.waitUntilElementIsPresent(applyButton);
    webElementInteraction.click(applyButton);
  }
}
