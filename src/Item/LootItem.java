package Item;

public class LootItem extends Item {
    private int dropPrecentage;
    private boolean defaultItem;

    public LootItem(String name,int dropPrecentage,boolean defaultItem,int id) {
        super(name,id);
        this.dropPrecentage = dropPrecentage;
        this.defaultItem = defaultItem;
    }

    public String toString() {
        return this.name;
    }
}
