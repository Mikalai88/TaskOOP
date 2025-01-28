package by.mikhalachkin.electroshop.service.computers;

import by.mikhalachkin.electroshop.model.computers.Laptop;
import by.mikhalachkin.electroshop.model.computers.OperatingSystemDesktopLaptop;
import by.mikhalachkin.electroshop.service.ApplianceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LaptopServiceTest {

  @Mock
  private ApplianceService applianceService;

  private LaptopService laptopService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    laptopService = new LaptopService(applianceService);
  }

  @Test
  void testAddLaptop() {
    Laptop laptop = createLaptop();
    laptopService.addLaptop(laptop);
    verify(applianceService, times(1)).addAppliance(laptop);
  }

  @Test
  void testRemoveLaptop() {
    Laptop laptop = createLaptop();
    laptopService.removeLaptop(laptop);
    verify(applianceService, times(1)).removeAppliance(laptop);
  }

  @Test
  void testGetAllLaptops() {
    Laptop laptop = createLaptop();
    when(applianceService.getAllAppliances()).thenReturn(List.of(laptop));

    List<Laptop> result = laptopService.getAllLaptops();
    assertEquals(1, result.size());
    assertEquals(laptop, result.get(0));
  }

  @Test
  void testFindByOperatingSystem() {
    Laptop windowsLaptop = createLaptopWithOS(OperatingSystemDesktopLaptop.WINDOWS);
    Laptop macLaptop = createLaptopWithOS(OperatingSystemDesktopLaptop.MACOS);

    when(applianceService.getAllAppliances()).thenReturn(List.of(windowsLaptop, macLaptop));

    List<Laptop> result = laptopService.findByOperatingSystem(OperatingSystemDesktopLaptop.WINDOWS);
    assertEquals(1, result.size());
    assertEquals(OperatingSystemDesktopLaptop.WINDOWS, result.get(0).getOperatingSystem());
  }

  @Test
  void testFindGamingLaptops() {
    Laptop gamingLaptop = createLaptopWithGaming(true);
    Laptop nonGamingLaptop = createLaptopWithGaming(false);

    when(applianceService.getAllAppliances()).thenReturn(List.of(gamingLaptop, nonGamingLaptop));

    List<Laptop> result = laptopService.findGamingLaptops();
    assertEquals(1, result.size());
    assertTrue(result.get(0).isGaming());
  }

  @Test
  void testFindByMinRam() {
    Laptop lowRamLaptop = createLaptopWithRam(8);
    Laptop highRamLaptop = createLaptopWithRam(16);

    when(applianceService.getAllAppliances()).thenReturn(List.of(lowRamLaptop, highRamLaptop));

    List<Laptop> result = laptopService.findByMinRam(12);
    assertEquals(1, result.size());
    assertEquals(16, result.get(0).getRam());
  }

  @Test
  void testFindByProcessor() {
    Laptop intelLaptop = createLaptopWithProcessor("Intel");
    Laptop amdLaptop = createLaptopWithProcessor("AMD");

    when(applianceService.getAllAppliances()).thenReturn(List.of(intelLaptop, amdLaptop));

    List<Laptop> result = laptopService.findByProcessor("Intel");
    assertEquals(1, result.size());
    assertEquals("Intel", result.get(0).getProcessor());
  }

  @Test
  void testSortByRam() {
    Laptop lowRamLaptop = createLaptopWithRam(8);
    Laptop highRamLaptop = createLaptopWithRam(16);

    when(applianceService.getAllAppliances()).thenReturn(List.of(highRamLaptop, lowRamLaptop));

    List<Laptop> result = laptopService.sortByRam();
    assertEquals(8, result.get(0).getRam());
    assertEquals(16, result.get(1).getRam());
  }

  @Test
  void testSortByBatteryCapacity() {
    Laptop lowBatteryLaptop = createLaptopWithBatteryCapacity(50.0);
    Laptop highBatteryLaptop = createLaptopWithBatteryCapacity(100.0);

    when(applianceService.getAllAppliances()).thenReturn(List.of(highBatteryLaptop, lowBatteryLaptop));

    List<Laptop> result = laptopService.sortByBatteryCapacity();
    assertEquals(50.0, result.get(0).getBatteryCapacity());
    assertEquals(100.0, result.get(1).getBatteryCapacity());
  }

  @Test
  void testSortByPrice() {
    Laptop cheapLaptop = createLaptopWithPrice(500.0);
    Laptop expensiveLaptop = createLaptopWithPrice(1500.0);

    when(applianceService.getAllAppliances()).thenReturn(List.of(expensiveLaptop, cheapLaptop));

    List<Laptop> result = laptopService.sortByPrice();
    assertEquals(500.0, result.get(0).getPrice());
    assertEquals(1500.0, result.get(1).getPrice());
  }

  @Test
  void testFilterByPriceRange() {
    Laptop cheapLaptop = createLaptopWithPrice(500.0);
    Laptop midRangeLaptop = createLaptopWithPrice(1000.0);
    Laptop expensiveLaptop = createLaptopWithPrice(1500.0);

    when(applianceService.getAllAppliances()).thenReturn(List.of(cheapLaptop, midRangeLaptop, expensiveLaptop));

    List<Laptop> result = laptopService.filterByPriceRange(800.0, 1200.0);
    assertEquals(1, result.size());
    assertEquals(1000.0, result.get(0).getPrice());
  }

  @Test
  void testCountLaptops() {
    Laptop laptop1 = createLaptop();
    Laptop laptop2 = createLaptop();

    when(applianceService.getAllAppliances()).thenReturn(List.of(laptop1, laptop2));

    int count = laptopService.countLaptops();
    assertEquals(2, count);
  }

  // Вспомогательные методы для создания объектов Laptop

  private Laptop createLaptop() {
    return Laptop.builder()
            .name("Standard Laptop")
            .brand("LaptopBrand")
            .price(1200.0)
            .powerSource("Battery")
            .operatingSystem(OperatingSystemDesktopLaptop.WINDOWS)
            .ram(16)
            .processor("Intel")
            .storage("512GB SSD")
            .batteryCapacity(80.0)
            .weight(2.5)
            .gaming(false)
            .build();
  }

  private Laptop createLaptopWithOS(OperatingSystemDesktopLaptop os) {
    return Laptop.builder()
            .name("OS Laptop")
            .brand("LaptopBrand")
            .price(1400.0)
            .powerSource("Battery")
            .operatingSystem(os)
            .ram(16)
            .processor("Intel")
            .storage("1TB SSD")
            .batteryCapacity(90.0)
            .weight(2.0)
            .gaming(false)
            .build();
  }

  private Laptop createLaptopWithGaming(boolean gaming) {
    return Laptop.builder()
            .name("Gaming Laptop")
            .brand("GamingBrand")
            .price(2000.0)
            .powerSource("Battery")
            .operatingSystem(OperatingSystemDesktopLaptop.WINDOWS)
            .ram(32)
            .processor("AMD Ryzen")
            .storage("1TB NVMe")
            .batteryCapacity(100.0)
            .weight(3.0)
            .gaming(gaming)
            .build();
  }

  private Laptop createLaptopWithRam(int ram) {
    return Laptop.builder()
            .name("RAM Laptop")
            .brand("LaptopBrand")
            .price(1000.0)
            .powerSource("Battery")
            .operatingSystem(OperatingSystemDesktopLaptop.LINUX)
            .ram(ram)
            .processor("Intel")
            .storage("256GB SSD")
            .batteryCapacity(60.0)
            .weight(2.5)
            .gaming(false)
            .build();
  }

  private Laptop createLaptopWithProcessor(String processor) {
    return Laptop.builder()
            .name("Processor Laptop")
            .brand("LaptopBrand")
            .price(1200.0)
            .powerSource("Battery")
            .operatingSystem(OperatingSystemDesktopLaptop.WINDOWS)
            .ram(16)
            .processor(processor)
            .storage("512GB SSD")
            .batteryCapacity(70.0)
            .weight(2.3)
            .gaming(false)
            .build();
  }

  private Laptop createLaptopWithBatteryCapacity(double batteryCapacity) {
    return Laptop.builder()
            .name("Battery Laptop")
            .brand("LaptopBrand")
            .price(1500.0)
            .powerSource("Battery")
            .operatingSystem(OperatingSystemDesktopLaptop.WINDOWS)
            .ram(16)
            .processor("AMD Ryzen")
            .storage("512GB SSD")
            .batteryCapacity(batteryCapacity)
            .weight(2.0)
            .gaming(false)
            .build();
  }

  private Laptop createLaptopWithPrice(double price) {
    return Laptop.builder()
            .name("Price Laptop")
            .brand("LaptopBrand")
            .price(price)
            .powerSource("Battery")
            .operatingSystem(OperatingSystemDesktopLaptop.WINDOWS)
            .ram(16)
            .processor("Intel")
            .storage("1TB SSD")
            .batteryCapacity(80.0)
            .weight(2.5)
            .gaming(false)
            .build();
  }
}
