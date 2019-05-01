package io.pivotal.coffeemachine;

public class InventoryTest extends InventoryTests{
    protected Inventory getInventory() {
        return new MainInventory();
    }
}
