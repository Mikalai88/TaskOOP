package by.mikhalachkin.electroshop.model;

public enum EnergyEfficiency {
  A_PLUS_PLUS("A++"),
  A_PLUS("A+"),
  A("A"),
  B("B"),
  C("C"),
  D("D");

  private final String label;

  EnergyEfficiency(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  @Override
  public String toString() {
    return label;
  }
}
