package by.mikhalachkin.electroshop.service.clothingcare;

import by.mikhalachkin.electroshop.model.clothingcare.Dryer;
import by.mikhalachkin.electroshop.model.EnergyEfficiency;
import by.mikhalachkin.electroshop.service.ApplianceService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DryerService {
  private final ApplianceService applianceService;

  public DryerService(ApplianceService applianceService) {
    this.applianceService = applianceService;
  }

  // Добавить сушильную машину
  public void addDryer(Dryer dryer) {
    applianceService.addAppliance(dryer);
  }

  // Удалить сушильную машину
  public void removeDryer(Dryer dryer) {
    applianceService.removeAppliance(dryer);
  }

  // Получить список всех сушильных машин
  public List<Dryer> getAllDryers() {
    return applianceService.getAllAppliances().stream()
            .filter(appliance -> appliance instanceof Dryer)
            .map(appliance -> (Dryer) appliance)
            .collect(Collectors.toList());
  }

  // Найти сушильные машины по минимальной вместимости барабана
  public List<Dryer> findByMinDrumCapacity(int minCapacity) {
    return getAllDryers().stream()
            .filter(d -> d.getDrumCapacity() >= minCapacity)
            .collect(Collectors.toList());
  }

  // Найти сушильные машины по типу сушки
  public List<Dryer> findByDryingType(String dryingType) {
    return getAllDryers().stream()
            .filter(d -> d.getDryingType().equalsIgnoreCase(dryingType))
            .collect(Collectors.toList());
  }

  // Сортировать по вместимости барабана
  public List<Dryer> sortByDrumCapacity() {
    return getAllDryers().stream()
            .sorted(Comparator.comparingInt(Dryer::getDrumCapacity))
            .collect(Collectors.toList());
  }

  // Сортировать по цене
  public List<Dryer> sortByPrice() {
    return getAllDryers().stream()
            .sorted(Comparator.comparingDouble(Dryer::getPrice))
            .collect(Collectors.toList());
  }

  // Фильтровать по диапазону цен
  public List<Dryer> filterByPriceRange(double minPrice, double maxPrice) {
    return getAllDryers().stream()
            .filter(d -> d.getPrice() >= minPrice && d.getPrice() <= maxPrice)
            .collect(Collectors.toList());
  }

  // Подсчитать общее количество сушильных машин
  public int countDryers() {
    return getAllDryers().size();
  }

  // Найти сушильные машины по классу энергоэффективности
  public List<Dryer> findByEnergyEfficiency(EnergyEfficiency energyEfficiency) {
    return getAllDryers().stream()
            .filter(d -> d.getEnergyEfficiency() == energyEfficiency)
            .collect(Collectors.toList());
  }

  // Сортировать по классу энергоэффективности (например, A++, A+)
  public List<Dryer> sortByEnergyEfficiency() {
    return getAllDryers().stream()
            .sorted(Comparator.comparing(Dryer::getEnergyEfficiency))
            .collect(Collectors.toList());
  }

  // Сортировать по энергозатратности (потребляемая мощность)
  public List<Dryer> sortByPowerConsumption() {
    return getAllDryers().stream()
            .sorted(Comparator.comparingInt(Dryer::calculatePowerConsumption))
            .collect(Collectors.toList());
  }
}
