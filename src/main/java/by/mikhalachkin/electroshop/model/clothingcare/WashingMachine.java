package by.mikhalachkin.electroshop.model.clothingcare;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class WashingMachine extends ClothingCareAppliance {
  private final int drumCapacity; // Вместимость барабана (кг)
  private final int spinSpeed;    // Скорость отжима (об/мин)
}
