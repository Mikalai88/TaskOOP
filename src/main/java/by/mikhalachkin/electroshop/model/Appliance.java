package by.mikhalachkin.electroshop.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@EqualsAndHashCode
@SuperBuilder
public abstract class Appliance {
  private final String name;
  private final String brand;
  private final double price;
  private final String powerSource;
}
