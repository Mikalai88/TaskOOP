package by.mikhalachkin.electroshop.model.computers;

import by.mikhalachkin.electroshop.model.EnergyEfficiency;
import by.mikhalachkin.electroshop.model.EnergyEfficient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Monitor extends ComputerAppliance implements EnergyEfficient {
  private final String resolution;       // Разрешение экрана (например, "1920x1080")
  private final int refreshRate;         // Частота обновления (Гц)
  private final EnergyEfficiency energyEfficiency; // Класс энергоэффективности
  private final int powerConsumption;    // Потребляемая мощность (Вт)

  @Override
  public int calculatePowerConsumption() {
    return powerConsumption;
  }

  @Override
  public EnergyEfficiency getEnergyEfficiency() {
    return energyEfficiency;
  }
}
