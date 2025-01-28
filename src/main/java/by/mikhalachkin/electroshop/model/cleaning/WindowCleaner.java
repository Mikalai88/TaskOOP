package by.mikhalachkin.electroshop.model.cleaning;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class WindowCleaner extends CleaningAppliance {
  private final boolean isRobot;            // Является ли устройством роботом
  private final double waterTankCapacity;   // Объем бака для воды (в литрах)
  private final int batteryLife;            // Время работы от батареи (в минутах)
  private final boolean supportsSmartControl; // Поддержка управления через приложение
}
