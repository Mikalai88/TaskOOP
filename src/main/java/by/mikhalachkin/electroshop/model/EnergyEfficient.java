package by.mikhalachkin.electroshop.model;

public interface EnergyEfficient {
  EnergyEfficiency getEnergyEfficiency(); // Метод для получения класса энергоэффективности
  int calculatePowerConsumption();        // Метод для расчета потребляемой мощности (Вт)
}
