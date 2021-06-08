package by.sergel.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    private static final Logger logger = LogManager.getLogger();
    private final int DEFAULT_MAX_SIZE = 500;
    private final int PIERS_NUMBER = 2;
    private final int maxSize;
    private final int minSize = 0;
    private int currentSize;
    private Queue<Pier> piersQueue;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private Port() {
        this.maxSize = DEFAULT_MAX_SIZE;
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
            try{
                while(piersQueue.isEmpty()){
                    condition.await();
                }
            } catch (InterruptedException e){
                e.printStackTrace();//TODO
            }
            return piersQueue.remove();
        } finally {
            lock.unlock();
        }
    }

    public void releasePier(Pier pier) {
        try {
            lock.lock();
            System.out.println("current size:" + currentSize);
            condition.signalAll();
            piersQueue.add(pier);
        } finally {
            lock.unlock();
        }
    }

    public boolean get(int goods){
        return add(-goods);
    }

    public boolean put(int goods){
        return add(goods);
    }

    private boolean add(int goods) {
        boolean flag = false;
        try {
            lock.lock();
            int buff = currentSize + goods;
            if (buff >= minSize && buff <= maxSize) {
                currentSize = buff;
                flag = true;
            }
        } finally {
            System.out.println("current size:" + currentSize);
            lock.unlock();
        }
        return flag;
    }

    private static class PortHolder {
        private static final Port instance = new Port();
    }
}