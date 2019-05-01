package io.pivotal.coffeemachine;

import java.util.HashMap;
import java.util.Map;

public class MainInventory implements Inventory {

    private Map<String, Integer> inventory;

    public MainInventory() {
        inventory = new HashMap<String, Integer>();
        inventory.put("coffee", 10);
        inventory.put("sugar", 10);
        inventory.put("cream", 10);
    }

    public Map<String, Integer> getIngredients() {
        return inventory;
    }

    public void deduct(String name, Integer amount) {
        if (!inventory.containsKey(name)) {
            throw new IllegalArgumentException(name + " is not part of the inventory");
        }
        Integer initialAmount = inventory.get(name);

        if (initialAmount - amount < 0) {
            throw new IllegalArgumentException("Unable to deduct " + amount + " from " + initialAmount);
        }

        inventory.put(name, initialAmount - amount);
    }
}
