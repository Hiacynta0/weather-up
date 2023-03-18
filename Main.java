package ProjectObserver;

import ProjectObserver.App.Menu;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Menu menu = new Menu();
        Thread thread = new Thread(menu::startGeneratingMeasurements);
        thread.start();
        menu.appInterface();
        thread.join();
    }
}
