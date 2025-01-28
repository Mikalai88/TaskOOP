package by.mikhalachkin.electroshop.model.cleaning;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class RobotVacuum extends CleaningAppliance {
  private final int batteryCapacity;       // Емкость аккумулятора в мАч
  private final boolean supportsSmartControl; // Поддержка управления через приложение
}
