package files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import api.NodesPrecondition;
import driver.support.BaseTests;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;

@Tag("regression")
public class ContentServiceUISuite extends BaseTests {

  private static final String USERNAME = "guest@example.com";
  private static final String PASSWORD = "Password";

  private LoginPage loginPage;
  private HomePage homePage;

  private NodesPrecondition precondition = new NodesPrecondition();
  private Response entries;
  private int entriesListSize;

  private String id;
  private String folderName = "AndreiRR";
  private String folderDescription = "description";

  @BeforeEach
  public void beforeEach() {
    id = precondition.getEntries().getBody().jsonPath().getString("list.entries.entry");

    entriesListSize = precondition.getEntries().getBody().jsonPath().getList("list.entries").size();

    for (int i = 0; i < entriesListSize; i++) {
      if (!precondition.getEntries().getBody().jsonPath().getList("list.entries").isEmpty()) {
        id = precondition.getEntries().getBody().jsonPath().getList("list.entries.entry.id").get(0)
            .toString();
        precondition.deleteFolder(id);
      }
    }

    super.beforeEach();

    loginPage = new LoginPage(driver);
    homePage = new HomePage(driver);

    loginPage.navigateToPageUrl("login");
    loginPage.login(USERNAME, PASSWORD);
    homePage.navigateToPageUrl("files");
  }

  @Test
  public void shouldSuccessfullyCreateNewFolder() {
    homePage.createFolder(folderName, folderDescription);

    assertTrue(homePage.isFolderImageDisplayed(),"Folder image not displayed");
    assertEquals(folderName, homePage.getFolderName(folderName).getText(),"Folder name not equals with expected");
  }

  @Test
  public void shouldDisplayErrorToastMessageWhenCreateFolderWithSameName() {
    entries = precondition.createFolderDetails();
    homePage.createFolder(folderName,"desc");

    assertEquals("There's already a folder with this name. Try a different name.",
        homePage.getErrorToastMessage(), "Folder name not equals with expected");
  }

  @AfterEach
  public void afterEach() {
    super.afterEach();

    for (int i = 0; i < entriesListSize; i++) {
      if (!precondition.getEntries().getBody().jsonPath().getList("list.entries").isEmpty()) {
        id = precondition.getEntries().getBody().jsonPath().getList("list.entries.entry.id").get(0)
            .toString();
        precondition.deleteFolder(id);
      }
    }
  }
}
