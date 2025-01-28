package by.mikhalachkin.electroshop.service.clothingcare;

import by.mikhalachkin.electroshop.model.clothingcare.WashingMachine;
import by.mikhalachkin.electroshop.model.EnergyEfficiency;
import by.mikhalachkin.electroshop.service.ApplianceService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WashingMachineService {
  private final ApplianceService applianceService;

  public WashingMachineService(ApplianceService applianceService) {
    this.applianceService = applianceService;
  }

  // Добавить стиральную машину
  public void addWashingMachine(WashingMachine washingMachine) {
    applianceService.addAppliance(washingMachine);
  }

  // Удалить стиральную машину
  public void removeWashingMachine(WashingMachine washingMachine) {
    applianceService.removeAppliance(washingMachine);
  }

  // Получить список всех стиральных машин
  public List<WashingMachine> getAllWashingMachines() {
    return applianceService.getAllAppliances().stream()
            .filter(appliance -> appliance instanceof WashingMachine)
            .map(appliance -> (WashingMachine) appliance)
            .collect(Collectors.toList());
  }

  // Найти стиральные машины по минимальной вместимости барабана
  public List<WashingMachine> findByMinDrumCapacity(int minCapacity) {
    return getAllWashingMachines().stream()
            .filter(wm -> wm.getDrumCapacity() >= minCapacity)
            .collect(Collectors.toList());
  }

  // Найти стиральные машины по минимальной скорости отжима
  public List<WashingMachine> findByMinSpinSpeed(int minSpinSpeed) {
    return getAllWashingMachines().stream()
            .filter(wm -> wm.getSpinSpeed() >= minSpinSpeed)
            .collect(Collectors.toList());
  }

  // Сортировать стиральные машины по вместимости барабана
  public List<WashingMachine> sortByDrumCapacity() {
    return getAllWashingMachines().stream()
            .sorted(Comparator.comparingInt(WashingMachine::getDrumCapacity))
            .collect(Collectors.toList());
  }

  // Сортировать стиральные машины по скорости отжима
  public List<WashingMachine> sortBySpinSpeed() {
    return getAllWashingMachines().stream()
            .sorted(Comparator.comparingInt(WashingMachine::getSpinSpeed))
            .collect(Collectors.toList());
  }

  // Сортировать стиральные машины по цене
  public List<WashingMachine> sortByPrice() {
    return getAllWashingMachines().stream()
            .sorted(Comparator.comparingDouble(WashingMachine::getPrice))
            .collect(Collectors.toList());
  }

  // Фильтровать стиральные машины по диапазону цен
  public List<WashingMachine> filterByPriceRange(double minPrice, double maxPrice) {
    return getAllWashingMachines().stream()
            .filter(wm -> wm.getPrice() >= minPrice && wm.getPrice() <= maxPrice)
            .collect(Collectors.toList());
  }

  // Подсчитать общее количество стиральных машин
  public int countWashingMachines() {
    return getAllWashingMachines().size();
  }

  // Найти стиральные машины по классу энергоэффективности
  public List<WashingMachine> findByEnergyEfficiency(EnergyEfficiency energyEfficiency) {
    return getAllWashingMachines().stream()
            .filter(wm -> wm.getEnergyEfficiency() == energyEfficiency)
            .collect(Collectors.toList());
  }

  // Сортировать стиральные машины по классу энергоэффективности (например, A++, A+)
  public List<WashingMachine> sortByEnergyEfficiency() {
    return getAllWashingMachines().stream()
            .sorted(Comparator.comparing(WashingMachine::getEnergyEfficiency))
            .collect(Collectors.toList());
  }

  // Сортировать стиральные машины по энергозатратности (потребляемая мощность)
  public List<WashingMachine> sortByPowerConsumption() {
    return getAllWashingMachines().stream()
            .sorted(Comparator.comparingInt(WashingMachine::calculatePowerConsumption))
            .collect(Collectors.toList());
  }
}
