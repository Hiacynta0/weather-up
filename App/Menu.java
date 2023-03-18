package ProjectObserver.App;

import ProjectObserver.Observers.Subscriber;
import ProjectObserver.Subjects.Sensor;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

public class Menu {

    private final Subscriber subscriber = new Subscriber();
    private JsonOperations operations;
    private AtomicBoolean generate = new AtomicBoolean(true);

    public Menu() {
        operations = new JsonOperations();
        operations.readJsonFromFile();
    }

    public void appInterface() {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        String choice;
        do {
            showMenu();
            choice = scanner.next();
            System.out.println();
            switch (choice) {

                case "1" -> {
                    System.out.println("Currently subscribed-------------");
                    showSubscribedLocations(subscriber);
                }

                case "2" -> {
                    ArrayList<Sensor> allSensors = operations.getAllSensorsArray();
                    System.out.println("Available locations--------------");
                    if (showAvailableSensors()) {
                        System.out.println("No more locations available");
                    } else {
                        System.out.printf("%s\n%s\n", "(Choose location to subscribe by", "writing its name.)");
                        boolean stop = false;
                        do {
                            String choice2 = scanner2.nextLine();
                            for (Sensor sensor : allSensors) {
                                if (sensor.getLocation().equals(choice2)) {
                                    if (sensor.getObservers().contains(subscriber))
                                        System.out.println("You've already subscribed this\nlocation!");
                                    else {
                                        sensor.registerObserver(subscriber);
                                        System.out.println(sensor.getLocation() + " is now subscribed");
                                    }
                                    stop = true;
                                }
                            }
                            if (!stop)
                                System.out.println("Sensor " + choice2 + " is unavailable\nPlease choose different one");
                        }
                        while (!stop);
                    }
                }

                case "3" -> {
                    System.out.println("Remove subscription--------------");
                    if (showSubscribedLocations(subscriber)) {
                        System.out.println("You can only unsubscribe\nlocations that are subscribed!");
                    } else {
                        System.out.printf("%s\n%s\n", "(Choose location to unsubscribe", "by writing its name.)");
                        Sensor sensor = returnSensorByLocation(scanner2.nextLine());
                        if (operations.getAllSensorsArray().contains(sensor)) {
                            sensor.removeObserver(subscriber);
                        }
                    }
                }

                case "4" -> {
                    System.out.println("Current weather------------------");
                    if (!subscriber.getNewWeatherNotifications().isEmpty())
                        for (WeatherGenerator newWeatherNotification : subscriber.getNewWeatherNotifications()) {
                            System.out.println(newWeatherNotification + "\n");
                        }
                    else System.out.println("No info available!");
                }

                case "5" -> {
                    boolean noStatistic = true;
                    System.out.println("Weather statistics---------------");
                    for (Sensor sensor : operations.getAllSensorsArray()) {
                        if (sensor.getObservers().contains(subscriber)) {
                            WeatherStatistics statistics = new WeatherStatistics();
                            statistics.showStatistics(sensor);
                            noStatistic = false;
                            System.out.println();
                        }
                    }
                    if (noStatistic) System.out.println("No statistics available!");
                }

                case "6" -> {
                    System.out.println("Save data------------------------");
                    operations.saveDataIntoJsonFile(subscriber);
                }

                case "7" -> {
                    System.out.println("Have a nice day!");
                    generate.compareAndSet(true, false);
                }

                default -> System.out.printf("%s\n%s\n", "Error! Please try choosing one of", "available options(1,2,3,4,5,6,7)");
            }
            System.out.println();
        }
        while (!choice.equals("7"));
    }

    public void showMenu() {
        System.out.println("---------------------------------");
        System.out.printf("%12s%s\n", "", "WeatherUp!");
        System.out.println("---------------------------------");
        System.out.println("1 List of subscribed locations---");
        System.out.println("2 Subscribe to a new location----");
        System.out.println("3 Unsubscribe location-----------");
        System.out.println("4 Check the weather--------------");
        System.out.println("5 Check statistics---------------");
        System.out.println("6 Save data----------------------");
        System.out.println("7 Exit---------------------------");
    }

    public boolean showAvailableSensors() {
        ArrayList<Sensor> allSensors = operations.getAllSensorsArray();
        boolean empty = true;
        for (Sensor sensor : allSensors) {
            if (!sensor.getObservers().contains(subscriber)) {
                System.out.println(sensor);
                empty = false;
            }
        }
        return empty;
    }

    public boolean showSubscribedLocations(Subscriber subscriber) {
        boolean empty = true;
        for (Sensor sensor : operations.getAllSensorsArray()) {
            if (sensor.getObservers().contains(subscriber)) {
                System.out.println(sensor);
                empty = false;
            }
        }
        if (empty) System.out.println("No currently subscribed locations");
        return empty;
    }

    public Sensor returnSensorByLocation(String locationName) {
        for (Sensor sensor : operations.getAllSensorsArray()) {
            if (sensor.getLocation().equals(locationName)) return sensor;
        }
        return new Sensor("", "");
    }

    public void startGeneratingMeasurements() {
        ArrayList<Sensor> sensors = operations.getAllSensorsArray();
        while (generate.get()) {
            for (Sensor sensor : sensors) {
                sensor.newWeatherAvailable();
            }
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}





