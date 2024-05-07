//: sfg6lab.domain.model.ThreadSafeInventory.java

package sfg6lab.domain.model;


import java.util.concurrent.locks.ReentrantLock;


class ThreadSafeInventory implements Inventory {

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

} ///:~