package by.sergel.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Ship implements Runnable {
    private static final Logger logger = LogManager.getLogger();
    private static int WORKING_TIME = 3;
    private Warehouse warehouse;
    private int maxSize;
    private int currentSize;
    private int loadingSize;
    private boolean loading;
    private boolean done;

    public Ship(int maxSize, int currentSize, int loadingSize, boolean loading) {
        this.warehouse = Warehouse.getInstance();
        this.currentSize = currentSize;
        this.maxSize = maxSize;
        this.loadingSize = loadingSize;
        this.loading = loading;
    }

    @Override
    public void run() {
        while (!done) {
            AbstractPort port = warehouse.acquirePort(this);
            if (port != null) {
                if (loading) {
                    load(port);
                } else {
                    unload(port);
                }
                done = true;
                warehouse.releasePort(port);
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getLoading() {
        return loading;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    private void load(AbstractPort port) {
        while (maxSize != currentSize) {
            int goods;
            logger.info("Loading, current size = " + currentSize + ", max size = " + maxSize);
            if (currentSize + loadingSize < maxSize && port.get(loadingSize)) {
                goods = loadingSize;
            } else {
                goods = maxSize - currentSize;
            }
            port.get(goods);
            currentSize += goods;
            try {
                TimeUnit.SECONDS.sleep(WORKING_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void unload(AbstractPort port) {
        while (currentSize != 0) {
            int goods;
            logger.info("Unloading, current size = " + currentSize + ", max size = " + maxSize);
            if (currentSize - loadingSize >= 0) {
                goods = loadingSize;
            } else {
                goods = currentSize;
            }
            port.put(goods);
            currentSize -= goods;
            try {
                TimeUnit.SECONDS.sleep(WORKING_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Ship{" +
                ", maxSize=" + maxSize +
                ", currentSize=" + currentSize +
                ", loadingSize=" + loadingSize +
                ", loading=" + loading +
                ", done=" + done +
                '}';
    }
}
