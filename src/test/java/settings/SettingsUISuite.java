package settings;

import static org.junit.jupiter.api.Assertions.assertTrue;

import driver.support.BaseTests;
import enums.Provider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.SettingsPage;

@Tag("regression")
public class SettingsUISuite extends BaseTests {

  private SettingsPage settingsPage;
  private LoginPage loginPage;

  @BeforeEach
  public void beforeEach() {
    super.beforeEach();

    settingsPage = new SettingsPage(driver);
    loginPage = new LoginPage(driver);
    settingsPage.navigateToPageUrl("settings");
  }

  @Test
  public void shouldChangeProviderToEcm() {
    settingsPage.openProvidersDropDown();
    settingsPage.selectProvider(Provider.ECM);
    settingsPage.clickApplyButton();

    assertTrue(loginPage.isUsernamePlaceHolderDisplayed(), "Login page is not displayed");
  }

  @AfterEach
  public void afterEach() {
    super.afterEach();
  }
}
