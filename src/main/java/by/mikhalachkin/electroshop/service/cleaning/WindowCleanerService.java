package by.mikhalachkin.electroshop.service.cleaning;

import by.mikhalachkin.electroshop.model.cleaning.WindowCleaner;
import by.mikhalachkin.electroshop.model.EnergyEfficiency;
import by.mikhalachkin.electroshop.service.ApplianceService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WindowCleanerService {
  private final ApplianceService applianceService;

  public WindowCleanerService(ApplianceService applianceService) {
    this.applianceService = applianceService;
  }

  // Добавить очиститель окон
  public void addWindowCleaner(WindowCleaner windowCleaner) {
    applianceService.addAppliance(windowCleaner);
  }

  // Удалить очиститель окон
  public void removeWindowCleaner(WindowCleaner windowCleaner) {
    applianceService.removeAppliance(windowCleaner);
  }

  // Получить список всех очистителей окон
  public List<WindowCleaner> getAllWindowCleaners() {
    return applianceService.getAllAppliances().stream()
            .filter(appliance -> appliance instanceof WindowCleaner)
            .map(appliance -> (WindowCleaner) appliance)
            .collect(Collectors.toList());
  }

  // Найти роботы-очистители окон
  public List<WindowCleaner> findRoboticWindowCleaners() {
    return getAllWindowCleaners().stream()
            .filter(WindowCleaner::isRobot)
            .collect(Collectors.toList());
  }

  // Найти очистители с управлением через приложение
  public List<WindowCleaner> findSmartControlledWindowCleaners() {
    return getAllWindowCleaners().stream()
            .filter(WindowCleaner::isSupportsSmartControl)
            .collect(Collectors.toList());
  }

  // Найти очистители по минимальной емкости бака для воды
  public List<WindowCleaner> findByMinWaterTankCapacity(double minCapacity) {
    return getAllWindowCleaners().stream()
            .filter(w -> w.getWaterTankCapacity() >= minCapacity)
            .collect(Collectors.toList());
  }

  // Найти очистители с минимальным временем работы от батареи
  public List<WindowCleaner> findByMinBatteryLife(int minBatteryLife) {
    return getAllWindowCleaners().stream()
            .filter(w -> w.getBatteryLife() >= minBatteryLife)
            .collect(Collectors.toList());
  }

  // Сортировать по времени работы от батареи
  public List<WindowCleaner> sortByBatteryLife() {
    return getAllWindowCleaners().stream()
            .sorted(Comparator.comparingInt(WindowCleaner::getBatteryLife))
            .collect(Collectors.toList());
  }

  // Сортировать по цене
  public List<WindowCleaner> sortByPrice() {
    return getAllWindowCleaners().stream()
            .sorted(Comparator.comparingDouble(WindowCleaner::getPrice))
            .collect(Collectors.toList());
  }

  // Фильтровать по диапазону цен
  public List<WindowCleaner> filterByPriceRange(double minPrice, double maxPrice) {
    return getAllWindowCleaners().stream()
            .filter(w -> w.getPrice() >= minPrice && w.getPrice() <= maxPrice)
            .collect(Collectors.toList());
  }

  // Подсчитать общее количество очистителей окон
  public int countWindowCleaners() {
    return getAllWindowCleaners().size();
  }

  // Найти очистители окон по классу энергоэффективности
  public List<WindowCleaner> findByEnergyEfficiency(EnergyEfficiency energyEfficiency) {
    return getAllWindowCleaners().stream()
            .filter(w -> w.getEnergyEfficiency() == energyEfficiency)
            .collect(Collectors.toList());
  }

  // Сортировать по классу энергоэффективности (например, A++, A+)
  public List<WindowCleaner> sortByEnergyEfficiency() {
    return getAllWindowCleaners().stream()
            .sorted(Comparator.comparing(WindowCleaner::getEnergyEfficiency))
            .collect(Collectors.toList());
  }

  // Сортировать по энергозатратности (потребляемая мощность)
  public List<WindowCleaner> sortByPowerConsumption() {
    return getAllWindowCleaners().stream()
            .sorted(Comparator.comparingInt(WindowCleaner::calculatePowerConsumption))
            .collect(Collectors.toList());
  }
}
