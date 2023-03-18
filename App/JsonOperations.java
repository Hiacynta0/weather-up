package ProjectObserver.App;

import ProjectObserver.Observers.Subscriber;
import ProjectObserver.Subjects.Sensor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonOperations {

    private ArrayList<Sensor> sensorArrayList;

    public void readJsonFromFile() {
        String jsonToString = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("SensorsLocations.json"));
            while (reader.ready()) {
                jsonToString += reader.readLine();
            }
            reader.close();

            Gson gson = new Gson();
            Type locationsList = new TypeToken<ArrayList<Sensor>>() {
            }.getType();
            ArrayList<Sensor> sensorList = gson.fromJson(jsonToString, locationsList);
            ArrayList<Sensor> newSensorList = new ArrayList<>();
            for (Sensor sensor : sensorList) {
                newSensorList.add(new Sensor(sensor.getLocation(), sensor.getSensorKind()));
            }
            this.sensorArrayList = newSensorList;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Sensor> getAllSensorsArray() {
        return sensorArrayList;
    }

    public void saveDataIntoJsonFile(Subscriber subscriber) {
        Gson gson = new Gson();
        ArrayList<ArrayList<WeatherGenerator>> weatherData = new ArrayList<>();
        for (Sensor sensor : this.getAllSensorsArray()) {
            if (sensor.getObservers().contains(subscriber)) {
                weatherData.add(sensor.getMeasurementsList());
            }
        }
        if (weatherData.isEmpty()) System.out.println("No data to save available");
        else {
            try {
                FileWriter writer = new FileWriter("savedWeatherData.json");
                gson.toJson(weatherData, writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Data will save after exiting app");
        }
    }
}
