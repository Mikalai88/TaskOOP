package by.mikhalachkin.electroshop.model.clothingcare;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Dryer extends ClothingCareAppliance {
  private final int drumCapacity; // Вместимость барабана (кг)
  private final String dryingType; // Тип сушки
}
