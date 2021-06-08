package by.sergel.entity;

public class Ship implements Runnable {
    private int maxSize;
    private int currentSize;
    private Task task;

    public Ship(int maxSize, int currentSize, Task task) {
        this.maxSize = maxSize;
        this.currentSize = currentSize;
        this.task = task;
    }

    public enum Task{
        LOAD, UNLOAD
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public Task getTask() {
        return task;
    }

    @Override
    public void run() {
        Port port = Port.getInstance();
        Pier pier = port.acquirePort(this);
        pier.processShip(this);
        port.releasePier(pier);
    }
}
