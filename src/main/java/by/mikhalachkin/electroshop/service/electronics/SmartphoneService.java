package by.mikhalachkin.electroshop.service.electronics;

import by.mikhalachkin.electroshop.model.electronics.Smartphone;
import by.mikhalachkin.electroshop.model.electronics.SmartphoneOS;
import by.mikhalachkin.electroshop.service.ApplianceService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SmartphoneService {
  private final ApplianceService applianceService;

  public SmartphoneService(ApplianceService applianceService) {
    this.applianceService = applianceService;
  }

  // Добавить смартфон
  public void addSmartphone(Smartphone smartphone) {
    applianceService.addAppliance(smartphone);
  }

  // Удалить смартфон
  public void removeSmartphone(Smartphone smartphone) {
    applianceService.removeAppliance(smartphone);
  }

  // Получить список всех смартфонов
  public List<Smartphone> getAllSmartphones() {
    return applianceService.getAllAppliances().stream()
            .filter(appliance -> appliance instanceof Smartphone)
            .map(appliance -> (Smartphone) appliance)
            .collect(Collectors.toList());
  }

  // Найти смартфоны по операционной системе
  public List<Smartphone> findByOperatingSystem(SmartphoneOS os) {
    return getAllSmartphones().stream()
            .filter(smartphone -> smartphone.getOs() == os)
            .collect(Collectors.toList());
  }

  // Найти смартфоны с минимальным объемом памяти
  public List<Smartphone> findByMinStorage(int minStorage) {
    return getAllSmartphones().stream()
            .filter(smartphone -> smartphone.getStorage() >= minStorage)
            .collect(Collectors.toList());
  }

  // Найти смартфоны с минимальным объемом оперативной памяти
  public List<Smartphone> findByMinRam(int minRam) {
    return getAllSmartphones().stream()
            .filter(smartphone -> smartphone.getRam() >= minRam)
            .collect(Collectors.toList());
  }

  // Сортировать смартфоны по объему памяти
  public List<Smartphone> sortByStorage() {
    return getAllSmartphones().stream()
            .sorted(Comparator.comparingInt(Smartphone::getStorage))
            .collect(Collectors.toList());
  }

  // Сортировать смартфоны по оперативной памяти
  public List<Smartphone> sortByRam() {
    return getAllSmartphones().stream()
            .sorted(Comparator.comparingInt(Smartphone::getRam))
            .collect(Collectors.toList());
  }

  // Сортировать смартфоны по цене
  public List<Smartphone> sortByPrice() {
    return getAllSmartphones().stream()
            .sorted(Comparator.comparingDouble(Smartphone::getPrice))
            .collect(Collectors.toList());
  }

  // Фильтровать смартфоны по диапазону цен
  public List<Smartphone> filterByPriceRange(double minPrice, double maxPrice) {
    return getAllSmartphones().stream()
            .filter(smartphone -> smartphone.getPrice() >= minPrice && smartphone.getPrice() <= maxPrice)
            .collect(Collectors.toList());
  }

  // Подсчитать общее количество смартфонов
  public int countSmartphones() {
    return getAllSmartphones().size();
  }
}
