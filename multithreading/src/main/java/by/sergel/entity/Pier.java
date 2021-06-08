package by.sergel.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Pier {
    private static final Logger logger = LogManager.getLogger();
    public static final int SECONDS_SLEEPING_TIME = 1;
    public static final int MAX_LOADING_SIZE = 5;
    private final Port port;

    public Pier(Port port) {
        this.port = port;
    }

    public void processShip(Ship ship) {
        int goods = 0;
        switch (ship.getTask()){
            case LOAD -> {
                goods = ship.getMaxSize() - ship.getCurrentSize();
                logger.info("-" + goods);
//                System.out.println(Thread.currentThread().getName() + ": " + "-" + goods);
                port.get(goods);
            }
            case UNLOAD -> {
                goods = ship.getCurrentSize();
                logger.info("+" + goods);
//                System.out.println(Thread.currentThread().getName() + ": " + "+" + goods);
                port.put(goods);
            }
        }
        try {
            int sleepingTime = SECONDS_SLEEPING_TIME * goods / MAX_LOADING_SIZE;
            TimeUnit.SECONDS.sleep(sleepingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();//TODO
        }//todo
    }
}
