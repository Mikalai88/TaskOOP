package by.mikhalachkin.electroshop.model.cleaning;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class VacuumCleaner extends CleaningAppliance {
  private final VacuumCleanerType type;  // Тип пылесоса
  private final int suctionPower;        // Мощность всасывания (Вт)
  private final int dustCapacity;        // Объем пылесборника (литры)
  private final boolean isCordless;      // Беспроводной или проводной
}
