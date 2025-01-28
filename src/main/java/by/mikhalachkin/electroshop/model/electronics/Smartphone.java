package by.mikhalachkin.electroshop.model.electronics;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Smartphone extends Electronics {
  private final int storage;             // Объем памяти (в ГБ)
  private final int ram;                 // Оперативная память (в ГБ)
  private final SmartphoneOS os;         // Операционная система (enum)
}
