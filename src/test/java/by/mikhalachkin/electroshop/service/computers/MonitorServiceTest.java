package by.mikhalachkin.electroshop.service.computers;

import by.mikhalachkin.electroshop.model.EnergyEfficiency;
import by.mikhalachkin.electroshop.model.computers.Monitor;
import by.mikhalachkin.electroshop.service.ApplianceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MonitorServiceTest {

  @Mock
  private ApplianceService applianceService;

  private MonitorService monitorService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    monitorService = new MonitorService(applianceService);
  }

  @Test
  void testAddMonitor() {
    Monitor monitor = createMonitor();
    monitorService.addMonitor(monitor);
    verify(applianceService, times(1)).addAppliance(monitor);
  }

  @Test
  void testRemoveMonitor() {
    Monitor monitor = createMonitor();
    monitorService.removeMonitor(monitor);
    verify(applianceService, times(1)).removeAppliance(monitor);
  }

  @Test
  void testGetAllMonitors() {
    Monitor monitor = createMonitor();
    when(applianceService.getAllAppliances()).thenReturn(List.of(monitor));

    List<Monitor> result = monitorService.getAllMonitors();
    assertEquals(1, result.size());
    assertEquals(monitor, result.get(0));
  }

  @Test
  void testFindByResolution() {
    Monitor fullHdMonitor = createMonitorWithResolution("1920x1080");
    Monitor ultraHdMonitor = createMonitorWithResolution("3840x2160");

    when(applianceService.getAllAppliances()).thenReturn(List.of(fullHdMonitor, ultraHdMonitor));

    List<Monitor> result = monitorService.findByResolution("1920x1080");
    assertEquals(1, result.size());
    assertEquals("1920x1080", result.get(0).getResolution());
  }

  @Test
  void testFindByMinRefreshRate() {
    Monitor lowRefreshMonitor = createMonitorWithRefreshRate(60);
    Monitor highRefreshMonitor = createMonitorWithRefreshRate(144);

    when(applianceService.getAllAppliances()).thenReturn(List.of(lowRefreshMonitor, highRefreshMonitor));

    List<Monitor> result = monitorService.findByMinRefreshRate(120);
    assertEquals(1, result.size());
    assertEquals(144, result.get(0).getRefreshRate());
  }

  @Test
  void testFindByEnergyEfficiency() {
    Monitor efficientMonitor = createMonitorWithEnergyEfficiency(EnergyEfficiency.A_PLUS_PLUS);
    Monitor lessEfficientMonitor = createMonitorWithEnergyEfficiency(EnergyEfficiency.A);

    when(applianceService.getAllAppliances()).thenReturn(List.of(efficientMonitor, lessEfficientMonitor));

    List<Monitor> result = monitorService.findByEnergyEfficiency(EnergyEfficiency.A_PLUS_PLUS);
    assertEquals(1, result.size());
    assertEquals(EnergyEfficiency.A_PLUS_PLUS, result.get(0).getEnergyEfficiency());
  }

  @Test
  void testSortByRefreshRate() {
    Monitor lowRefreshMonitor = createMonitorWithRefreshRate(60);
    Monitor highRefreshMonitor = createMonitorWithRefreshRate(144);

    when(applianceService.getAllAppliances()).thenReturn(List.of(highRefreshMonitor, lowRefreshMonitor));

    List<Monitor> result = monitorService.sortByRefreshRate();
    assertEquals(60, result.get(0).getRefreshRate());
    assertEquals(144, result.get(1).getRefreshRate());
  }

  @Test
  void testSortByPrice() {
    Monitor cheapMonitor = createMonitorWithPrice(200.0);
    Monitor expensiveMonitor = createMonitorWithPrice(500.0);

    when(applianceService.getAllAppliances()).thenReturn(List.of(expensiveMonitor, cheapMonitor));

    List<Monitor> result = monitorService.sortByPrice();
    assertEquals(200.0, result.get(0).getPrice());
    assertEquals(500.0, result.get(1).getPrice());
  }

  @Test
  void testSortByEnergyEfficiency() {
    Monitor efficientMonitor = createMonitorWithEnergyEfficiency(EnergyEfficiency.A_PLUS_PLUS);
    Monitor lessEfficientMonitor = createMonitorWithEnergyEfficiency(EnergyEfficiency.B);

    when(applianceService.getAllAppliances()).thenReturn(List.of(lessEfficientMonitor, efficientMonitor));

    List<Monitor> result = monitorService.sortByEnergyEfficiency();
    assertEquals(EnergyEfficiency.A_PLUS_PLUS, result.get(0).getEnergyEfficiency());
    assertEquals(EnergyEfficiency.B, result.get(1).getEnergyEfficiency());
  }

  @Test
  void testSortByPowerConsumption() {
    Monitor lowPowerMonitor = createMonitorWithPowerConsumption(50);
    Monitor highPowerMonitor = createMonitorWithPowerConsumption(100);

    when(applianceService.getAllAppliances()).thenReturn(List.of(highPowerMonitor, lowPowerMonitor));

    List<Monitor> result = monitorService.sortByPowerConsumption();
    assertEquals(50, result.get(0).calculatePowerConsumption());
    assertEquals(100, result.get(1).calculatePowerConsumption());
  }

  @Test
  void testFilterByPriceRange() {
    Monitor cheapMonitor = createMonitorWithPrice(200.0);
    Monitor midRangeMonitor = createMonitorWithPrice(350.0);
    Monitor expensiveMonitor = createMonitorWithPrice(500.0);

    when(applianceService.getAllAppliances()).thenReturn(List.of(cheapMonitor, midRangeMonitor, expensiveMonitor));

    List<Monitor> result = monitorService.filterByPriceRange(300.0, 400.0);
    assertEquals(1, result.size());
    assertEquals(350.0, result.get(0).getPrice());
  }

  @Test
  void testCountMonitors() {
    Monitor monitor1 = createMonitor();
    Monitor monitor2 = createMonitor();

    when(applianceService.getAllAppliances()).thenReturn(List.of(monitor1, monitor2));

    int count = monitorService.countMonitors();
    assertEquals(2, count);
  }

  // Вспомогательные методы для создания объектов Monitor

  private Monitor createMonitor() {
    return Monitor.builder()
            .name("Standard Monitor")
            .brand("MonitorBrand")
            .price(300.0)
            .powerSource("Electricity")
            .resolution("1920x1080")
            .refreshRate(60)
            .energyEfficiency(EnergyEfficiency.A_PLUS)
            .powerConsumption(50)
            .build();
  }

  private Monitor createMonitorWithResolution(String resolution) {
    return Monitor.builder()
            .name("Resolution Monitor")
            .brand("MonitorBrand")
            .price(400.0)
            .powerSource("Electricity")
            .resolution(resolution)
            .refreshRate(60)
            .energyEfficiency(EnergyEfficiency.A)
            .powerConsumption(70)
            .build();
  }

  private Monitor createMonitorWithRefreshRate(int refreshRate) {
    return Monitor.builder()
            .name("Refresh Monitor")
            .brand("MonitorBrand")
            .price(500.0)
            .powerSource("Electricity")
            .resolution("2560x1440")
            .refreshRate(refreshRate)
            .energyEfficiency(EnergyEfficiency.A)
            .powerConsumption(70)
            .build();
  }

  private Monitor createMonitorWithEnergyEfficiency(EnergyEfficiency efficiency) {
    return Monitor.builder()
            .name("Efficient Monitor")
            .brand("MonitorBrand")
            .price(600.0)
            .powerSource("Electricity")
            .resolution("3840x2160")
            .refreshRate(144)
            .energyEfficiency(efficiency)
            .powerConsumption(80)
            .build();
  }

  private Monitor createMonitorWithPrice(double price) {
    return Monitor.builder()
            .name("Price Monitor")
            .brand("MonitorBrand")
            .price(price)
            .powerSource("Electricity")
            .resolution("2560x1440")
            .refreshRate(75)
            .energyEfficiency(EnergyEfficiency.B)
            .powerConsumption(60)
            .build();
  }

  private Monitor createMonitorWithPowerConsumption(int powerConsumption) {
    return Monitor.builder()
            .name("Power Monitor")
            .brand("MonitorBrand")
            .price(400.0)
            .powerSource("Electricity")
            .resolution("1920x1080")
            .refreshRate(60)
            .energyEfficiency(EnergyEfficiency.A)
            .powerConsumption(powerConsumption)
            .build();
  }
}
