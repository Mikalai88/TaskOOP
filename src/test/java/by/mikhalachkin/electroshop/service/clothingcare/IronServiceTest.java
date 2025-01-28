package by.mikhalachkin.electroshop.service.clothingcare;

import by.mikhalachkin.electroshop.model.EnergyEfficiency;
import by.mikhalachkin.electroshop.model.clothingcare.Iron;
import by.mikhalachkin.electroshop.service.ApplianceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IronServiceTest {

  @Mock
  private ApplianceService applianceService;

  private IronService ironService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    ironService = new IronService(applianceService);
  }

  @Test
  void testAddIron() {
    Iron iron = createIron();
    ironService.addIron(iron);
    verify(applianceService, times(1)).addAppliance(iron);
  }

  @Test
  void testRemoveIron() {
    Iron iron = createIron();
    ironService.removeIron(iron);
    verify(applianceService, times(1)).removeAppliance(iron);
  }

  @Test
  void testGetAllIrons() {
    Iron iron = createIron();
    when(applianceService.getAllAppliances()).thenReturn(List.of(iron));

    List<Iron> result = ironService.getAllIrons();
    assertEquals(1, result.size());
    assertEquals(iron, result.get(0));
  }

  @Test
  void testFindWithSteamFunction() {
    Iron ironWithSteam = createIronWithSteam(true);
    Iron ironWithoutSteam = createIronWithSteam(false);

    when(applianceService.getAllAppliances()).thenReturn(List.of(ironWithSteam, ironWithoutSteam));

    List<Iron> result = ironService.findWithSteamFunction();
    assertEquals(1, result.size());
    assertTrue(result.get(0).hasSteam());
  }

  @Test
  void testFindByMinWaterTankCapacity() {
    Iron smallTankIron = createIronWithWaterTankCapacity(200);
    Iron largeTankIron = createIronWithWaterTankCapacity(400);

    when(applianceService.getAllAppliances()).thenReturn(List.of(smallTankIron, largeTankIron));

    List<Iron> result = ironService.findByMinWaterTankCapacity(300);
    assertEquals(1, result.size());
    assertEquals(400, result.get(0).getWaterTankCapacity());
  }

  @Test
  void testSortByWaterTankCapacity() {
    Iron smallTankIron = createIronWithWaterTankCapacity(200);
    Iron largeTankIron = createIronWithWaterTankCapacity(400);

    when(applianceService.getAllAppliances()).thenReturn(List.of(largeTankIron, smallTankIron));

    List<Iron> result = ironService.sortByWaterTankCapacity();
    assertEquals(200, result.get(0).getWaterTankCapacity());
    assertEquals(400, result.get(1).getWaterTankCapacity());
  }

  @Test
  void testSortByPrice() {
    Iron cheapIron = createIronWithPrice(50.0);
    Iron expensiveIron = createIronWithPrice(100.0);

    when(applianceService.getAllAppliances()).thenReturn(List.of(expensiveIron, cheapIron));

    List<Iron> result = ironService.sortByPrice();
    assertEquals(50.0, result.get(0).getPrice());
    assertEquals(100.0, result.get(1).getPrice());
  }

  @Test
  void testFindByEnergyEfficiency() {
    Iron iron = createIron();
    when(applianceService.getAllAppliances()).thenReturn(List.of(iron));

    List<Iron> result = ironService.findByEnergyEfficiency(EnergyEfficiency.A_PLUS);
    assertEquals(1, result.size());
    assertEquals(EnergyEfficiency.A_PLUS, result.get(0).getEnergyEfficiency());
  }

  @Test
  void testCountIrons() {
    Iron iron1 = createIron();
    Iron iron2 = createIron();

    when(applianceService.getAllAppliances()).thenReturn(List.of(iron1, iron2));

    int count = ironService.countIrons();
    assertEquals(2, count);
  }

  // Вспомогательные методы для создания объектов Iron

  private Iron createIron() {
    return Iron.builder()
            .name("Basic Iron")
            .brand("IronBrand")
            .price(75.99)
            .powerSource("Electric")
            .energyEfficiency(EnergyEfficiency.A_PLUS)
            .powerConsumption(1500)
            .hasSteam(true)
            .waterTankCapacity(300)
            .build();
  }

  private Iron createIronWithSteam(boolean hasSteam) {
    return Iron.builder()
            .name("Steam Iron")
            .brand("SteamBrand")
            .price(85.99)
            .powerSource("Electric")
            .energyEfficiency(EnergyEfficiency.A)
            .powerConsumption(1600)
            .hasSteam(hasSteam)
            .waterTankCapacity(350)
            .build();
  }

  private Iron createIronWithWaterTankCapacity(int capacity) {
    return Iron.builder()
            .name("Tank Iron")
            .brand("TankBrand")
            .price(99.99)
            .powerSource("Electric")
            .energyEfficiency(EnergyEfficiency.B)
            .powerConsumption(1400)
            .hasSteam(true)
            .waterTankCapacity(capacity)
            .build();
  }

  private Iron createIronWithPrice(double price) {
    return Iron.builder()
            .name("Priced Iron")
            .brand("PriceBrand")
            .price(price)
            .powerSource("Electric")
            .energyEfficiency(EnergyEfficiency.C)
            .powerConsumption(1300)
            .hasSteam(true)
            .waterTankCapacity(250)
            .build();
  }
}
