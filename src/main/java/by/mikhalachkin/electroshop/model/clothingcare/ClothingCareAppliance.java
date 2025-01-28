package by.mikhalachkin.electroshop.model.clothingcare;

import by.mikhalachkin.electroshop.model.Appliance;
import by.mikhalachkin.electroshop.model.EnergyEfficiency;
import by.mikhalachkin.electroshop.model.EnergyEfficient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public abstract class ClothingCareAppliance extends Appliance implements EnergyEfficient {
  private final EnergyEfficiency energyEfficiency;
  private final int powerConsumption; // Потребляемая мощность (Вт)

  @Override
  public EnergyEfficiency getEnergyEfficiency() {
    return energyEfficiency;
  }

  @Override
  public int calculatePowerConsumption() {
    return powerConsumption;
  }
}
