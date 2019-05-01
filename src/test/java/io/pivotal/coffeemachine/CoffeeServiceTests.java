package io.pivotal.coffeemachine;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link CoffeeService}.
 */
public class CoffeeServiceTests {

    private CoffeeService machine;

    private Inventory inventory;

    @Before
    public void setUp() {
        this.inventory = mock(Inventory.class);
        this.machine = new CoffeeService(this.inventory);
    }

    @Test
    public void getMenu() {
        Map<String, Double> menu = this.machine.getMenu();
        assertThat(menu).contains(entry("coffee", 2.75));
        assertThat(menu).contains(entry("cappuccino", 2.90));
        assertThat(menu).contains(entry("caffe mocha", 3.90));
    }

    @Test
    public void makeDrink() {
        this.machine.makeDrink("cappuccino");
        verify(this.inventory).deduct("coffee", 2);
        verify(this.inventory).deduct("sugar", 1);
        verify(this.inventory).deduct("cream", 1);
    }

    @Test
    public void createDrink() {
        Map<String, Integer> latteIngredients = new HashMap<String, Integer>();
        latteIngredients.put("coffee", 3);
        latteIngredients.put("sugar", 2);
        latteIngredients.put("cream", 2);
        this.machine.createDrink("latte", latteIngredients, 3.50);
        this.machine.makeDrink("latte");
        verify(this.inventory).deduct("coffee", 3);
        verify(this.inventory).deduct("sugar", 2);
        verify(this.inventory).deduct("cream", 2);
    }

}