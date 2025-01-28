package by.mikhalachkin.electroshop.model.electronics;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class SmartWatch extends Electronics {
  private final int batteryLife;       // Время работы от батареи (в часах)
  private final boolean isWaterResistant; // Наличие водонепроницаемости
}
