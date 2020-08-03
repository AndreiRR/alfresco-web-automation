package webelement.support;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class WebElementInteraction {

  private static final String TIMEOUT_EXCEPTION = "Timeout exception";
  private static final int WAIT_TIME = 40;
  private static final int POLL_TIME = 2;

  private RemoteWebDriver driver;

  public WebElementInteraction(WebDriver driver) {
    this.driver = (RemoteWebDriver) driver;
  }

  public FluentWait<WebDriver> setWaitingTime(int timeOutInSeconds, int pollingTimeInMillis) {
    return new WebDriverWait(driver, timeOutInSeconds)
        .pollingEvery(Duration.ofMillis(pollingTimeInMillis))
        .ignoring(ElementClickInterceptedException.class)
        .ignoring(ElementNotInteractableException.class)
        .ignoring(NoSuchElementException.class)
        .ignoring(TimeoutException.class)
        .ignoring(StaleElementReferenceException.class);
  }

  public void waitUntilElementIsPresent(By by) {
    log.info("Wait element presence: {}", by);
    try {
      setWaitingTime( WAIT_TIME, POLL_TIME)
          .until(ExpectedConditions.visibilityOfElementLocated(by));
    } catch (TimeoutException timeoutException) {
      Assertions.fail(TIMEOUT_EXCEPTION, timeoutException.getCause());
    }
  }

  public void click(By by) {
    log.info("Click element: {}", by);
    try {
      setWaitingTime(WAIT_TIME, POLL_TIME)
          .until(ExpectedConditions.elementToBeClickable(by));
      driver.findElement(by).click();
    } catch (TimeoutException timeoutException) {
      Assertions.fail(TIMEOUT_EXCEPTION, timeoutException.getCause());
    }
  }

  public void click(WebElement webElement) {
    log.info("Click element: {}", webElement.getText());
    try {
      setWaitingTime(WAIT_TIME, POLL_TIME)
          .until(ExpectedConditions.elementToBeClickable(webElement));
      webElement.click();
    } catch (TimeoutException timeoutException) {
      Assertions.fail(TIMEOUT_EXCEPTION, timeoutException.getCause());
    }
  }

  public void clickUsingJavaScript(WebElement element) {
    log.info("Click element: {}", element);
    try {
      setWaitingTime(WAIT_TIME, POLL_TIME)
          .until(ExpectedConditions.elementToBeClickable(element));
      driver.executeScript("arguments[0].click()", element);
    } catch (TimeoutException timeoutException) {
      Assertions.fail(String.format(TIMEOUT_EXCEPTION, timeoutException.getCause()));
    }
  }

  public void sendKeys(By by, String textToType) {
    log.info("Clear and send keys:{}", textToType);
    try {
      setWaitingTime(WAIT_TIME, POLL_TIME)
          .until(ExpectedConditions.elementToBeClickable(by));
      driver.findElement(by).clear();
      driver.findElement(by).sendKeys(textToType);
    } catch (TimeoutException timeoutException) {
      Assertions.fail(String.format(TIMEOUT_EXCEPTION, timeoutException.getCause()));
    }
  }

  public boolean isElementDisplayed(By by) {
    log.info("Check element is displayed: {}", by);
    try {
      setWaitingTime( WAIT_TIME, POLL_TIME)
          .until(ExpectedConditions.visibilityOfElementLocated(by));
      return true;
    } catch (TimeoutException timeoutException) {
      log.error("Element not displayed: {}", by, timeoutException.getCause());
      return false;
    }
  }

  public String getElementText(By by) {
    log.info("Get element text: {}", driver.findElement(by).getText());
    try {
      return setWaitingTime(WAIT_TIME, POLL_TIME)
          .until(ExpectedConditions.visibilityOfElementLocated(by)).getText();
    } catch (TimeoutException timeoutException) {
      return TIMEOUT_EXCEPTION + timeoutException.getCause();
    }
  }
}
