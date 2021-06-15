package by.sergel.entity;

import by.sergel.exception.ProcessingShipException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ship implements Runnable {
    private static final Logger logger = LogManager.getLogger();
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
        try{
            pier.processShip(this);
        } catch (ProcessingShipException e) {
            logger.warn("Can't process ship. Interrupting thread!\n" + this, e);
        } finally {
            port.releasePier(pier);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Ship{");
        builder.append("maxSize=").append(maxSize);
        builder.append(", currentSize=").append(currentSize);
        builder.append(", task=").append(task).append(" }");
        return builder.toString();
    }
}
