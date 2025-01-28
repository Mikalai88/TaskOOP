package by.mikhalachkin.electroshop.service.cleaning;

import by.mikhalachkin.electroshop.model.EnergyEfficiency;
import by.mikhalachkin.electroshop.model.cleaning.WindowCleaner;
import by.mikhalachkin.electroshop.service.ApplianceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WindowCleanerServiceTest {

  @Mock
  private ApplianceService applianceService;

  private WindowCleanerService windowCleanerService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    windowCleanerService = new WindowCleanerService(applianceService);
  }

  @Test
  void testAddWindowCleaner() {
    WindowCleaner windowCleaner = createWindowCleaner();
    windowCleanerService.addWindowCleaner(windowCleaner);
    verify(applianceService, times(1)).addAppliance(windowCleaner);
  }

  @Test
  void testRemoveWindowCleaner() {
    WindowCleaner windowCleaner = createWindowCleaner();
    windowCleanerService.removeWindowCleaner(windowCleaner);
    verify(applianceService, times(1)).removeAppliance(windowCleaner);
  }

  @Test
  void testGetAllWindowCleaners() {
    WindowCleaner windowCleaner = createWindowCleaner();
    when(applianceService.getAllAppliances()).thenReturn(List.of(windowCleaner));

    List<WindowCleaner> result = windowCleanerService.getAllWindowCleaners();
    assertEquals(1, result.size());
    assertEquals(windowCleaner, result.get(0));
  }

  @Test
  void testFindRoboticWindowCleaners() {
    WindowCleaner robotWindowCleaner = createWindowCleaner(true);
    WindowCleaner manualWindowCleaner = createWindowCleaner(false);

    when(applianceService.getAllAppliances()).thenReturn(List.of(robotWindowCleaner, manualWindowCleaner));

    List<WindowCleaner> result = windowCleanerService.findRoboticWindowCleaners();
    assertEquals(1, result.size());
    assertEquals(robotWindowCleaner, result.get(0));
  }

  @Test
  void testFindSmartControlledWindowCleaners() {
    WindowCleaner smartWindowCleaner = createWindowCleaner();
    when(applianceService.getAllAppliances()).thenReturn(List.of(smartWindowCleaner));

    List<WindowCleaner> result = windowCleanerService.findSmartControlledWindowCleaners();
    assertEquals(1, result.size());
    assertTrue(result.get(0).isSupportsSmartControl());
  }

  @Test
  void testSortByBatteryLife() {
    WindowCleaner cleaner1 = createWindowCleanerWithBatteryLife(60);
    WindowCleaner cleaner2 = createWindowCleanerWithBatteryLife(120);

    when(applianceService.getAllAppliances()).thenReturn(List.of(cleaner2, cleaner1));

    List<WindowCleaner> result = windowCleanerService.sortByBatteryLife();
    assertEquals(60, result.get(0).getBatteryLife());
    assertEquals(120, result.get(1).getBatteryLife());
  }

  // Создание тестового объекта WindowCleaner
  private WindowCleaner createWindowCleaner() {
    return WindowCleaner.builder()
            .name("Smart Cleaner")
            .brand("SmartBrand")
            .price(299.99)
            .powerSource("Battery")
            .energyEfficiency(EnergyEfficiency.A_PLUS)
            .powerConsumption(100)
            .isRobot(true)
            .waterTankCapacity(0.5)
            .batteryLife(90)
            .supportsSmartControl(true)
            .build();
  }

  private WindowCleaner createWindowCleaner(boolean isRobot) {
    return WindowCleaner.builder()
            .name("Cleaner")
            .brand("Brand")
            .price(199.99)
            .powerSource("Battery")
            .energyEfficiency(EnergyEfficiency.A)
            .powerConsumption(120)
            .isRobot(isRobot)
            .waterTankCapacity(0.7)
            .batteryLife(100)
            .supportsSmartControl(false)
            .build();
  }

  private WindowCleaner createWindowCleanerWithBatteryLife(int batteryLife) {
    return WindowCleaner.builder()
            .name("Cleaner")
            .brand("Brand")
            .price(199.99)
            .powerSource("Battery")
            .energyEfficiency(EnergyEfficiency.B)
            .powerConsumption(100)
            .isRobot(false)
            .waterTankCapacity(0.5)
            .batteryLife(batteryLife)
            .supportsSmartControl(false)
            .build();
  }
}
