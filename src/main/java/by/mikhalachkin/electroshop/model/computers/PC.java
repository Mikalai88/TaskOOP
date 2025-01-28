package by.mikhalachkin.electroshop.model.computers;

public interface PC {
  String getProcessor();
  int getRam(); // Оперативная память в ГБ
  String getStorage(); // Тип и объем памяти
  String getGpuModel();
  boolean isGaming(); // Игровой ли компьютер
}
