package enums;

public enum BrowserName {

  CHROME("chrome"),
  FIREFOX("firefox");

  private String value;

  BrowserName(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
