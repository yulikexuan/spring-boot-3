//: sfg6lab.domain.model.Inventory.java

package sfg6lab.domain.model;


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

} ///:~