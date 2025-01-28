package by.mikhalachkin.electroshop.model.computers;

import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Tablet extends ComputerAppliance implements Portable {
  private final OperatingSystemTablet operatingSystem;
  private final int ram;
  private final String processor;
  private final String storage;
  private final double batteryCapacity;
  private final double weight;

  @Override
  public double getBatteryCapacity() {
    return batteryCapacity;
  }

  @Override
  public double getWeight() {
    return weight;
  }
}
