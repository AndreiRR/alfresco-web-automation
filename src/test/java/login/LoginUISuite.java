package login;

import static org.junit.jupiter.api.Assertions.assertTrue;

import driver.support.BaseTests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;

@Tag("regression")
public class LoginUISuite extends BaseTests {

  private static final String USERNAME = "guest@example.com";
  private static final String PASSWORD = "Password";

  private LoginPage loginPage;
  private HomePage homePage;

  @BeforeEach
  public void beforeEach() {
    super.beforeEach();

    loginPage = new LoginPage(driver);
    homePage = new HomePage(driver);
    loginPage.navigateToPageUrl("login");
  }

  @Test
  public void shouldSuccessfullyLogin() {
    loginPage.login(USERNAME, PASSWORD);

    assertTrue(homePage.isLoggedInSuccessfully(), "Login failed");
  }

  @AfterEach
  public void afterEach() {
    super.afterEach();
  }
}
