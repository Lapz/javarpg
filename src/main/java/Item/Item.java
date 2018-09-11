package Item;

public class Item {
    public String name;
    public int id;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Item(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
