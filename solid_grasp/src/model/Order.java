
package model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Item> items = new ArrayList<>();

    public void addItem(String name, double price) {
        items.add(new Item(name, price));
    }

    public double calculateTotal() {

        double total = 0;

        for (Item item : items) {
            total += item.getPrice();
        }

        return total;
    }

    public List<Item> getItems() {
        return items;
    }
}