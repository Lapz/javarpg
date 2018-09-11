package Item;

public class LootItem extends Item {
    private int dropPrecentage;
    private boolean defaultItem;

    public LootItem(Item item,int percentage,boolean b) {
        super(item.name,item.id);
        this.dropPrecentage = percentage;
        this.defaultItem = b;
    }


    public String toString() {
        return this.name;
    }
}
