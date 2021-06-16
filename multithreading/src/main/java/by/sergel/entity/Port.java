package by.sergel.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    private static final Logger logger = LogManager.getLogger();
    public final int DEFAULT_MAX_SIZE = 500;
    public final int DEFAULT_CURRENT_SIZE = 250;
    public final int PIERS_NUMBER = 4;
    private final int maxSize;
    private final int minSize = 0;
    private int currentSize;
    private Queue<Pier> piersQueue;
    private Lock lock = new ReentrantLock();
    private Condition acquireCondition = lock.newCondition();
    private Condition putCondition = lock.newCondition();
    private Condition getCondition = lock.newCondition();

    private Port() {
        this.maxSize = DEFAULT_MAX_SIZE;
        this.currentSize = DEFAULT_CURRENT_SIZE;
        this.piersQueue = new ArrayDeque<>();
        for (int i = 0; i < PIERS_NUMBER; i++) {
            piersQueue.add(new Pier(this));
        }
    }

    public static Port getInstance() {
        return PortHolder.instance;
    }

    public Pier acquirePort(Ship ship) {
        lock.lock();
        try {
            while (piersQueue.isEmpty()) {
                try {
                    acquireCondition.await();
                } catch (InterruptedException e) {
                    logger.warn("Thread was interrupted when acquiring a port.", e.getCause());
                }
            }
            return piersQueue.remove();
        }finally {
            lock.unlock();
        }
    }

    public void releasePier(Pier pier) {
        try {
            lock.lock();
            logger.info("Pier is releasing.");
            piersQueue.add(pier);
            acquireCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public boolean get(int goods){
        return add(-goods, putCondition, getCondition);
    }

    public boolean put(int goods){
        return add(goods, getCondition, putCondition);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Port{ maxSize=").append(maxSize)
                .append(", minSize=").append(minSize)
                .append(", currentSize=").append(currentSize)
                .append(", piersQueue=").append(piersQueue).append("}");
        return builder.toString();
    }

    private boolean add(int goods, Condition firstCondition, Condition secondCondition) {
        boolean flag = false;
        try {
            lock.lock();
            int buff = currentSize + goods;
            if (buff >= minSize && buff <= maxSize) {
                currentSize = buff;
                flag = true;
                firstCondition.signalAll();
            } else{
                try {
                    secondCondition.await();
                } catch (InterruptedException e) {
                    logger.error("Thread was interrupted in adding method. Goods count: " + goods, e.getCause());
                    Thread.currentThread().interrupt();
                }
            }
        } finally {
            logger.info("Current port size: " + currentSize);
            lock.unlock();
        }
        return flag;
    }

    private static class PortHolder {
        private static final Port instance = new Port();
    }
}