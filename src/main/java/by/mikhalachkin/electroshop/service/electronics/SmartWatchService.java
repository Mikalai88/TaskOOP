package by.mikhalachkin.electroshop.service.electronics;

import by.mikhalachkin.electroshop.model.electronics.SmartWatch;
import by.mikhalachkin.electroshop.service.ApplianceService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SmartWatchService {
  private final ApplianceService applianceService;

  public SmartWatchService(ApplianceService applianceService) {
    this.applianceService = applianceService;
  }

  // Добавить умные часы
  public void addSmartWatch(SmartWatch smartWatch) {
    applianceService.addAppliance(smartWatch);
  }

  // Удалить умные часы
  public void removeSmartWatch(SmartWatch smartWatch) {
    applianceService.removeAppliance(smartWatch);
  }

  // Получить список всех умных часов
  public List<SmartWatch> getAllSmartWatches() {
    return applianceService.getAllAppliances().stream()
            .filter(appliance -> appliance instanceof SmartWatch)
            .map(appliance -> (SmartWatch) appliance)
            .collect(Collectors.toList());
  }

  // Найти умные часы с минимальным временем работы от батареи
  public List<SmartWatch> findByMinBatteryLife(int minBatteryLife) {
    return getAllSmartWatches().stream()
            .filter(smartWatch -> smartWatch.getBatteryLife() >= minBatteryLife)
            .collect(Collectors.toList());
  }

  // Найти водонепроницаемые умные часы
  public List<SmartWatch> findWaterResistant() {
    return getAllSmartWatches().stream()
            .filter(SmartWatch::isWaterResistant)
            .collect(Collectors.toList());
  }

  // Сортировать умные часы по времени работы от батареи
  public List<SmartWatch> sortByBatteryLife() {
    return getAllSmartWatches().stream()
            .sorted(Comparator.comparingInt(SmartWatch::getBatteryLife))
            .collect(Collectors.toList());
  }

  // Сортировать умные часы по цене
  public List<SmartWatch> sortByPrice() {
    return getAllSmartWatches().stream()
            .sorted(Comparator.comparingDouble(SmartWatch::getPrice))
            .collect(Collectors.toList());
  }

  // Фильтровать умные часы по диапазону цен
  public List<SmartWatch> filterByPriceRange(double minPrice, double maxPrice) {
    return getAllSmartWatches().stream()
            .filter(smartWatch -> smartWatch.getPrice() >= minPrice && smartWatch.getPrice() <= maxPrice)
            .collect(Collectors.toList());
  }

  // Подсчитать общее количество умных часов
  public int countSmartWatches() {
    return getAllSmartWatches().size();
  }
}
