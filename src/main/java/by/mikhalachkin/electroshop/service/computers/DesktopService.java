package by.mikhalachkin.electroshop.service.computers;

import by.mikhalachkin.electroshop.model.computers.Desktop;
import by.mikhalachkin.electroshop.model.computers.OperatingSystemDesktopLaptop;
import by.mikhalachkin.electroshop.service.ApplianceService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DesktopService {
  private final ApplianceService applianceService;

  public DesktopService(ApplianceService applianceService) {
    this.applianceService = applianceService;
  }

  // Добавить десктоп
  public void addDesktop(Desktop desktop) {
    applianceService.addAppliance(desktop);
  }

  // Удалить десктоп
  public void removeDesktop(Desktop desktop) {
    applianceService.removeAppliance(desktop);
  }

  // Получить список всех десктопов
  public List<Desktop> getAllDesktops() {
    return applianceService.getAllAppliances().stream()
            .filter(appliance -> appliance instanceof Desktop)
            .map(appliance -> (Desktop) appliance)
            .collect(Collectors.toList());
  }

  // Найти десктопы по операционной системе
  public List<Desktop> findByOperatingSystem(OperatingSystemDesktopLaptop operatingSystem) {
    return getAllDesktops().stream()
            .filter(desktop -> desktop.getOperatingSystem() == operatingSystem)
            .collect(Collectors.toList());
  }

  // Найти игровые десктопы
  public List<Desktop> findGamingDesktops() {
    return getAllDesktops().stream()
            .filter(Desktop::isGaming)
            .collect(Collectors.toList());
  }

  // Найти десктопы с минимальным объемом оперативной памяти
  public List<Desktop> findByMinRam(int minRam) {
    return getAllDesktops().stream()
            .filter(desktop -> desktop.getRam() >= minRam)
            .collect(Collectors.toList());
  }

  // Найти десктопы с конкретным процессором
  public List<Desktop> findByProcessor(String processor) {
    return getAllDesktops().stream()
            .filter(desktop -> desktop.getProcessor().equalsIgnoreCase(processor))
            .collect(Collectors.toList());
  }

  // Сортировать десктопы по оперативной памяти
  public List<Desktop> sortByRam() {
    return getAllDesktops().stream()
            .sorted(Comparator.comparingInt(Desktop::getRam))
            .collect(Collectors.toList());
  }

  // Сортировать десктопы по цене
  public List<Desktop> sortByPrice() {
    return getAllDesktops().stream()
            .sorted(Comparator.comparingDouble(Desktop::getPrice))
            .collect(Collectors.toList());
  }

  // Фильтровать десктопы по диапазону цен
  public List<Desktop> filterByPriceRange(double minPrice, double maxPrice) {
    return getAllDesktops().stream()
            .filter(desktop -> desktop.getPrice() >= minPrice && desktop.getPrice() <= maxPrice)
            .collect(Collectors.toList());
  }

  // Подсчитать общее количество десктопов
  public int countDesktops() {
    return getAllDesktops().size();
  }
}
