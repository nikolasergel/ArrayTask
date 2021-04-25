package by.serhel.shapestask.observer;

public interface Observable {
    void notifyObserver();
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
}
