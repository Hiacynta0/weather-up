package ProjectObserver.App;

import ProjectObserver.Subjects.Sensor;

import java.util.ArrayList;

public class WeatherStatistics {

    private ArrayList<WeatherGenerator> measurements;

    public void showStatistics(Sensor sensor) {
        setMeasurements(sensor.getMeasurementsList());
        System.out.println(sensor.getLocation());
        System.out.println("  Temperature:");
        System.out.println(temperatureStatistics());
        System.out.println("  Pressure:");
        System.out.println(pressureStatistics());
        System.out.println("  Humidity:");
        System.out.println(humidityStatistics());
    }

    public String temperatureStatistics() {
        if (measurements.get(0).getTemperature() == -100) return "    Unavailable in this location";
        else {
            int maxTemperature = -100;
            int minTemperature = 100;
            int temperatureSum = 0;

            for (WeatherGenerator measurement : measurements) {
                if (measurement.getTemperature() > maxTemperature) maxTemperature = measurement.getTemperature();
                if (measurement.getTemperature() < minTemperature) minTemperature = measurement.getTemperature();
                temperatureSum += measurement.getTemperature();
            }
            return "    MAX: " + maxTemperature + "\n    MIN: " + minTemperature + "\n    AVG: " + temperatureSum / measurements.size();
        }
    }

    public String pressureStatistics() {
        if (measurements.get(0).getPressure() == -100) return "    Unavailable in this location";
        else {
            int maxPressure = -100;
            int minPressure = 1030;
            int pressureSum = 0;

            for (WeatherGenerator measurement : measurements) {
                if (measurement.getPressure() > maxPressure) maxPressure = measurement.getPressure();
                if (measurement.getPressure() < minPressure) minPressure = measurement.getPressure();
                pressureSum += measurement.getPressure();
            }
            return "    MAX: " + maxPressure + "\n    MIN: " + minPressure + "\n    AVG: " + pressureSum / measurements.size();
        }
    }

    public String humidityStatistics() {
        if (measurements.get(0).getHumidity() == -100) return "    Unavailable in this location";
        else {
            int maxHumidity = -100;
            int minHumidity = 100;
            int humiditySum = 0;

            for (WeatherGenerator measurement : measurements) {
                if (measurement.getHumidity() > maxHumidity) maxHumidity = measurement.getHumidity();
                if (measurement.getHumidity() < minHumidity) minHumidity = measurement.getHumidity();
                humiditySum += measurement.getHumidity();
            }
            return "    MAX: " + maxHumidity + "\n    MIN: " + minHumidity + "\n    AVG: " + humiditySum / measurements.size();
        }
    }

    public void setMeasurements(ArrayList<WeatherGenerator> measurements) {
        this.measurements = measurements;
    }
}
