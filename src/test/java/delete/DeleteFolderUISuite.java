package delete;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.NodesPrecondition;
import driver.support.BaseTests;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;

public class DeleteFolderUISuite extends BaseTests {

  private static final String USERNAME = "guest@example.com";
  private static final String PASSWORD = "Password";

  private HomePage homePage;
  private LoginPage loginPage;
  private NodesPrecondition nodesPrecondition = new NodesPrecondition();
  private Response entries;
  private int entriesListSize;
  private String id;

  @BeforeEach
  public void beforeEach() {
    id = nodesPrecondition.getEntries().getBody().jsonPath().getString("list.entries.entry");

    entriesListSize = nodesPrecondition.getEntries().getBody().jsonPath().getList("list.entries").size();

    for (int i = 0; i < entriesListSize; i++) {
      if (!nodesPrecondition.getEntries().getBody().jsonPath().getList("list.entries").isEmpty()) {
        id = nodesPrecondition.getEntries().getBody().jsonPath().getList("list.entries.entry.id")
            .get(0)
            .toString();
        nodesPrecondition.deleteFolder(id);
      }
    }

    entries = nodesPrecondition.createFolderDetails();

    super.beforeEach();
    loginPage = new LoginPage(driver);
    homePage = new HomePage(driver);

    loginPage.navigateToPageUrl("login");
    loginPage.login(USERNAME, PASSWORD);
    homePage.navigateToPageUrl("files");

    homePage = new HomePage(driver);
  }

  @Test
  public void shouldSuccessfullyDeleteFolder() {
    homePage.clickOptionsButton();
    homePage.clickDelete();

    assertEquals("This folder is empty", homePage.getEmptyFolderLabel() , "Empty folder label not equals expected one");
  }

  @AfterEach
  public void afterEach() {
    super.afterEach();

    for (int i = 0; i < entriesListSize; i++) {
      if (!nodesPrecondition.getEntries().getBody().jsonPath().getList("list.entries").isEmpty()) {
        id = nodesPrecondition.getEntries().getBody().jsonPath().getList("list.entries.entry.id")
            .get(0)
            .toString();
        nodesPrecondition.deleteFolder(id);
      }
    }
  }
}
