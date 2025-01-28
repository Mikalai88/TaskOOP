package by.mikhalachkin.electroshop.model.clothingcare;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Iron extends ClothingCareAppliance {
  private final boolean hasSteam;        // Наличие функции подачи пара
  private final int waterTankCapacity;  // Объем резервуара для воды (мл)

  public boolean hasSteam() {
    return hasSteam;
  }
}
