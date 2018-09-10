package Item;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    // Item and qunatiy
    private Map<Item,Integer> items;

    public Inventory(Item... initialItems) {

        this.items = new HashMap<Item, Integer>();
        for (Item item : initialItems) {
            this.items.put(item,1);
        }
    }


    public boolean containsValue(int id) {
        return this.items.containsValue(id);
    }

    public void addItem(Item item,int quantity) {
        this.items.put(item,quantity);
    }


    public String toString() {
        String debug_repr = "Item\tQuantity";

        for (Map.Entry<Item,Integer> entry: items.entrySet()) {
            debug_repr +=  "\n"+entry.getKey()+"\t"+entry.getValue();
        }

        return debug_repr;
    }
}
