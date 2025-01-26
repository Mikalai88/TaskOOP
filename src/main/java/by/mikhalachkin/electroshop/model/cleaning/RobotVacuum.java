package by.mikhalachkin.electroshop.model;

public class RobotVacuum extends CleaningEquipment {
  private int batteryCapacity; // Емкость аккумулятора в мАч
  private boolean supportsSmartControl; // Поддержка управления через приложение

  public RobotVacuum(String name, String brand, double price, String powerSource, int batteryCapacity, boolean supportsSmartControl) {
    super(name, brand, price, powerSource);
    this.batteryCapacity = batteryCapacity;
    this.supportsSmartControl = supportsSmartControl;
  }

  public int getBatteryCapacity() {
    return batteryCapacity;
  }

  public boolean isSupportsSmartControl() {
    return supportsSmartControl;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(", Battery Capacity: %d mAh, Smart Control: %b", batteryCapacity, supportsSmartControl);
  }
}

