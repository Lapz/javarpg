package Item;

public class Item {
    public final String name;
    public final int id;
    public int quantity = 1;

    public String getName() {
        return name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getId() {
        return id;
    }

    public Item(String name, int id) {
        this.name = name;
        this.id = id;

    }
}
