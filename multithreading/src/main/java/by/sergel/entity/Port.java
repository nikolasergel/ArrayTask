package by.sergel.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    private static final Logger logger = LogManager.getLogger();
    private final int DEFAULT_MAX_SIZE = 500;
    private final int DEFAULT_CURRENT_SIZE = 250;
    private final int DEFAULT_PIER_NUMBER = 4;
    private final String MAX_SIZE_PROPERTY = "maxSize";
    private final String CURRENT_SIZE_PROPERTY = "currentSize";
    private final String PIER_NUMBER_PROPERTY = "pierNumber";

    private int pierNumber;
    private int maxSize;
    private int minSize;
    private int currentSize;
    private Queue<Pier> piersQueue;
    private Lock lock = new ReentrantLock();
    private Condition acquireCondition = lock.newCondition();
    private Condition putCondition = lock.newCondition();
    private Condition getCondition = lock.newCondition();

    private Port() {
        this.pierNumber = DEFAULT_PIER_NUMBER;
        this.maxSize = DEFAULT_MAX_SIZE;
        this.currentSize = DEFAULT_CURRENT_SIZE;

        URL url =  getClass().getClassLoader().getResource("port.properties");

        if(url != null){
            String path = url.getPath();

            try (InputStream inputStream = new FileInputStream(path)){
                Properties props = new Properties();
                props.load(inputStream);

                String defaultMaxSize = String.valueOf(DEFAULT_MAX_SIZE);
                maxSize = Integer.parseInt(props.getProperty(MAX_SIZE_PROPERTY, defaultMaxSize));

                String defaultCurrentSize = String.valueOf(DEFAULT_CURRENT_SIZE);
                currentSize = Integer.parseInt(props.getProperty(CURRENT_SIZE_PROPERTY, defaultCurrentSize));

                String defaultPierNumber = String.valueOf(DEFAULT_PIER_NUMBER);
                pierNumber = Integer.parseInt(props.getProperty(PIER_NUMBER_PROPERTY, defaultPierNumber));
            } catch (IOException | NumberFormatException e ) {
                logger.error("Can't open properties file or properties isn't valid: " + path);
                logger.warn("Using default values:\n\tmaxSize=" + maxSize + "\n\tcurrentSize=" + currentSize
                                + "\n\tpierNumber=" + pierNumber);
            };
        }

        this.piersQueue = new ArrayDeque<>();
        for (int i = 0; i < pierNumber; i++) {
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