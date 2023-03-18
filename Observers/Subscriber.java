package ProjectObserver.Observers;

import ProjectObserver.App.WeatherGenerator;

import java.util.ArrayList;

public class Subscriber implements Observer {

    private ArrayList<WeatherGenerator> newWeatherNotifications;

    public Subscriber() {
        newWeatherNotifications = new ArrayList<>();
    }

    public ArrayList<WeatherGenerator> getNewWeatherNotifications() {
        return newWeatherNotifications;
    }

    @Override
    public void update(WeatherGenerator currentWeather) {
        if (!newWeatherNotifications.isEmpty() && !newWeatherNotifications.get(0).getTime().equals(currentWeather.getTime())) {
            newWeatherNotifications.clear();
        }
        newWeatherNotifications.add(currentWeather);
    }
}
