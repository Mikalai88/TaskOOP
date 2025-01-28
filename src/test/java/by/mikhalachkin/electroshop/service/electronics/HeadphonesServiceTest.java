package by.mikhalachkin.electroshop.service.electronics;

import by.mikhalachkin.electroshop.model.electronics.Headphones;
import by.mikhalachkin.electroshop.service.ApplianceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HeadphonesServiceTest {

  @Mock
  private ApplianceService applianceService;

  private HeadphonesService headphonesService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    headphonesService = new HeadphonesService(applianceService);
  }

  @Test
  void testAddHeadphones() {
    Headphones headphones = createHeadphones("Sony WH-1000XM4", 30, 299.99, true);
    headphonesService.addHeadphones(headphones);
    verify(applianceService, times(1)).addAppliance(headphones);
  }

  @Test
  void testRemoveHeadphones() {
    Headphones headphones = createHeadphones("Sony WH-1000XM4", 30, 299.99, true);
    headphonesService.removeHeadphones(headphones);
    verify(applianceService, times(1)).removeAppliance(headphones);
  }

  @Test
  void testGetAllHeadphones() {
    Headphones headphones = createHeadphones("Sony WH-1000XM4", 30, 299.99, true);
    when(applianceService.getAllAppliances()).thenReturn(List.of(headphones));

    List<Headphones> result = headphonesService.getAllHeadphones();
    assertEquals(1, result.size());
    assertEquals(headphones, result.get(0));
  }

  @Test
  void testFindByNoiseCancellation() {
    Headphones noiseCancellingHeadphones = createHeadphones("Sony WH-1000XM4", 30, 299.99, true);
    Headphones nonNoiseCancellingHeadphones = createHeadphones("Bose 700", 20, 379.99, false);

    when(applianceService.getAllAppliances()).thenReturn(List.of(noiseCancellingHeadphones, nonNoiseCancellingHeadphones));

    List<Headphones> result = headphonesService.findByNoiseCancellation();
    assertEquals(1, result.size());
    assertEquals(noiseCancellingHeadphones, result.get(0));
  }

  @Test
  void testFindByMinBatteryLife() {
    Headphones lowBatteryLifeHeadphones = createHeadphones("Low Battery", 10, 199.99, true);
    Headphones highBatteryLifeHeadphones = createHeadphones("High Battery", 40, 299.99, true);

    when(applianceService.getAllAppliances()).thenReturn(List.of(lowBatteryLifeHeadphones, highBatteryLifeHeadphones));

    List<Headphones> result = headphonesService.findByMinBatteryLife(20);
    assertEquals(1, result.size());
    assertEquals(highBatteryLifeHeadphones, result.get(0));
  }

  @Test
  void testSortByBatteryLife() {
    Headphones lowBatteryLifeHeadphones = createHeadphones("Low Battery", 10, 199.99, true);
    Headphones highBatteryLifeHeadphones = createHeadphones("High Battery", 40, 299.99, true);

    when(applianceService.getAllAppliances()).thenReturn(List.of(highBatteryLifeHeadphones, lowBatteryLifeHeadphones));

    List<Headphones> result = headphonesService.sortByBatteryLife();
    assertEquals(lowBatteryLifeHeadphones, result.get(0));
    assertEquals(highBatteryLifeHeadphones, result.get(1));
  }

  @Test
  void testSortByPrice() {
    Headphones cheapHeadphones = createHeadphones("Cheap", 10, 99.99, true);
    Headphones expensiveHeadphones = createHeadphones("Expensive", 40, 399.99, true);

    when(applianceService.getAllAppliances()).thenReturn(List.of(expensiveHeadphones, cheapHeadphones));

    List<Headphones> result = headphonesService.sortByPrice();
    assertEquals(cheapHeadphones, result.get(0));
    assertEquals(expensiveHeadphones, result.get(1));
  }

  @Test
  void testFilterByPriceRange() {
    Headphones cheapHeadphones = createHeadphones("Cheap", 10, 99.99, true);
    Headphones midRangeHeadphones = createHeadphones("Mid Range", 20, 199.99, true);
    Headphones expensiveHeadphones = createHeadphones("Expensive", 40, 399.99, true);

    when(applianceService.getAllAppliances()).thenReturn(List.of(cheapHeadphones, midRangeHeadphones, expensiveHeadphones));

    List<Headphones> result = headphonesService.filterByPriceRange(100.0, 300.0);
    assertEquals(1, result.size());
    assertEquals(midRangeHeadphones, result.get(0));
  }

  @Test
  void testCountHeadphones() {
    Headphones headphones1 = createHeadphones("Headphones 1", 20, 199.99, true);
    Headphones headphones2 = createHeadphones("Headphones 2", 30, 299.99, true);

    when(applianceService.getAllAppliances()).thenReturn(List.of(headphones1, headphones2));

    int count = headphonesService.countHeadphones();
    assertEquals(2, count);
  }

  private Headphones createHeadphones(String name, int batteryLife, double price, boolean noiseCancellation) {
    return Headphones.builder()
            .name(name)
            .brand("Brand")
            .price(price)
            .powerSource("Battery")
            .supportsWireless(true)
            .hasNoiseCancellation(noiseCancellation)
            .batteryLife(batteryLife)
            .build();
  }
}
