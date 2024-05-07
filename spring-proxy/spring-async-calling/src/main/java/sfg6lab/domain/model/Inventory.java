package sfg6lab.domain.model;


public interface Inventory {

    void increment();

    void decrement();

    int items();

    static Inventory threadSafeInventory() {
        return new ThreadSafeInventory();
    }

    static Inventory simpleInventory() {
        return new SimpleInventory();
    }

}
