package by.mikhalachkin.electroshop;

import by.mikhalachkin.electroshop.model.electronics.SmartWatch;
import by.mikhalachkin.electroshop.model.electronics.Headphones;
import by.mikhalachkin.electroshop.service.ApplianceService;
import by.mikhalachkin.electroshop.service.electronics.SmartWatchService;
import by.mikhalachkin.electroshop.service.electronics.HeadphonesService;

public class Main {
  public static void main(String[] args) {
    // Создаем общий ApplianceService
    ApplianceService applianceService = new ApplianceService();

    // Создаем сервисы для устройств
    SmartWatchService smartWatchService = new SmartWatchService(applianceService);
    HeadphonesService headphonesService = new HeadphonesService(applianceService);

    // Создаем объекты SmartWatch с использованием Lombok Builder
    SmartWatch smartWatch1 = SmartWatch.builder()
            .name("Apple Watch Series 8")
            .brand("Apple")
            .price(399.99)
            .powerSource("Battery")
            .supportsWireless(true)
            .batteryLife(18)
            .isWaterResistant(true)
            .build();

    SmartWatch smartWatch2 = SmartWatch.builder()
            .name("Samsung Galaxy Watch 6")
            .brand("Samsung")
            .price(349.99)
            .powerSource("Battery")
            .supportsWireless(true)
            .batteryLife(24)
            .isWaterResistant(true)
            .build();

    // Добавляем умные часы в сервис
    smartWatchService.addSmartWatch(smartWatch1);
    smartWatchService.addSmartWatch(smartWatch2);

    // Выводим список всех умных часов
    System.out.println("All SmartWatches:");
    smartWatchService.getAllSmartWatches().forEach(System.out::println);

    // Найти водонепроницаемые умные часы
    System.out.println("\nWater-Resistant SmartWatches:");
    smartWatchService.findWaterResistant().forEach(System.out::println);

    // Создаем объекты Headphones с использованием Lombok Builder
    Headphones headphones1 = Headphones.builder()
            .name("Sony WH-1000XM5")
            .brand("Sony")
            .price(299.99)
            .powerSource("Battery")
            .supportsWireless(true)
            .hasNoiseCancellation(true)
            .batteryLife(30)
            .build();

    Headphones headphones2 = Headphones.builder()
            .name("Bose 700")
            .brand("Bose")
            .price(379.99)
            .powerSource("Battery")
            .supportsWireless(true)
            .hasNoiseCancellation(true)
            .batteryLife(20)
            .build();

    // Добавляем наушники в сервис
    headphonesService.addHeadphones(headphones1);
    headphonesService.addHeadphones(headphones2);

    // Выводим список всех наушников
    System.out.println("\nAll Headphones:");
    headphonesService.getAllHeadphones().forEach(System.out::println);

    // Сортируем наушники по времени работы от батареи
    System.out.println("\nHeadphones sorted by battery life:");
    headphonesService.sortByBatteryLife().forEach(System.out::println);
  }
}
