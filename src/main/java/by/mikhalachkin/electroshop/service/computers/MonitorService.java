package by.mikhalachkin.electroshop.service.computers;

import by.mikhalachkin.electroshop.model.computers.Monitor;
import by.mikhalachkin.electroshop.model.EnergyEfficiency;
import by.mikhalachkin.electroshop.service.ApplianceService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MonitorService {
  private final ApplianceService applianceService;

  public MonitorService(ApplianceService applianceService) {
    this.applianceService = applianceService;
  }

  // Добавить монитор
  public void addMonitor(Monitor monitor) {
    applianceService.addAppliance(monitor);
  }

  // Удалить монитор
  public void removeMonitor(Monitor monitor) {
    applianceService.removeAppliance(monitor);
  }

  // Получить список всех мониторов
  public List<Monitor> getAllMonitors() {
    return applianceService.getAllAppliances().stream()
            .filter(appliance -> appliance instanceof Monitor)
            .map(appliance -> (Monitor) appliance)
            .collect(Collectors.toList());
  }

  // Найти мониторы по разрешению
  public List<Monitor> findByResolution(String resolution) {
    return getAllMonitors().stream()
            .filter(monitor -> monitor.getResolution().equalsIgnoreCase(resolution))
            .collect(Collectors.toList());
  }

  // Найти мониторы по минимальной частоте обновления
  public List<Monitor> findByMinRefreshRate(int minRefreshRate) {
    return getAllMonitors().stream()
            .filter(monitor -> monitor.getRefreshRate() >= minRefreshRate)
            .collect(Collectors.toList());
  }

  // Найти мониторы по классу энергоэффективности
  public List<Monitor> findByEnergyEfficiency(EnergyEfficiency energyEfficiency) {
    return getAllMonitors().stream()
            .filter(monitor -> monitor.getEnergyEfficiency() == energyEfficiency)
            .collect(Collectors.toList());
  }

  // Сортировать мониторы по частоте обновления
  public List<Monitor> sortByRefreshRate() {
    return getAllMonitors().stream()
            .sorted(Comparator.comparingInt(Monitor::getRefreshRate))
            .collect(Collectors.toList());
  }

  // Сортировать мониторы по цене
  public List<Monitor> sortByPrice() {
    return getAllMonitors().stream()
            .sorted(Comparator.comparingDouble(Monitor::getPrice))
            .collect(Collectors.toList());
  }

  // Сортировать мониторы по классу энергоэффективности
  public List<Monitor> sortByEnergyEfficiency() {
    return getAllMonitors().stream()
            .sorted(Comparator.comparing(Monitor::getEnergyEfficiency))
            .collect(Collectors.toList());
  }

  // Сортировать мониторы по энергозатратности
  public List<Monitor> sortByPowerConsumption() {
    return getAllMonitors().stream()
            .sorted(Comparator.comparingInt(Monitor::calculatePowerConsumption))
            .collect(Collectors.toList());
  }

  // Фильтровать мониторы по диапазону цен
  public List<Monitor> filterByPriceRange(double minPrice, double maxPrice) {
    return getAllMonitors().stream()
            .filter(monitor -> monitor.getPrice() >= minPrice && monitor.getPrice() <= maxPrice)
            .collect(Collectors.toList());
  }

  // Подсчитать общее количество мониторов
  public int countMonitors() {
    return getAllMonitors().size();
  }
}
