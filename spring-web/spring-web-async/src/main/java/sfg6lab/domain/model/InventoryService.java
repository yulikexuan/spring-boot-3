//: sfg6lab.domain.model.ResourceSharing.java

package sfg6lab.domain.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;


class InventoryService {

    public static final int LOOP_COUNT = 1000;

    @AllArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
    static class InventoryIncrementingService extends Thread {

        private final Inventory inventory;

        @Override
        public void run() {

            for (int i = 0; i < LOOP_COUNT; i++) {
                this.inventory.increment();
            }
        }

    }

    @AllArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
    static class InventoryDecrementingService extends Thread {

        private final Inventory inventory;

        @Override
        public void run() {

            for (int i = 0; i < LOOP_COUNT; i++) {
                this.inventory.decrement();
            }
        }

    }

    static class Inventory {

        private int items = 0;

        void increment() {
            items++;
        }

        void decrement() {
            items--;
        }

        int items() {
            return items;
        }
    }

} ///:~