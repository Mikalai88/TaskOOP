package by.mikhalachkin.electroshop.service.computers;

import by.mikhalachkin.electroshop.model.computers.Tablet;
import by.mikhalachkin.electroshop.model.computers.OperatingSystemTablet;
import by.mikhalachkin.electroshop.service.ApplianceService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TabletService {
  private final ApplianceService applianceService;

  public TabletService(ApplianceService applianceService) {
    this.applianceService = applianceService;
  }

  // Добавить планшет
  public void addTablet(Tablet tablet) {
    applianceService.addAppliance(tablet);
  }

  // Удалить планшет
  public void removeTablet(Tablet tablet) {
    applianceService.removeAppliance(tablet);
  }

  // Получить список всех планшетов
  public List<Tablet> getAllTablets() {
    return applianceService.getAllAppliances().stream()
            .filter(appliance -> appliance instanceof Tablet)
            .map(appliance -> (Tablet) appliance)
            .collect(Collectors.toList());
  }

  // Найти планшеты по операционной системе
  public List<Tablet> findByOperatingSystem(OperatingSystemTablet operatingSystem) {
    return getAllTablets().stream()
            .filter(tablet -> tablet.getOperatingSystem() == operatingSystem)
            .collect(Collectors.toList());
  }

  // Найти планшеты с минимальным объемом оперативной памяти
  public List<Tablet> findByMinRam(int minRam) {
    return getAllTablets().stream()
            .filter(tablet -> tablet.getRam() >= minRam)
            .collect(Collectors.toList());
  }

  // Найти планшеты с конкретным процессором
  public List<Tablet> findByProcessor(String processor) {
    return getAllTablets().stream()
            .filter(tablet -> tablet.getProcessor().equalsIgnoreCase(processor))
            .collect(Collectors.toList());
  }

  // Сортировать планшеты по оперативной памяти
  public List<Tablet> sortByRam() {
    return getAllTablets().stream()
            .sorted(Comparator.comparingInt(Tablet::getRam))
            .collect(Collectors.toList());
  }

  // Сортировать планшеты по емкости батареи
  public List<Tablet> sortByBatteryCapacity() {
    return getAllTablets().stream()
            .sorted(Comparator.comparingDouble(Tablet::getBatteryCapacity))
            .collect(Collectors.toList());
  }

  // Сортировать планшеты по весу
  public List<Tablet> sortByWeight() {
    return getAllTablets().stream()
            .sorted(Comparator.comparingDouble(Tablet::getWeight))
            .collect(Collectors.toList());
  }

  // Сортировать планшеты по цене
  public List<Tablet> sortByPrice() {
    return getAllTablets().stream()
            .sorted(Comparator.comparingDouble(Tablet::getPrice))
            .collect(Collectors.toList());
  }

  // Фильтровать планшеты по диапазону цен
  public List<Tablet> filterByPriceRange(double minPrice, double maxPrice) {
    return getAllTablets().stream()
            .filter(tablet -> tablet.getPrice() >= minPrice && tablet.getPrice() <= maxPrice)
            .collect(Collectors.toList());
  }

  // Подсчитать общее количество планшетов
  public int countTablets() {
    return getAllTablets().size();
  }
}
