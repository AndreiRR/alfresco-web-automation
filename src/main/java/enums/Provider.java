package enums;

public enum Provider {

  BPM("BPM"),
  ECM("ECM"),
  ALL("ALL");

  private String value;

  Provider(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
