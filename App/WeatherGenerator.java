package ProjectObserver.App;

import ProjectObserver.Subjects.Sensor;

import java.time.LocalDateTime;
import java.util.Random;

public class WeatherGenerator {

    private String location;
    private String time;
    private int temperature = -100;
    private int pressure = -100;
    private int humidity = -100;


    public WeatherGenerator(int temperature, int pressure, int humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public WeatherGenerator(Sensor sensor) {
        Random random = new Random();
        if ((sensor.getSensorKind().contains("T"))) {
            temperature = random.nextInt(30);
        }
        if (sensor.getSensorKind().contains("P")) {
            pressure = 1030 - random.nextInt(30);
        }
        if (sensor.getSensorKind().contains("H"))
            humidity = 90 - random.nextInt(30);

        time = String.valueOf(LocalDateTime.now()).substring(11, 19);
        location = sensor.getLocation();
    }

    @Override
    public String toString() {
        String temperatureValue = "temperature: T";
        String pressureValue = "pressure: P";
        String humidityValue = "humidity: H";

        if (this.temperature == -100) temperatureValue = temperatureValue.replace("T", "unavailable");
        else temperatureValue = temperatureValue.replace("T", String.valueOf(temperature));

        if (pressure == -100) pressureValue = pressureValue.replace("P", "unavailable");
        else pressureValue = pressureValue.replace("P", String.valueOf(pressure));

        if (humidity == -100) humidityValue = humidityValue.replace("H", "unavailable");
        else humidityValue = humidityValue.replace("H", String.valueOf(humidity));

        return location + " " + time + "\n  " + temperatureValue + "\n  " + pressureValue + "\n  " + humidityValue;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getTime() {
        return time;
    }
}
