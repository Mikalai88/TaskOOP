package by.mikhalachkin.electroshop.service.clothingcare;

import by.mikhalachkin.electroshop.model.clothingcare.Iron;
import by.mikhalachkin.electroshop.model.EnergyEfficiency;
import by.mikhalachkin.electroshop.service.ApplianceService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IronService {
  private final ApplianceService applianceService;

  public IronService(ApplianceService applianceService) {
    this.applianceService = applianceService;
  }

  // Добавить утюг
  public void addIron(Iron iron) {
    applianceService.addAppliance(iron);
  }

  // Удалить утюг
  public void removeIron(Iron iron) {
    applianceService.removeAppliance(iron);
  }

  // Получить список всех утюгов
  public List<Iron> getAllIrons() {
    return applianceService.getAllAppliances().stream()
            .filter(appliance -> appliance instanceof Iron)
            .map(appliance -> (Iron) appliance)
            .collect(Collectors.toList());
  }

  // Найти утюги с функцией подачи пара
  public List<Iron> findWithSteamFunction() {
    return getAllIrons().stream()
            .filter(Iron::hasSteam)
            .collect(Collectors.toList());
  }

  // Найти утюги с минимальной емкостью резервуара для воды
  public List<Iron> findByMinWaterTankCapacity(int minCapacity) {
    return getAllIrons().stream()
            .filter(iron -> iron.getWaterTankCapacity() >= minCapacity)
            .collect(Collectors.toList());
  }

  // Сортировать утюги по объему резервуара для воды
  public List<Iron> sortByWaterTankCapacity() {
    return getAllIrons().stream()
            .sorted(Comparator.comparingInt(Iron::getWaterTankCapacity))
            .collect(Collectors.toList());
  }

  // Сортировать утюги по цене
  public List<Iron> sortByPrice() {
    return getAllIrons().stream()
            .sorted(Comparator.comparingDouble(Iron::getPrice))
            .collect(Collectors.toList());
  }

  // Фильтровать утюги по диапазону цен
  public List<Iron> filterByPriceRange(double minPrice, double maxPrice) {
    return getAllIrons().stream()
            .filter(iron -> iron.getPrice() >= minPrice && iron.getPrice() <= maxPrice)
            .collect(Collectors.toList());
  }

  // Подсчитать общее количество утюгов
  public int countIrons() {
    return getAllIrons().size();
  }

  // Найти утюги по классу энергоэффективности
  public List<Iron> findByEnergyEfficiency(EnergyEfficiency energyEfficiency) {
    return getAllIrons().stream()
            .filter(iron -> iron.getEnergyEfficiency() == energyEfficiency)
            .collect(Collectors.toList());
  }

  // Сортировать утюги по классу энергоэффективности (например, A++, A+)
  public List<Iron> sortByEnergyEfficiency() {
    return getAllIrons().stream()
            .sorted(Comparator.comparing(Iron::getEnergyEfficiency))
            .collect(Collectors.toList());
  }

  // Сортировать утюги по энергозатратности (потребляемая мощность)
  public List<Iron> sortByPowerConsumption() {
    return getAllIrons().stream()
            .sorted(Comparator.comparingInt(Iron::calculatePowerConsumption))
            .collect(Collectors.toList());
  }
}
