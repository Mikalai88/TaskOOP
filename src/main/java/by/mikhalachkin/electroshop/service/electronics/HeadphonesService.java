package by.mikhalachkin.electroshop.service.electronics;

import by.mikhalachkin.electroshop.model.electronics.Headphones;
import by.mikhalachkin.electroshop.service.ApplianceService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HeadphonesService {
  private final ApplianceService applianceService;

  public HeadphonesService(ApplianceService applianceService) {
    this.applianceService = applianceService;
  }

  // Добавить наушники
  public void addHeadphones(Headphones headphones) {
    applianceService.addAppliance(headphones);
  }

  // Удалить наушники
  public void removeHeadphones(Headphones headphones) {
    applianceService.removeAppliance(headphones);
  }

  // Получить список всех наушников
  public List<Headphones> getAllHeadphones() {
    return applianceService.getAllAppliances().stream()
            .filter(appliance -> appliance instanceof Headphones)
            .map(appliance -> (Headphones) appliance)
            .collect(Collectors.toList());
  }

  // Найти наушники с активным шумоподавлением
  public List<Headphones> findByNoiseCancellation() {
    return getAllHeadphones().stream()
            .filter(Headphones::hasNoiseCancellation)
            .collect(Collectors.toList());
  }

  // Найти наушники с минимальным временем работы от батареи
  public List<Headphones> findByMinBatteryLife(int minBatteryLife) {
    return getAllHeadphones().stream()
            .filter(headphones -> headphones.getBatteryLife() >= minBatteryLife)
            .collect(Collectors.toList());
  }

  // Сортировать наушники по времени работы от батареи
  public List<Headphones> sortByBatteryLife() {
    return getAllHeadphones().stream()
            .sorted(Comparator.comparingInt(Headphones::getBatteryLife))
            .collect(Collectors.toList());
  }

  // Сортировать наушники по цене
  public List<Headphones> sortByPrice() {
    return getAllHeadphones().stream()
            .sorted(Comparator.comparingDouble(Headphones::getPrice))
            .collect(Collectors.toList());
  }

  // Фильтровать наушники по диапазону цен
  public List<Headphones> filterByPriceRange(double minPrice, double maxPrice) {
    return getAllHeadphones().stream()
            .filter(headphones -> headphones.getPrice() >= minPrice && headphones.getPrice() <= maxPrice)
            .collect(Collectors.toList());
  }

  // Подсчитать общее количество наушников
  public int countHeadphones() {
    return getAllHeadphones().size();
  }
}
