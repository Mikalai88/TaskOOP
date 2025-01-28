package by.mikhalachkin.electroshop.service.cleaning;

import by.mikhalachkin.electroshop.model.EnergyEfficiency;
import by.mikhalachkin.electroshop.model.cleaning.VacuumCleaner;
import by.mikhalachkin.electroshop.model.cleaning.VacuumCleanerType;
import by.mikhalachkin.electroshop.service.ApplianceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VacuumCleanerServiceTest {

  private ApplianceService applianceService;
  private VacuumCleanerService vacuumCleanerService;

  @BeforeEach
  void setUp() {
    applianceService = mock(ApplianceService.class);
    vacuumCleanerService = new VacuumCleanerService(applianceService);
  }

  @Test
  void testAddVacuumCleaner() {
    VacuumCleaner vacuumCleaner = createVacuumCleaner();
    vacuumCleanerService.addVacuumCleaner(vacuumCleaner);
    verify(applianceService, times(1)).addAppliance(vacuumCleaner);
  }

  @Test
  void testRemoveVacuumCleaner() {
    VacuumCleaner vacuumCleaner = createVacuumCleaner();
    vacuumCleanerService.removeVacuumCleaner(vacuumCleaner);
    verify(applianceService, times(1)).removeAppliance(vacuumCleaner);
  }

  @Test
  void testGetAllVacuumCleaners() {
    VacuumCleaner vacuumCleaner = createVacuumCleaner();
    when(applianceService.getAllAppliances()).thenReturn(Collections.singletonList(vacuumCleaner));

    List<VacuumCleaner> result = vacuumCleanerService.getAllVacuumCleaners();
    assertEquals(1, result.size());
    assertEquals(vacuumCleaner, result.get(0));
  }

  @Test
  void testFindByType() {
    VacuumCleaner vacuumCleaner = createVacuumCleaner();
    when(applianceService.getAllAppliances()).thenReturn(Collections.singletonList(vacuumCleaner));

    List<VacuumCleaner> result = vacuumCleanerService.findByType(VacuumCleanerType.UPRIGHT);
    assertEquals(1, result.size());
    assertEquals(vacuumCleaner, result.get(0));
  }

  @Test
  void testFindBySuctionPower() {
    VacuumCleaner vacuumCleaner = createVacuumCleaner();
    when(applianceService.getAllAppliances()).thenReturn(Collections.singletonList(vacuumCleaner));

    List<VacuumCleaner> result = vacuumCleanerService.findBySuctionPower(300);
    assertEquals(1, result.size());
    assertEquals(vacuumCleaner, result.get(0));
  }

  @Test
  void testFindCordlessVacuumCleaners() {
    VacuumCleaner vacuumCleaner = createVacuumCleaner();
    when(applianceService.getAllAppliances()).thenReturn(Collections.singletonList(vacuumCleaner));

    List<VacuumCleaner> result = vacuumCleanerService.findCordlessVacuumCleaners();
    assertEquals(1, result.size());
    assertEquals(vacuumCleaner, result.get(0));
  }

  @Test
  void testSortBySuctionPower() {
    VacuumCleaner vacuumCleaner1 = createVacuumCleaner(300);
    VacuumCleaner vacuumCleaner2 = createVacuumCleaner(500);

    when(applianceService.getAllAppliances()).thenReturn(Arrays.asList(vacuumCleaner1, vacuumCleaner2));

    List<VacuumCleaner> result = vacuumCleanerService.sortBySuctionPower();
    assertEquals(300, result.get(0).getSuctionPower());
    assertEquals(500, result.get(1).getSuctionPower());
  }

  @Test
  void testSortByPrice() {
    VacuumCleaner vacuumCleaner1 = createVacuumCleaner(300, 100.0);
    VacuumCleaner vacuumCleaner2 = createVacuumCleaner(500, 200.0);

    when(applianceService.getAllAppliances()).thenReturn(Arrays.asList(vacuumCleaner1, vacuumCleaner2));

    List<VacuumCleaner> result = vacuumCleanerService.sortByPrice();
    assertEquals(100.0, result.get(0).getPrice());
    assertEquals(200.0, result.get(1).getPrice());
  }

  @Test
  void testCountVacuumCleaners() {
    VacuumCleaner vacuumCleaner = createVacuumCleaner();
    when(applianceService.getAllAppliances()).thenReturn(Collections.singletonList(vacuumCleaner));

    int result = vacuumCleanerService.countVacuumCleaners();
    assertEquals(1, result);
  }

  @Test
  void testFindByEnergyEfficiency() {
    VacuumCleaner vacuumCleaner = createVacuumCleaner();
    when(applianceService.getAllAppliances()).thenReturn(Collections.singletonList(vacuumCleaner));

    List<VacuumCleaner> result = vacuumCleanerService.findByEnergyEfficiency(EnergyEfficiency.A_PLUS);
    assertEquals(1, result.size());
    assertEquals(vacuumCleaner, result.get(0));
  }

  private VacuumCleaner createVacuumCleaner() {
    return VacuumCleaner.builder()
            .name("Test Vacuum")
            .brand("Test Brand")
            .price(150.0)
            .powerSource("Electric")
            .energyEfficiency(EnergyEfficiency.A_PLUS)
            .powerConsumption(500)
            .type(VacuumCleanerType.UPRIGHT)
            .suctionPower(400)
            .dustCapacity(2)
            .isCordless(true)
            .build();
  }

  private VacuumCleaner createVacuumCleaner(int suctionPower) {
    return VacuumCleaner.builder()
            .name("Test Vacuum")
            .brand("Test Brand")
            .price(150.0)
            .powerSource("Electric")
            .energyEfficiency(EnergyEfficiency.A_PLUS)
            .powerConsumption(500)
            .type(VacuumCleanerType.UPRIGHT)
            .suctionPower(suctionPower)
            .dustCapacity(2)
            .isCordless(true)
            .build();
  }

  private VacuumCleaner createVacuumCleaner(int suctionPower, double price) {
    return VacuumCleaner.builder()
            .name("Test Vacuum")
            .brand("Test Brand")
            .price(price)
            .powerSource("Electric")
            .energyEfficiency(EnergyEfficiency.A_PLUS)
            .powerConsumption(500)
            .type(VacuumCleanerType.UPRIGHT)
            .suctionPower(suctionPower)
            .dustCapacity(2)
            .isCordless(true)
            .build();
  }
}
