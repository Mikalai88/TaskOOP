package by.mikhalachkin.electroshop.service.computers;

import by.mikhalachkin.electroshop.model.computers.OperatingSystemTablet;
import by.mikhalachkin.electroshop.model.computers.Tablet;
import by.mikhalachkin.electroshop.service.ApplianceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TabletServiceTest {

  @Mock
  private ApplianceService applianceService;

  private TabletService tabletService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    tabletService = new TabletService(applianceService);
  }

  @Test
  void testAddTablet() {
    Tablet tablet = createTablet("Standard Tablet", OperatingSystemTablet.ANDROID, 300.0, 4, 4000, 0.5);
    tabletService.addTablet(tablet);
    verify(applianceService, times(1)).addAppliance(tablet);
  }

  @Test
  void testRemoveTablet() {
    Tablet tablet = createTablet("Standard Tablet", OperatingSystemTablet.ANDROID, 300.0, 4, 4000, 0.5);
    tabletService.removeTablet(tablet);
    verify(applianceService, times(1)).removeAppliance(tablet);
  }

  @Test
  void testGetAllTablets() {
    Tablet tablet = createTablet("Standard Tablet", OperatingSystemTablet.ANDROID, 300.0, 4, 4000, 0.5);
    when(applianceService.getAllAppliances()).thenReturn(List.of(tablet));

    List<Tablet> result = tabletService.getAllTablets();
    assertEquals(1, result.size());
    assertEquals(tablet, result.get(0));
  }

  @Test
  void testFindByOperatingSystem() {
    Tablet androidTablet = createTablet("Android Tablet", OperatingSystemTablet.ANDROID, 300.0, 4, 4000, 0.5);
    Tablet iosTablet = createTablet("iOS Tablet", OperatingSystemTablet.IOS, 400.0, 6, 5000, 0.6);

    when(applianceService.getAllAppliances()).thenReturn(List.of(androidTablet, iosTablet));

    List<Tablet> result = tabletService.findByOperatingSystem(OperatingSystemTablet.ANDROID);
    assertEquals(1, result.size());
    assertEquals(androidTablet, result.get(0));
  }

  @Test
  void testFindByMinRam() {
    Tablet lowRamTablet = createTablet("Low RAM Tablet", OperatingSystemTablet.ANDROID, 200.0, 4, 3000, 0.4);
    Tablet highRamTablet = createTablet("High RAM Tablet", OperatingSystemTablet.ANDROID, 400.0, 8, 6000, 0.6);

    when(applianceService.getAllAppliances()).thenReturn(List.of(lowRamTablet, highRamTablet));

    List<Tablet> result = tabletService.findByMinRam(6);
    assertEquals(1, result.size());
    assertEquals(highRamTablet, result.get(0));
  }

  @Test
  void testSortByRam() {
    Tablet lowRamTablet = createTablet("Low RAM Tablet", OperatingSystemTablet.ANDROID, 200.0, 4, 3000, 0.4);
    Tablet highRamTablet = createTablet("High RAM Tablet", OperatingSystemTablet.ANDROID, 400.0, 8, 6000, 0.6);

    when(applianceService.getAllAppliances()).thenReturn(List.of(highRamTablet, lowRamTablet));

    List<Tablet> result = tabletService.sortByRam();
    assertEquals(lowRamTablet, result.get(0));
    assertEquals(highRamTablet, result.get(1));
  }

  @Test
  void testFilterByPriceRange() {
    Tablet cheapTablet = createTablet("Cheap Tablet", OperatingSystemTablet.ANDROID, 200.0, 4, 3000, 0.4);
    Tablet midRangeTablet = createTablet("Mid-Range Tablet", OperatingSystemTablet.ANDROID, 350.0, 6, 5000, 0.5);
    Tablet expensiveTablet = createTablet("Expensive Tablet", OperatingSystemTablet.ANDROID, 500.0, 8, 7000, 0.6);

    when(applianceService.getAllAppliances()).thenReturn(List.of(cheapTablet, midRangeTablet, expensiveTablet));

    List<Tablet> result = tabletService.filterByPriceRange(300.0, 400.0);
    assertEquals(1, result.size());
    assertEquals(midRangeTablet, result.get(0));
  }

  @Test
  void testCountTablets() {
    Tablet tablet1 = createTablet("Tablet 1", OperatingSystemTablet.ANDROID, 300.0, 4, 4000, 0.5);
    Tablet tablet2 = createTablet("Tablet 2", OperatingSystemTablet.IOS, 400.0, 6, 5000, 0.6);

    when(applianceService.getAllAppliances()).thenReturn(List.of(tablet1, tablet2));

    int count = tabletService.countTablets();
    assertEquals(2, count);
  }

  private Tablet createTablet(String name, OperatingSystemTablet os, double price, int ram, double batteryCapacity, double weight) {
    return Tablet.builder()
            .name(name)
            .brand("TabletBrand")
            .price(price)
            .powerSource("Battery")
            .operatingSystem(os)
            .ram(ram)
            .processor("ARM")
            .storage("64GB")
            .batteryCapacity(batteryCapacity)
            .weight(weight)
            .build();
  }
}
