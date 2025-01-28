package by.mikhalachkin.electroshop.service.computers;

import by.mikhalachkin.electroshop.model.computers.Desktop;
import by.mikhalachkin.electroshop.model.computers.OperatingSystemDesktopLaptop;
import by.mikhalachkin.electroshop.service.ApplianceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DesktopServiceTest {

  @Mock
  private ApplianceService applianceService;

  private DesktopService desktopService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    desktopService = new DesktopService(applianceService);
  }

  @Test
  void testAddDesktop() {
    Desktop desktop = createDesktop();
    desktopService.addDesktop(desktop);
    verify(applianceService, times(1)).addAppliance(desktop);
  }

  @Test
  void testRemoveDesktop() {
    Desktop desktop = createDesktop();
    desktopService.removeDesktop(desktop);
    verify(applianceService, times(1)).removeAppliance(desktop);
  }

  @Test
  void testGetAllDesktops() {
    Desktop desktop = createDesktop();
    when(applianceService.getAllAppliances()).thenReturn(List.of(desktop));

    List<Desktop> result = desktopService.getAllDesktops();
    assertEquals(1, result.size());
    assertEquals(desktop, result.get(0));
  }

  @Test
  void testFindByOperatingSystem() {
    Desktop windowsDesktop = createDesktopWithOS(OperatingSystemDesktopLaptop.WINDOWS);
    Desktop linuxDesktop = createDesktopWithOS(OperatingSystemDesktopLaptop.LINUX);

    when(applianceService.getAllAppliances()).thenReturn(List.of(windowsDesktop, linuxDesktop));

    List<Desktop> result = desktopService.findByOperatingSystem(OperatingSystemDesktopLaptop.WINDOWS);
    assertEquals(1, result.size());
    assertEquals(OperatingSystemDesktopLaptop.WINDOWS, result.get(0).getOperatingSystem());
  }

  @Test
  void testFindGamingDesktops() {
    Desktop gamingDesktop = createDesktopWithGaming(true);
    Desktop nonGamingDesktop = createDesktopWithGaming(false);

    when(applianceService.getAllAppliances()).thenReturn(List.of(gamingDesktop, nonGamingDesktop));

    List<Desktop> result = desktopService.findGamingDesktops();
    assertEquals(1, result.size());
    assertTrue(result.get(0).isGaming());
  }

  @Test
  void testFindByMinRam() {
    Desktop lowRamDesktop = createDesktopWithRam(8);
    Desktop highRamDesktop = createDesktopWithRam(16);

    when(applianceService.getAllAppliances()).thenReturn(List.of(lowRamDesktop, highRamDesktop));

    List<Desktop> result = desktopService.findByMinRam(12);
    assertEquals(1, result.size());
    assertEquals(16, result.get(0).getRam());
  }

  @Test
  void testFindByProcessor() {
    Desktop intelDesktop = createDesktopWithProcessor("Intel");
    Desktop amdDesktop = createDesktopWithProcessor("AMD");

    when(applianceService.getAllAppliances()).thenReturn(List.of(intelDesktop, amdDesktop));

    List<Desktop> result = desktopService.findByProcessor("Intel");
    assertEquals(1, result.size());
    assertEquals("Intel", result.get(0).getProcessor());
  }

  @Test
  void testSortByRam() {
    Desktop lowRamDesktop = createDesktopWithRam(8);
    Desktop highRamDesktop = createDesktopWithRam(16);

    when(applianceService.getAllAppliances()).thenReturn(List.of(highRamDesktop, lowRamDesktop));

    List<Desktop> result = desktopService.sortByRam();
    assertEquals(8, result.get(0).getRam());
    assertEquals(16, result.get(1).getRam());
  }

  @Test
  void testSortByPrice() {
    Desktop cheapDesktop = createDesktopWithPrice(500.0);
    Desktop expensiveDesktop = createDesktopWithPrice(1000.0);

    when(applianceService.getAllAppliances()).thenReturn(List.of(expensiveDesktop, cheapDesktop));

    List<Desktop> result = desktopService.sortByPrice();
    assertEquals(500.0, result.get(0).getPrice());
    assertEquals(1000.0, result.get(1).getPrice());
  }

  @Test
  void testFilterByPriceRange() {
    Desktop cheapDesktop = createDesktopWithPrice(500.0);
    Desktop midRangeDesktop = createDesktopWithPrice(750.0);
    Desktop expensiveDesktop = createDesktopWithPrice(1000.0);

    when(applianceService.getAllAppliances()).thenReturn(List.of(cheapDesktop, midRangeDesktop, expensiveDesktop));

    List<Desktop> result = desktopService.filterByPriceRange(600.0, 800.0);
    assertEquals(1, result.size());
    assertEquals(750.0, result.get(0).getPrice());
  }

  @Test
  void testCountDesktops() {
    Desktop desktop1 = createDesktop();
    Desktop desktop2 = createDesktop();

    when(applianceService.getAllAppliances()).thenReturn(List.of(desktop1, desktop2));

    int count = desktopService.countDesktops();
    assertEquals(2, count);
  }

  // Вспомогательные методы для создания объектов Desktop

  private Desktop createDesktop() {
    return Desktop.builder()
            .name("Standard Desktop")
            .brand("DesktopBrand")
            .price(800.0)
            .powerSource("Electric")
            .operatingSystem(OperatingSystemDesktopLaptop.WINDOWS)
            .ram(16)
            .processor("Intel")
            .storage("512GB SSD")
            .gpuModel("NVIDIA RTX 3060")
            .gaming(false)
            .build();
  }

  private Desktop createDesktopWithOS(OperatingSystemDesktopLaptop os) {
    return Desktop.builder()
            .name("OS Desktop")
            .brand("DesktopBrand")
            .price(900.0)
            .powerSource("Electric")
            .operatingSystem(os)
            .ram(16)
            .processor("Intel")
            .storage("1TB SSD")
            .gpuModel("NVIDIA RTX 3070")
            .gaming(false)
            .build();
  }

  private Desktop createDesktopWithGaming(boolean gaming) {
    return Desktop.builder()
            .name("Gaming Desktop")
            .brand("GamingBrand")
            .price(1500.0)
            .powerSource("Electric")
            .operatingSystem(OperatingSystemDesktopLaptop.WINDOWS)
            .ram(32)
            .processor("AMD")
            .storage("1TB NVMe")
            .gpuModel("NVIDIA RTX 4090")
            .gaming(gaming)
            .build();
  }

  private Desktop createDesktopWithRam(int ram) {
    return Desktop.builder()
            .name("RAM Desktop")
            .brand("DesktopBrand")
            .price(1000.0)
            .powerSource("Electric")
            .operatingSystem(OperatingSystemDesktopLaptop.LINUX)
            .ram(ram)
            .processor("Intel")
            .storage("256GB SSD")
            .gpuModel("Intel Integrated")
            .gaming(false)
            .build();
  }

  private Desktop createDesktopWithProcessor(String processor) {
    return Desktop.builder()
            .name("Processor Desktop")
            .brand("DesktopBrand")
            .price(1200.0)
            .powerSource("Electric")
            .operatingSystem(OperatingSystemDesktopLaptop.WINDOWS)
            .ram(16)
            .processor(processor)
            .storage("512GB SSD")
            .gpuModel("NVIDIA RTX 2060")
            .gaming(false)
            .build();
  }

  private Desktop createDesktopWithPrice(double price) {
    return Desktop.builder()
            .name("Price Desktop")
            .brand("DesktopBrand")
            .price(price)
            .powerSource("Electric")
            .operatingSystem(OperatingSystemDesktopLaptop.WINDOWS)
            .ram(16)
            .processor("AMD")
            .storage("1TB HDD")
            .gpuModel("NVIDIA GTX 1650")
            .gaming(false)
            .build();
  }
}
