package ProjectObserver.Tests;

import ProjectObserver.Observers.Subscriber;
import ProjectObserver.Subjects.Sensor;
import org.testng.annotations.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ObserverPatternTest {

    private Sensor sensor = new Sensor("Test", "T");
    private Subscriber subscriber = new Subscriber();

    @Test
    public void testRegisterObserver() {
        sensor.registerObserver(new Subscriber());
        assertFalse(sensor.getObservers().isEmpty());
    }

    @Test
    public void testUnregisterObserver() {
        sensor.registerObserver(subscriber);
        sensor.removeObserver(subscriber);
        assertTrue(sensor.getObservers().isEmpty());
    }

    @Test
    public void testNotifyObservers() {
        sensor.registerObserver(subscriber);
        sensor.newWeatherAvailable();
        assertFalse(subscriber.getNewWeatherNotifications().isEmpty());
    }
}
