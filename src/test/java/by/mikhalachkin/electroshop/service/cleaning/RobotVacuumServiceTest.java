package by.mikhalachkin.electroshop.service.cleaning;

import by.mikhalachkin.electroshop.model.EnergyEfficiency;
import by.mikhalachkin.electroshop.model.cleaning.RobotVacuum;
import by.mikhalachkin.electroshop.service.ApplianceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RobotVacuumServiceTest {

  private ApplianceService applianceService;
  private RobotVacuumService robotVacuumService;

  @BeforeEach
  void setUp() {
    applianceService = mock(ApplianceService.class);
    robotVacuumService = new RobotVacuumService(applianceService);
  }

  @Test
  void testAddRobotVacuum() {
    RobotVacuum robotVacuum = RobotVacuum.builder()
            .name("Xiaomi Mi Robot Vacuum-Mop 2")
            .brand("Xiaomi")
            .price(299.99)
            .powerSource("Battery")
            .energyEfficiency(EnergyEfficiency.A_PLUS)
            .powerConsumption(50)
            .batteryCapacity(3000)
            .supportsSmartControl(true)
            .build();

    robotVacuumService.addRobotVacuum(robotVacuum);

    verify(applianceService).addAppliance(robotVacuum);
  }

  @Test
  void testRemoveRobotVacuum() {
    RobotVacuum robotVacuum = RobotVacuum.builder()
            .name("Xiaomi Mi Robot Vacuum-Mop 2")
            .brand("Xiaomi")
            .price(299.99)
            .powerSource("Battery")
            .energyEfficiency(EnergyEfficiency.A_PLUS)
            .powerConsumption(50)
            .batteryCapacity(3000)
            .supportsSmartControl(true)
            .build();

    robotVacuumService.removeRobotVacuum(robotVacuum);

    verify(applianceService).removeAppliance(robotVacuum);
  }

  @Test
  void testGetAllRobotVacuums() {
    RobotVacuum vacuum1 = RobotVacuum.builder()
            .name("iRobot Roomba S9+")
            .brand("iRobot")
            .price(1099.99)
            .powerSource("Battery")
            .energyEfficiency(EnergyEfficiency.A_PLUS_PLUS)
            .powerConsumption(45)
            .batteryCapacity(3300)
            .supportsSmartControl(true)
            .build();

    RobotVacuum vacuum2 = RobotVacuum.builder()
            .name("Eufy RoboVac 11S")
            .brand("Eufy")
            .price(249.99)
            .powerSource("Battery")
            .energyEfficiency(EnergyEfficiency.A_PLUS)
            .powerConsumption(40)
            .batteryCapacity(2600)
            .supportsSmartControl(false)
            .build();

    when(applianceService.getAllAppliances()).thenReturn(List.of(vacuum1, vacuum2));

    List<RobotVacuum> result = robotVacuumService.getAllRobotVacuums();

    assertEquals(2, result.size());
    assertTrue(result.contains(vacuum1));
    assertTrue(result.contains(vacuum2));
  }

  @Test
  void testFindByBatteryCapacity() {
    RobotVacuum vacuum1 = RobotVacuum.builder()
            .name("iRobot Roomba S9+")
            .brand("iRobot")
            .price(1099.99)
            .powerSource("Battery")
            .energyEfficiency(EnergyEfficiency.A_PLUS_PLUS)
            .powerConsumption(45)
            .batteryCapacity(3300)
            .supportsSmartControl(true)
            .build();

    RobotVacuum vacuum2 = RobotVacuum.builder()
            .name("Eufy RoboVac 11S")
            .brand("Eufy")
            .price(249.99)
            .powerSource("Battery")
            .energyEfficiency(EnergyEfficiency.A_PLUS)
            .powerConsumption(40)
            .batteryCapacity(2600)
            .supportsSmartControl(false)
            .build();

    when(applianceService.getAllAppliances()).thenReturn(List.of(vacuum1, vacuum2));

    List<RobotVacuum> result = robotVacuumService.findByBatteryCapacity(3000);

    assertEquals(1, result.size());
    assertTrue(result.contains(vacuum1));
  }

  @Test
  void testSortByPrice() {
    RobotVacuum vacuum1 = RobotVacuum.builder()
            .name("iRobot Roomba S9+")
            .brand("iRobot")
            .price(1099.99)
            .powerSource("Battery")
            .energyEfficiency(EnergyEfficiency.A_PLUS_PLUS)
            .powerConsumption(45)
            .batteryCapacity(3300)
            .supportsSmartControl(true)
            .build();

    RobotVacuum vacuum2 = RobotVacuum.builder()
            .name("Eufy RoboVac 11S")
            .brand("Eufy")
            .price(249.99)
            .powerSource("Battery")
            .energyEfficiency(EnergyEfficiency.A_PLUS)
            .powerConsumption(40)
            .batteryCapacity(2600)
            .supportsSmartControl(false)
            .build();

    when(applianceService.getAllAppliances()).thenReturn(List.of(vacuum1, vacuum2));

    List<RobotVacuum> result = robotVacuumService.sortByPrice();

    assertEquals(2, result.size());
    assertEquals(vacuum2, result.get(0));
    assertEquals(vacuum1, result.get(1));
  }

  @Test
  void testCountRobotVacuums() {
    RobotVacuum vacuum1 = RobotVacuum.builder()
            .name("iRobot Roomba S9+")
            .brand("iRobot")
            .price(1099.99)
            .powerSource("Battery")
            .energyEfficiency(EnergyEfficiency.A_PLUS_PLUS)
            .powerConsumption(45)
            .batteryCapacity(3300)
            .supportsSmartControl(true)
            .build();

    RobotVacuum vacuum2 = RobotVacuum.builder()
            .name("Eufy RoboVac 11S")
            .brand("Eufy")
            .price(249.99)
            .powerSource("Battery")
            .energyEfficiency(EnergyEfficiency.A_PLUS)
            .powerConsumption(40)
            .batteryCapacity(2600)
            .supportsSmartControl(false)
            .build();

    when(applianceService.getAllAppliances()).thenReturn(List.of(vacuum1, vacuum2));

    int count = robotVacuumService.countRobotVacuums();

    assertEquals(2, count);
  }
}
