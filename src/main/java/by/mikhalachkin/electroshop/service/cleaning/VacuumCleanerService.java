package by.mikhalachkin.electroshop.service.cleaning;

import by.mikhalachkin.electroshop.model.cleaning.VacuumCleaner;
import by.mikhalachkin.electroshop.model.EnergyEfficiency;
import by.mikhalachkin.electroshop.model.cleaning.VacuumCleanerType;
import by.mikhalachkin.electroshop.service.ApplianceService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class VacuumCleanerService {
  private final ApplianceService applianceService;

  public VacuumCleanerService(ApplianceService applianceService) {
    this.applianceService = applianceService;
  }

  // Добавить пылесос
  public void addVacuumCleaner(VacuumCleaner vacuumCleaner) {
    applianceService.addAppliance(vacuumCleaner);
  }

  // Удалить пылесос
  public void removeVacuumCleaner(VacuumCleaner vacuumCleaner) {
    applianceService.removeAppliance(vacuumCleaner);
  }

  // Получить список всех пылесосов
  public List<VacuumCleaner> getAllVacuumCleaners() {
    return applianceService.getAllAppliances().stream()
            .filter(appliance -> appliance instanceof VacuumCleaner)
            .map(appliance -> (VacuumCleaner) appliance)
            .collect(Collectors.toList());
  }

  // Найти пылесосы по типу
  public List<VacuumCleaner> findByType(VacuumCleanerType type) {
    return getAllVacuumCleaners().stream()
            .filter(v -> v.getType() == type)
            .collect(Collectors.toList());
  }

  // Найти пылесосы по мощности всасывания (минимум)
  public List<VacuumCleaner> findBySuctionPower(int minSuctionPower) {
    return getAllVacuumCleaners().stream()
            .filter(v -> v.getSuctionPower() >= minSuctionPower)
            .collect(Collectors.toList());
  }

  // Найти беспроводные пылесосы
  public List<VacuumCleaner> findCordlessVacuumCleaners() {
    return getAllVacuumCleaners().stream()
            .filter(VacuumCleaner::isCordless)
            .collect(Collectors.toList());
  }

  // Сортировать по мощности всасывания
  public List<VacuumCleaner> sortBySuctionPower() {
    return getAllVacuumCleaners().stream()
            .sorted(Comparator.comparingInt(VacuumCleaner::getSuctionPower))
            .collect(Collectors.toList());
  }

  // Сортировать по цене
  public List<VacuumCleaner> sortByPrice() {
    return getAllVacuumCleaners().stream()
            .sorted(Comparator.comparingDouble(VacuumCleaner::getPrice))
            .collect(Collectors.toList());
  }

  // Фильтровать по диапазону цен
  public List<VacuumCleaner> filterByPriceRange(double minPrice, double maxPrice) {
    return getAllVacuumCleaners().stream()
            .filter(v -> v.getPrice() >= minPrice && v.getPrice() <= maxPrice)
            .collect(Collectors.toList());
  }

  // Подсчитать общее количество пылесосов
  public int countVacuumCleaners() {
    return getAllVacuumCleaners().size();
  }

  // Найти пылесосы по классу энергоэффективности
  public List<VacuumCleaner> findByEnergyEfficiency(EnergyEfficiency energyEfficiency) {
    return getAllVacuumCleaners().stream()
            .filter(v -> v.getEnergyEfficiency() == energyEfficiency)
            .collect(Collectors.toList());
  }

  // Сортировать по классу энергоэффективности (например, A++, A+)
  public List<VacuumCleaner> sortByEnergyEfficiency() {
    return getAllVacuumCleaners().stream()
            .sorted(Comparator.comparing(VacuumCleaner::getEnergyEfficiency))
            .collect(Collectors.toList());
  }

  // Сортировать по энергозатратности (потребляемая мощность)
  public List<VacuumCleaner> sortByPowerConsumption() {
    return getAllVacuumCleaners().stream()
            .sorted(Comparator.comparingInt(VacuumCleaner::calculatePowerConsumption))
            .collect(Collectors.toList());
  }
}
