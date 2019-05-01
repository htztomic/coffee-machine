package io.pivotal.coffeemachine;

import java.util.HashMap;
import java.util.Map;

public class CoffeeService {

    private Inventory inventory;
    private Map<String, Double> costMenu;
    private Map<String, Map<String, Integer>> ingredientsMenu;

    public CoffeeService(Inventory inventory) {
        this.inventory = inventory;
        costMenu = new HashMap<String, Double>();
        ingredientsMenu = new HashMap<String, Map<String, Integer>>();
        //Establishes each drink name associated to its cost and ingredients
        //using a one to one correlation between each array
        String[] drinkNames = new String[]{"coffee", "cappuccino", "caffe mocha"};
        Double[] drinkCosts = new Double[]{2.75, 2.90, 3.90};
        Integer[][] ingredients = new Integer[][]{{2, 1}, {2, 1, 1}, {1, 1, 1}};
        String[] possibleIngredients = new String[]{"coffee", "sugar", "cream"};

        for (int i = 0; i < drinkNames.length; i++) {
            Integer[] ingredient = ingredients[i];
            Map<String, Integer> drinkIngredients = new HashMap<String, Integer>();
            for (int j = 0; j < ingredient.length; j++) {
                drinkIngredients.put(possibleIngredients[j], ingredient[j]);
            }
            createDrink(drinkNames[i], drinkIngredients, drinkCosts[i]);
        }
    }

    /**
     * Returns the menu for this coffee machine.
     *
     * @return a map of drink name to drink cost
     */
    public Map<String, Double> getMenu() {
        return costMenu;
    }

    /**
     * Make a drink using the given name. Ingredients for the drink are deducted from the inventory.
     *
     * @param name the name of the drink
     */
    public Drink makeDrink(String name) {
        if (!ingredientsMenu.containsKey(name)) {
            return null;
        }
        Map<String, Integer> ingredients = ingredientsMenu.get(name);
        //Deducts the drink's ingredients from the inventory
        for (String key : ingredients.keySet()) {
            try {
                inventory.deduct(key, ingredients.get(key));
            } catch (IllegalArgumentException i) {
                return null;
            }
        }
        Drink resultingDrink = new Drink();
        resultingDrink.setIngredients(ingredients);
        resultingDrink.setCost(costMenu.get(name));
        resultingDrink.setName(name);

        return resultingDrink;
    }

    /**
     * Create a new drink given a name, ingredients, and cost
     */

    public void createDrink(String name, Map<String, Integer> ingredients, Double cost) {
        costMenu.put(name, cost);
        ingredientsMenu.put(name, ingredients);
    }

}
