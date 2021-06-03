package by.sergel.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Warehouse {
    private static final Logger logger = LogManager.getLogger();
    private final int DEFAULT_MAX_SIZE = 500;
    private final int PORT_NUMBER = 4;
    private int minSize;
    private int maxSize;
    private int currentSize;
    private AbstractPort[] ports;
    private Semaphore semaphore = new Semaphore(PORT_NUMBER, true);
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private Warehouse() {
        this.maxSize = DEFAULT_MAX_SIZE;
        this.ports = new Port[PORT_NUMBER];
        for (int i = 0; i < PORT_NUMBER; i++) {
            ports[i] = new Port();
        }
    }

    public static Warehouse getInstance() {
        return WarehouseHolder.INSTANCE;
    }

    public AbstractPort acquirePort(Ship ship) {
        try {
            if (!ship.getLoading() && ship.getCurrentSize() + currentSize < maxSize
                    || ship.getLoading() && currentSize - ship.getCurrentSize() > minSize) {
                semaphore.acquire();
                for (int i = 0; i < PORT_NUMBER; i++) {
                    AbstractPort port = ports[i];
                    if (port.setAvailable(false)) {
                        logger.info("acquired port number " + i);
                        return port;
                    }
                }
                semaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void releasePort(AbstractPort port) {
        logger.info("released port");
        port.setAvailable(true);
        semaphore.release();
    }

    private boolean updateSize(int number) {
        boolean flag = false;
        lock.lock();
        try {
            int buff = currentSize + number;
            if (buff > minSize && buff < maxSize) {
                currentSize += number;
                flag = true;
                condition.signalAll();
            } else {
                condition.await();
            }
        } catch (InterruptedException e) {
            logger.warn("Interruption in " + Thread.currentThread().getName(), e);
        } finally {
            logger.info("Warehouse's current size = " + currentSize);
            lock.unlock();
        }
        return flag;
    }

    private class Port extends AbstractPort {
        @Override
        public boolean put(int goods) {
            return updateSize(goods);
        }

        @Override
        public boolean get(int goods) {
            return updateSize(-goods);
        }
    }

    private static class WarehouseHolder {
        private static final Warehouse INSTANCE = new Warehouse();
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "DEFAULT_MAX_SIZE=" + DEFAULT_MAX_SIZE +
                ", PORT_NUMBER=" + PORT_NUMBER +
                ", minSize=" + minSize +
                ", maxSize=" + maxSize +
                ", currentSize=" + currentSize +
                ", ports=" + Arrays.toString(ports) +
                ", semaphore=" + semaphore +
                ", lock=" + lock +
                ", condition=" + condition +
                '}';
    }
}
