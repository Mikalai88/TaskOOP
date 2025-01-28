package by.mikhalachkin.electroshop.service.clothingcare;

import by.mikhalachkin.electroshop.model.EnergyEfficiency;
import by.mikhalachkin.electroshop.model.clothingcare.WashingMachine;
import by.mikhalachkin.electroshop.service.ApplianceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WashingMachineServiceTest {

  @Mock
  private ApplianceService applianceService;

  private WashingMachineService washingMachineService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    washingMachineService = new WashingMachineService(applianceService);
  }

  @Test
  void testAddWashingMachine() {
    WashingMachine washingMachine = createWashingMachine();
    washingMachineService.addWashingMachine(washingMachine);
    verify(applianceService, times(1)).addAppliance(washingMachine);
  }

  @Test
  void testRemoveWashingMachine() {
    WashingMachine washingMachine = createWashingMachine();
    washingMachineService.removeWashingMachine(washingMachine);
    verify(applianceService, times(1)).removeAppliance(washingMachine);
  }

  @Test
  void testGetAllWashingMachines() {
    WashingMachine washingMachine = createWashingMachine();
    when(applianceService.getAllAppliances()).thenReturn(List.of(washingMachine));

    List<WashingMachine> result = washingMachineService.getAllWashingMachines();
    assertEquals(1, result.size());
    assertEquals(washingMachine, result.get(0));
  }

  @Test
  void testFindByMinDrumCapacity() {
    WashingMachine smallDrum = createWashingMachineWithDrumCapacity(5);
    WashingMachine largeDrum = createWashingMachineWithDrumCapacity(10);

    when(applianceService.getAllAppliances()).thenReturn(List.of(smallDrum, largeDrum));

    List<WashingMachine> result = washingMachineService.findByMinDrumCapacity(6);
    assertEquals(1, result.size());
    assertEquals(10, result.get(0).getDrumCapacity());
  }

  @Test
  void testFindByMinSpinSpeed() {
    WashingMachine slowSpin = createWashingMachineWithSpinSpeed(800);
    WashingMachine fastSpin = createWashingMachineWithSpinSpeed(1200);

    when(applianceService.getAllAppliances()).thenReturn(List.of(slowSpin, fastSpin));

    List<WashingMachine> result = washingMachineService.findByMinSpinSpeed(1000);
    assertEquals(1, result.size());
    assertEquals(1200, result.get(0).getSpinSpeed());
  }

  @Test
  void testSortByDrumCapacity() {
    WashingMachine smallDrum = createWashingMachineWithDrumCapacity(5);
    WashingMachine largeDrum = createWashingMachineWithDrumCapacity(10);

    when(applianceService.getAllAppliances()).thenReturn(List.of(largeDrum, smallDrum));

    List<WashingMachine> result = washingMachineService.sortByDrumCapacity();
    assertEquals(5, result.get(0).getDrumCapacity());
    assertEquals(10, result.get(1).getDrumCapacity());
  }

  @Test
  void testSortBySpinSpeed() {
    WashingMachine slowSpin = createWashingMachineWithSpinSpeed(800);
    WashingMachine fastSpin = createWashingMachineWithSpinSpeed(1200);

    when(applianceService.getAllAppliances()).thenReturn(List.of(fastSpin, slowSpin));

    List<WashingMachine> result = washingMachineService.sortBySpinSpeed();
    assertEquals(800, result.get(0).getSpinSpeed());
    assertEquals(1200, result.get(1).getSpinSpeed());
  }

  @Test
  void testSortByPrice() {
    WashingMachine cheapMachine = createWashingMachineWithPrice(500.0);
    WashingMachine expensiveMachine = createWashingMachineWithPrice(1000.0);

    when(applianceService.getAllAppliances()).thenReturn(List.of(expensiveMachine, cheapMachine));

    List<WashingMachine> result = washingMachineService.sortByPrice();
    assertEquals(500.0, result.get(0).getPrice());
    assertEquals(1000.0, result.get(1).getPrice());
  }

  @Test
  void testFindByEnergyEfficiency() {
    WashingMachine washingMachine = createWashingMachine();
    when(applianceService.getAllAppliances()).thenReturn(List.of(washingMachine));

    List<WashingMachine> result = washingMachineService.findByEnergyEfficiency(EnergyEfficiency.A_PLUS);
    assertEquals(1, result.size());
    assertEquals(EnergyEfficiency.A_PLUS, result.get(0).getEnergyEfficiency());
  }

  @Test
  void testCountWashingMachines() {
    WashingMachine washingMachine1 = createWashingMachine();
    WashingMachine washingMachine2 = createWashingMachine();

    when(applianceService.getAllAppliances()).thenReturn(List.of(washingMachine1, washingMachine2));

    int count = washingMachineService.countWashingMachines();
    assertEquals(2, count);
  }

  // Вспомогательные методы для создания объектов WashingMachine

  private WashingMachine createWashingMachine() {
    return WashingMachine.builder()
            .name("Standard Washer")
            .brand("WasherBrand")
            .price(800.0)
            .powerSource("Electric")
            .energyEfficiency(EnergyEfficiency.A_PLUS)
            .powerConsumption(1500)
            .drumCapacity(7)
            .spinSpeed(1000)
            .build();
  }

  private WashingMachine createWashingMachineWithDrumCapacity(int drumCapacity) {
    return WashingMachine.builder()
            .name("Drum Washer")
            .brand("DrumBrand")
            .price(900.0)
            .powerSource("Electric")
            .energyEfficiency(EnergyEfficiency.A)
            .powerConsumption(1400)
            .drumCapacity(drumCapacity)
            .spinSpeed(1000)
            .build();
  }

  private WashingMachine createWashingMachineWithSpinSpeed(int spinSpeed) {
    return WashingMachine.builder()
            .name("Spin Washer")
            .brand("SpinBrand")
            .price(1000.0)
            .powerSource("Electric")
            .energyEfficiency(EnergyEfficiency.B)
            .powerConsumption(1600)
            .drumCapacity(8)
            .spinSpeed(spinSpeed)
            .build();
  }

  private WashingMachine createWashingMachineWithPrice(double price) {
    return WashingMachine.builder()
            .name("Price Washer")
            .brand("PriceBrand")
            .price(price)
            .powerSource("Electric")
            .energyEfficiency(EnergyEfficiency.C)
            .powerConsumption(1200)
            .drumCapacity(6)
            .spinSpeed(800)
            .build();
  }
}
