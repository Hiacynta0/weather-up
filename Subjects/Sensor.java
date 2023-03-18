package ProjectObserver.Subjects;

import ProjectObserver.App.WeatherGenerator;
import ProjectObserver.Observers.Observer;

import java.util.ArrayList;
import java.util.Objects;

public class Sensor implements Subject {

    private final String location;
    private final String sensorKind;
    public ArrayList<WeatherGenerator> measurementsList = new ArrayList<>();
    private ArrayList<Observer> observers = new ArrayList<>();
    private WeatherGenerator currentWeather;

    public Sensor(String location, String sensorKind) {
        this.location = location;
        this.sensorKind = sensorKind;
    }

    public void addMeasurements(WeatherGenerator newMeasurements) {
        measurementsList.add(newMeasurements);
    }

    public String getLocation() {
        return location;
    }

    public String getSensorKind() {
        return sensorKind;
    }

    public ArrayList<WeatherGenerator> getMeasurementsList() {
        return measurementsList;
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public void setCurrentWeather(WeatherGenerator currentWeather) {
        this.currentWeather = currentWeather;
    }

    @Override
    public String toString() {
        return location + ", " + sensorKind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(location, sensor.location) &&
                Objects.equals(sensorKind, sensor.sensorKind);
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(currentWeather);
        }
    }

    public void newWeatherAvailable() {
        setCurrentWeather(new WeatherGenerator((this)));
        this.addMeasurements(currentWeather);
        notifyObservers();
    }
}
