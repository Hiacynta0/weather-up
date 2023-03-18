package ProjectObserver.Tests;

import ProjectObserver.App.WeatherGenerator;
import ProjectObserver.App.WeatherStatistics;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class WeatherStatisticsTest {

    public ArrayList<WeatherGenerator> allMeasurementsAvailableList() {
        ArrayList<WeatherGenerator> allMeasurementsList = new ArrayList<>();
        allMeasurementsList.add(new WeatherGenerator(5, 1020, 70));
        allMeasurementsList.add(new WeatherGenerator(10, 1010, 60));
        allMeasurementsList.add(new WeatherGenerator(15, 1030, 80));
        return allMeasurementsList;
    }

    public ArrayList<WeatherGenerator> noMeasurementsAvailableList() {
        ArrayList<WeatherGenerator> allMeasurementsList = new ArrayList<>();
        allMeasurementsList.add(new WeatherGenerator(-100, -100, -100));
        allMeasurementsList.add(new WeatherGenerator(-100, -100, -100));
        allMeasurementsList.add(new WeatherGenerator(-100, -100, -100));
        return allMeasurementsList;
    }

    @Test
    public void testTemperatureStatistics() {
        WeatherStatistics weatherStatisticsTest = new WeatherStatistics();
        weatherStatisticsTest.setMeasurements(allMeasurementsAvailableList());
        assertEquals("    MAX: 15\n    MIN: 5\n    AVG: 10", weatherStatisticsTest.temperatureStatistics());
    }

    @Test
    public void testTemperatureStatisticsUnavailable() {
        WeatherStatistics weatherStatisticsTest = new WeatherStatistics();
        weatherStatisticsTest.setMeasurements(noMeasurementsAvailableList());
        assertEquals("    Unavailable in this location", weatherStatisticsTest.temperatureStatistics());
    }

    @Test
    public void testPressureStatistics() {
        WeatherStatistics weatherStatisticsTest = new WeatherStatistics();
        weatherStatisticsTest.setMeasurements(allMeasurementsAvailableList());
        assertEquals("    MAX: 1030\n    MIN: 1010\n    AVG: 1020", weatherStatisticsTest.pressureStatistics());
    }

    @Test
    public void testPressureStatisticsUnavailable() {
        WeatherStatistics weatherStatisticsTest = new WeatherStatistics();
        weatherStatisticsTest.setMeasurements(noMeasurementsAvailableList());
        assertEquals("    Unavailable in this location", weatherStatisticsTest.pressureStatistics());
    }

    @Test
    public void testHumidityStatistics() {
        WeatherStatistics weatherStatisticsTest = new WeatherStatistics();
        weatherStatisticsTest.setMeasurements(allMeasurementsAvailableList());
        assertEquals("    MAX: 80\n    MIN: 60\n    AVG: 70", weatherStatisticsTest.humidityStatistics());
    }

    @Test
    public void testHumidityStatisticsUnavailable() {
        WeatherStatistics weatherStatisticsTest = new WeatherStatistics();
        weatherStatisticsTest.setMeasurements(noMeasurementsAvailableList());
        assertEquals("    Unavailable in this location", weatherStatisticsTest.humidityStatistics());
    }
}
