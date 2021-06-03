package by.sergel.entity;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractPort {
    private AtomicBoolean available = new AtomicBoolean(true);

    public AbstractPort() {
    }

    public abstract boolean put(int goods);

    public abstract boolean get(int goods);

    public boolean setAvailable(boolean value) {
        return available.compareAndSet(!value, value);
    }
}
