package by.mikhalachkin.electroshop.service.computers;

import by.mikhalachkin.electroshop.model.computers.Laptop;
import by.mikhalachkin.electroshop.model.computers.OperatingSystemDesktopLaptop;
import by.mikhalachkin.electroshop.service.ApplianceService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LaptopService {
  private final ApplianceService applianceService;

  public LaptopService(ApplianceService applianceService) {
    this.applianceService = applianceService;
  }

  // Добавить ноутбук
  public void addLaptop(Laptop laptop) {
    applianceService.addAppliance(laptop);
  }

  // Удалить ноутбук
  public void removeLaptop(Laptop laptop) {
    applianceService.removeAppliance(laptop);
  }

  // Получить список всех ноутбуков
  public List<Laptop> getAllLaptops() {
    return applianceService.getAllAppliances().stream()
            .filter(appliance -> appliance instanceof Laptop)
            .map(appliance -> (Laptop) appliance)
            .collect(Collectors.toList());
  }

  // Найти ноутбуки по операционной системе
  public List<Laptop> findByOperatingSystem(OperatingSystemDesktopLaptop operatingSystem) {
    return getAllLaptops().stream()
            .filter(laptop -> laptop.getOperatingSystem() == operatingSystem)
            .collect(Collectors.toList());
  }

  // Найти игровые ноутбуки
  public List<Laptop> findGamingLaptops() {
    return getAllLaptops().stream()
            .filter(Laptop::isGaming)
            .collect(Collectors.toList());
  }

  // Найти ноутбуки с минимальным объемом оперативной памяти
  public List<Laptop> findByMinRam(int minRam) {
    return getAllLaptops().stream()
            .filter(laptop -> laptop.getRam() >= minRam)
            .collect(Collectors.toList());
  }

  // Найти ноутбуки с конкретным процессором
  public List<Laptop> findByProcessor(String processor) {
    return getAllLaptops().stream()
            .filter(laptop -> laptop.getProcessor().equalsIgnoreCase(processor))
            .collect(Collectors.toList());
  }

  // Сортировать ноутбуки по оперативной памяти
  public List<Laptop> sortByRam() {
    return getAllLaptops().stream()
            .sorted(Comparator.comparingInt(Laptop::getRam))
            .collect(Collectors.toList());
  }

  // Сортировать ноутбуки по емкости батареи
  public List<Laptop> sortByBatteryCapacity() {
    return getAllLaptops().stream()
            .sorted(Comparator.comparingDouble(Laptop::getBatteryCapacity))
            .collect(Collectors.toList());
  }

  // Сортировать ноутбуки по цене
  public List<Laptop> sortByPrice() {
    return getAllLaptops().stream()
            .sorted(Comparator.comparingDouble(Laptop::getPrice))
            .collect(Collectors.toList());
  }

  // Фильтровать ноутбуки по диапазону цен
  public List<Laptop> filterByPriceRange(double minPrice, double maxPrice) {
    return getAllLaptops().stream()
            .filter(laptop -> laptop.getPrice() >= minPrice && laptop.getPrice() <= maxPrice)
            .collect(Collectors.toList());
  }

  // Подсчитать общее количество ноутбуков
  public int countLaptops() {
    return getAllLaptops().size();
  }
}
