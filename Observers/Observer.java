package ProjectObserver.Observers;

import ProjectObserver.App.WeatherGenerator;

public interface Observer {

    void update(WeatherGenerator currentWeather);
}
