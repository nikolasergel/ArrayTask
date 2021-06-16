package by.sergel.entity;

import by.sergel.exception.ProcessingShipException;
import by.sergel.util.ShipIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ship implements Runnable {
    private static final Logger logger = LogManager.getLogger();
    private long id;
    private int maxSize;
    private int currentSize;
    private Task task;

    public Ship(int maxSize, int currentSize, Task task) {
        this.id = ShipIdGenerator.generateId();
        this.maxSize = maxSize;
        this.currentSize = currentSize;
        this.task = task;
    }

    public enum Task{
        LOAD, UNLOAD
    }

    public long getId() {
        return id;
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
        StringBuilder builder = new StringBuilder("Ship {")
                .append(" id=").append(id)
                .append(", maxSize=").append(maxSize)
                .append(", currentSize=").append(currentSize)
                .append(", task=").append(task).append(" }");
        return builder.toString();
    }
}
