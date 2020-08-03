package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webelement.support.WebElementInteraction;

@Slf4j
public class BasePage {

  private static final String BASE_URL = "http://qaexercise.envalfresco.com/";

  protected WebElementInteraction webElementInteraction;
  protected WebDriver driver;

  public BasePage(WebDriver driver) {
    this.driver = driver;
    new WebDriverWait(driver, 30, 2)
        .until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader-container")));
    webElementInteraction = new WebElementInteraction(driver);
  }

  public void navigateToPageUrl(String urlPath) {
    log.info("Navigate to page url: {}", urlPath);
    driver.get(BASE_URL + urlPath);
  }
}
