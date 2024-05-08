package sfg6lab.domain.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import sfg6lab.domain.model.Inventory;


public interface InventoryService {

    int AMOUNT_OF_CHANGES = 1000;

    void changeInventory();

    static InventoryService incrementingService(Inventory inventory) {
        return InventoryIncrementingService.of(inventory);
    }

    static InventoryService decrementingService(Inventory inventory) {
        return InventoryDecrementingService.of(inventory);
    }
}


@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
final class InventoryIncrementingService implements InventoryService {

    private final Inventory inventory;

    @Override
    public void changeInventory() {
        for (int i = 0; i < AMOUNT_OF_CHANGES; i++) {
            this.inventory.increase();
        }
    }
}


@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
final class InventoryDecrementingService implements InventoryService {

    private final Inventory inventory;

    @Override
    public void changeInventory() {
        for (int i = 0; i < AMOUNT_OF_CHANGES; i++) {
            this.inventory.decrease();
        }
    }
}
