package by.mikhalachkin.electroshop.service;

import by.mikhalachkin.electroshop.model.Appliance;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ApplianceService {
  private final List<Appliance> appliances = new ArrayList<>();

  // Добавить устройство
  public void addAppliance(Appliance appliance) {
    appliances.add(appliance);
  }

  // Удалить устройство
  public void removeAppliance(Appliance appliance) {
    appliances.remove(appliance);
  }

  // Получить все устройства
  public List<Appliance> getAllAppliances() {
    return new ArrayList<>(appliances);
  }

  // Найти устройства по источнику питания
  public List<Appliance> findByPowerSource(String powerSource) {
    return appliances.stream()
            .filter(a -> a.getPowerSource().equalsIgnoreCase(powerSource))
            .collect(Collectors.toList());
  }

  // Найти устройства по диапазону цен
  public List<Appliance> filterByPriceRange(double minPrice, double maxPrice) {
    return appliances.stream()
            .filter(a -> a.getPrice() >= minPrice && a.getPrice() <= maxPrice)
            .collect(Collectors.toList());
  }

  // Сортировать устройства по цене
  public void sortByPrice() {
    appliances.sort(Comparator.comparingDouble(Appliance::getPrice));
  }

  // Найти устройства по бренду
  public List<Appliance> findByBrand(String brand) {
    return appliances.stream()
            .filter(a -> a.getBrand().equalsIgnoreCase(brand))
            .collect(Collectors.toList());
  }

  // Найти устройства по имени
  public List<Appliance> findByName(String name) {
    return appliances.stream()
            .filter(a -> a.getName().equalsIgnoreCase(name))
            .collect(Collectors.toList());
  }

  // Подсчитать общее количество устройств
  public int countAppliances() {
    return appliances.size();
  }

  // Подсчитать общую стоимость всех устройств
  public double calculateTotalCost() {
    return appliances.stream()
            .mapToDouble(Appliance::getPrice)
            .sum();
  }
}
