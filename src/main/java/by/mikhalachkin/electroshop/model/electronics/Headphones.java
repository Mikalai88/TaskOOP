package by.mikhalachkin.electroshop.model.electronics;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Headphones extends Electronics {
  private final boolean hasNoiseCancellation; // Наличие активного шумоподавления
  private final int batteryLife;             // Время работы от батареи (в часах)

  public boolean hasNoiseCancellation() {
    return hasNoiseCancellation;
  }
}
