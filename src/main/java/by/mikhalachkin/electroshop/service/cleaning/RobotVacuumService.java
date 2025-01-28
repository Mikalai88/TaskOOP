package by.mikhalachkin.electroshop.service.cleaning;

import by.mikhalachkin.electroshop.model.cleaning.RobotVacuum;
import by.mikhalachkin.electroshop.model.EnergyEfficiency;
import by.mikhalachkin.electroshop.service.ApplianceService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RobotVacuumService {
  private final ApplianceService applianceService;

  public RobotVacuumService(ApplianceService applianceService) {
    this.applianceService = applianceService;
  }

  // Добавить робот-пылесос
  public void addRobotVacuum(RobotVacuum robotVacuum) {
    applianceService.addAppliance(robotVacuum);
  }

  // Удалить робот-пылесос
  public void removeRobotVacuum(RobotVacuum robotVacuum) {
    applianceService.removeAppliance(robotVacuum);
  }

  // Получить список всех робот-пылесосов
  public List<RobotVacuum> getAllRobotVacuums() {
    return applianceService.getAllAppliances().stream()
            .filter(appliance -> appliance instanceof RobotVacuum)
            .map(appliance -> (RobotVacuum) appliance)
            .collect(Collectors.toList());
  }

  // Найти робот-пылесосы по емкости батареи (минимум)
  public List<RobotVacuum> findByBatteryCapacity(int minBatteryCapacity) {
    return getAllRobotVacuums().stream()
            .filter(v -> v.getBatteryCapacity() >= minBatteryCapacity)
            .collect(Collectors.toList());
  }

  // Найти робот-пылесосы, поддерживающие умное управление
  public List<RobotVacuum> findSmartControlled() {
    return getAllRobotVacuums().stream()
            .filter(RobotVacuum::isSupportsSmartControl)
            .collect(Collectors.toList());
  }

  // Сортировать по емкости батареи
  public List<RobotVacuum> sortByBatteryCapacity() {
    return getAllRobotVacuums().stream()
            .sorted(Comparator.comparingInt(RobotVacuum::getBatteryCapacity))
            .collect(Collectors.toList());
  }

  // Сортировать по цене
  public List<RobotVacuum> sortByPrice() {
    return getAllRobotVacuums().stream()
            .sorted(Comparator.comparingDouble(RobotVacuum::getPrice))
            .collect(Collectors.toList());
  }

  // Фильтровать по диапазону цен
  public List<RobotVacuum> filterByPriceRange(double minPrice, double maxPrice) {
    return getAllRobotVacuums().stream()
            .filter(v -> v.getPrice() >= minPrice && v.getPrice() <= maxPrice)
            .collect(Collectors.toList());
  }

  // Подсчитать общее количество робот-пылесосов
  public int countRobotVacuums() {
    return getAllRobotVacuums().size();
  }

  // Найти робот-пылесосы по классу энергоэффективности
  public List<RobotVacuum> findByEnergyEfficiency(EnergyEfficiency energyEfficiency) {
    return getAllRobotVacuums().stream()
            .filter(v -> v.getEnergyEfficiency() == energyEfficiency)
            .collect(Collectors.toList());
  }

  // Сортировать по классу энергоэффективности (например, A++, A+)
  public List<RobotVacuum> sortByEnergyEfficiency() {
    return getAllRobotVacuums().stream()
            .sorted(Comparator.comparing(RobotVacuum::getEnergyEfficiency))
            .collect(Collectors.toList());
  }

  // Сортировать по энергозатратности (потребляемая мощность)
  public List<RobotVacuum> sortByPowerConsumption() {
    return getAllRobotVacuums().stream()
            .sorted(Comparator.comparingInt(RobotVacuum::calculatePowerConsumption))
            .collect(Collectors.toList());
  }
}
