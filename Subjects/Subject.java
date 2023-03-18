package ProjectObserver.Subjects;

import ProjectObserver.Observers.Observer;

public interface Subject {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
