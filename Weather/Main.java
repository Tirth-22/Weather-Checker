package Weather;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Weather Information System");
        System.out.println("-------------------------");

        while (true) {
            System.out.print("\nEnter city name (or 'quit' to exit): ");
            String city = scanner.nextLine().trim();

            if (city.equalsIgnoreCase("quit")) {
                break;
            }

            if (city.isEmpty()) {
                System.out.println("Please enter a valid city name.");
                continue;
            }

            System.out.println("\nFetching weather data...");
            String result = WeatherFetcher.getWeather(city);
            System.out.println("\n" + result);
        }

        scanner.close();
        System.out.println("\nThank you for using the Weather Information System!");
    }
}