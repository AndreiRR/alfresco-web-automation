package driver.support.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

@Slf4j
public class BrowserFactory {

  public WebDriver driver;

  /**
   * Method to start specific browser
   *
   * @return specific browser driver
   */
  public WebDriver startBrowser() {
      driver = createChromeDriver();
    driver.manage().window().maximize();
    return driver;
  }

  private WebDriver createChromeDriver() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions chromeOptions = new ChromeOptions();
//    chromeOptions.addArguments("--window-size=1920,1080");
//    chromeOptions.addArguments("--start-maximized");
    chromeOptions.addArguments("--no-sandbox");
    chromeOptions.addArguments("--disable-dev-shm-usage");
    chromeOptions.setAcceptInsecureCerts(true);
    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);

    return new ChromeDriver(chromeOptions);
  }

  /**
   * Method to create firefox driver
   *
   * @return firefox driver details
   */
  private WebDriver createFirefoxWebDriver() {
    WebDriverManager.firefoxdriver().setup();
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    firefoxOptions.addArguments("--no-sandbox");
    firefoxOptions.addArguments("--disable-dev-shm-usage");

    return new FirefoxDriver(firefoxOptions);
  }

  public void closeBrowser() {
    log.info("Closing browser...");
    driver.quit();
  }
}
