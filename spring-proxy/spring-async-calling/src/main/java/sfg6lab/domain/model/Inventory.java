package sfg6lab.domain.model;


import java.util.concurrent.locks.ReentrantLock;


public interface Inventory {

    void increment();

    void decrement();

    int items();

    static Inventory simpleInventory() {
        return new SimpleInventory();
    }

    static Inventory safeInventory() {
        return new SafeInventory();
    }
}


class SimpleInventory implements Inventory {

    private int items = 0;

    @Override
    public void increment() {
        items++;
    }

    @Override
    public void decrement() {
        items--;
    }

    @Override
    public int items() {
        return items;
    }

}


class SafeInventory implements Inventory {

    private int items = 0;

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void increment() {

        try {
            this.lock.lockInterruptibly();
            items++;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            this.lock.unlock();
        }
    }

    @Override
    public void decrement() {
        try {
            this.lock.lockInterruptibly();
            items--;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            this.lock.unlock();
        }
    }

    @Override
    public int items() {
        return items;
    }

}
