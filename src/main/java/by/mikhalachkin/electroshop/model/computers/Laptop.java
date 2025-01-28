package by.mikhalachkin.electroshop.model.computers;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Laptop extends ComputerAppliance implements PC, Portable {
  private final OperatingSystemDesktopLaptop operatingSystem; // Операционная система
  private final int ram;                                     // Объем оперативной памяти (в ГБ)
  private final String processor;                           // Тип процессора
  private final String storage;                             // Тип и объем хранилища
  private final String gpuModel;                            // Модель видеокарты
  private final boolean gaming;                             // Является ли игровой системой
  private final double batteryCapacity;                     // Вместимость батареи
  private final double weight;                              // Вес (в кг)
}
