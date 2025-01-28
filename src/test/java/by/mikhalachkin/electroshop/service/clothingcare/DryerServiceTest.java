package by.mikhalachkin.electroshop.service.clothingcare;

import by.mikhalachkin.electroshop.model.EnergyEfficiency;
import by.mikhalachkin.electroshop.model.clothingcare.Dryer;
import by.mikhalachkin.electroshop.service.ApplianceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DryerServiceTest {

  @Mock
  private ApplianceService applianceService;

  private DryerService dryerService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    dryerService = new DryerService(applianceService);
  }

  @Test
  void testAddDryer() {
    Dryer dryer = createDryer();
    dryerService.addDryer(dryer);
    verify(applianceService, times(1)).addAppliance(dryer);
  }

  @Test
  void testRemoveDryer() {
    Dryer dryer = createDryer();
    dryerService.removeDryer(dryer);
    verify(applianceService, times(1)).removeAppliance(dryer);
  }

  @Test
  void testGetAllDryers() {
    Dryer dryer = createDryer();
    when(applianceService.getAllAppliances()).thenReturn(List.of(dryer));

    List<Dryer> result = dryerService.getAllDryers();
    assertEquals(1, result.size());
    assertEquals(dryer, result.get(0));
  }

  @Test
  void testFindByMinDrumCapacity() {
    Dryer dryer1 = createDryerWithCapacity(7);
    Dryer dryer2 = createDryerWithCapacity(10);

    when(applianceService.getAllAppliances()).thenReturn(List.of(dryer1, dryer2));

    List<Dryer> result = dryerService.findByMinDrumCapacity(8);
    assertEquals(1, result.size());
    assertEquals(dryer2, result.get(0));
  }

  @Test
  void testFindByDryingType() {
    Dryer heatPumpDryer = createDryerWithType("Heat Pump");
    Dryer condenserDryer = createDryerWithType("Condenser");

    when(applianceService.getAllAppliances()).thenReturn(List.of(heatPumpDryer, condenserDryer));

    List<Dryer> result = dryerService.findByDryingType("Heat Pump");
    assertEquals(1, result.size());
    assertEquals(heatPumpDryer, result.get(0));
  }

  @Test
  void testSortByDrumCapacity() {
    Dryer dryer1 = createDryerWithCapacity(7);
    Dryer dryer2 = createDryerWithCapacity(10);

    when(applianceService.getAllAppliances()).thenReturn(List.of(dryer2, dryer1));

    List<Dryer> result = dryerService.sortByDrumCapacity();
    assertEquals(7, result.get(0).getDrumCapacity());
    assertEquals(10, result.get(1).getDrumCapacity());
  }

  @Test
  void testSortByPrice() {
    Dryer dryer1 = createDryerWithPrice(399.99);
    Dryer dryer2 = createDryerWithPrice(499.99);

    when(applianceService.getAllAppliances()).thenReturn(List.of(dryer2, dryer1));

    List<Dryer> result = dryerService.sortByPrice();
    assertEquals(399.99, result.get(0).getPrice());
    assertEquals(499.99, result.get(1).getPrice());
  }

  @Test
  void testFindByEnergyEfficiency() {
    Dryer dryer = createDryer();
    when(applianceService.getAllAppliances()).thenReturn(List.of(dryer));

    List<Dryer> result = dryerService.findByEnergyEfficiency(EnergyEfficiency.A_PLUS);
    assertEquals(1, result.size());
    assertEquals(dryer, result.get(0));
  }

  // Вспомогательные методы для создания объектов Dryer

  private Dryer createDryer() {
    return Dryer.builder()
            .name("Basic Dryer")
            .brand("DryBrand")
            .price(399.99)
            .powerSource("Electric")
            .energyEfficiency(EnergyEfficiency.A_PLUS)
            .powerConsumption(1500)
            .drumCapacity(8)
            .dryingType("Heat Pump")
            .build();
  }

  private Dryer createDryerWithCapacity(int capacity) {
    return Dryer.builder()
            .name("Capacity Dryer")
            .brand("DryBrand")
            .price(399.99)
            .powerSource("Electric")
            .energyEfficiency(EnergyEfficiency.A)
            .powerConsumption(1500)
            .drumCapacity(capacity)
            .dryingType("Condenser")
            .build();
  }

  private Dryer createDryerWithType(String type) {
    return Dryer.builder()
            .name("Type Dryer")
            .brand("DryBrand")
            .price(399.99)
            .powerSource("Electric")
            .energyEfficiency(EnergyEfficiency.A_PLUS)
            .powerConsumption(1500)
            .drumCapacity(7)
            .dryingType(type)
            .build();
  }

  private Dryer createDryerWithPrice(double price) {
    return Dryer.builder()
            .name("Price Dryer")
            .brand("DryBrand")
            .price(price)
            .powerSource("Electric")
            .energyEfficiency(EnergyEfficiency.B)
            .powerConsumption(1500)
            .drumCapacity(7)
            .dryingType("Condenser")
            .build();
  }
}
