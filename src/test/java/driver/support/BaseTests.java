package driver.support;

import driver.support.factory.BrowserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

/**
 * Template for each test class
 */
public class BaseTests {

  protected WebDriver driver;
  private BrowserFactory browserFactory = new BrowserFactory();

  @BeforeEach
  public void beforeEach() {
    driver = browserFactory.startBrowser();
  }

  @AfterEach
  public void afterEach() {
   browserFactory.closeBrowser();
  }
}
