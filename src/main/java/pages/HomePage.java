package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

  private By userInfo = By.id("adf-userinfo-ecm-name-display");
  private By createNewFolderButton = By.cssSelector("button[data-automation-id='create-new-folder']");
  private By nameInputField = By.cssSelector("input[id='adf-folder-name-input']");
  private By descriptionInputField = By.cssSelector("textarea[id='adf-folder-description-input']");
  private By createButton = By.cssSelector("button[id='adf-folder-create-button']");
  private By folderImage = By.cssSelector("img[class='adf-datatable-center-img-ie ng-star-inserted']");
  private By errorToast = By.cssSelector("simple-snack-bar[class='mat-simple-snackbar ng-star-inserted']");
  private By threeDotsButton = By.id("action_menu_right_0");
  private By deleteOption = By.cssSelector("#mat-menu-panel-10 > div > button:nth-child(5) > span");
  private By emptyFolderLabel = By.cssSelector("adf-datatable > div > div.adf-datatable-body > div > div > adf-empty-list > div > div > div.adf-empty-folder-this-space-is-empty");

  public HomePage(WebDriver driver) {
    super(driver);
  }

  public boolean isLoggedInSuccessfully() {
    return webElementInteraction.isElementDisplayed(userInfo);
  }

  public void clickCreateNewFolder() {
    webElementInteraction.waitUntilElementIsPresent(createNewFolderButton);
    webElementInteraction.click(createNewFolderButton);
  }

  public void fillName(String name) {
    webElementInteraction.sendKeys(nameInputField,name);
  }

  public void fillDescription(String description) {
    webElementInteraction.sendKeys(descriptionInputField, description);
  }

  public void clickCreateButton() {
    webElementInteraction.waitUntilElementIsPresent(createButton);
    webElementInteraction.click(driver.findElement(createButton));
  }

  public void createFolder(String folderName, String folderDescription) {
    clickCreateNewFolder();
    fillName(folderName);
    fillDescription(folderDescription);
    clickCreateButton();
  }

  public WebElement getFolderName(String folderName) {
    return driver.findElement(By.xpath(".//span[contains(text(),"  + "'" + folderName + "'" + ")]"));
  }

  public String getErrorToastMessage() {
    webElementInteraction.waitUntilElementIsPresent(errorToast);
    return webElementInteraction.getElementText(errorToast);
  }

  public boolean isFolderImageDisplayed() {
    return webElementInteraction.isElementDisplayed(folderImage);
  }

  public void clickOptionsButton() {
    webElementInteraction.waitUntilElementIsPresent(threeDotsButton);
    webElementInteraction.click(threeDotsButton);
  }

  public void clickDelete() {
    webElementInteraction.waitUntilElementIsPresent(deleteOption);
    webElementInteraction.click(deleteOption);
  }

  public String getEmptyFolderLabel() {
    webElementInteraction.waitUntilElementIsPresent(emptyFolderLabel);
    return webElementInteraction.getElementText(emptyFolderLabel);
  }
}
