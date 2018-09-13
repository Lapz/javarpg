package Item;

public class LootItem extends Item {
    private final int dropPrecentage;
    private final boolean defaultItem;

    public int getDropPrecentage() {
        return dropPrecentage;
    }

    public boolean isDefaultItem() {
        return defaultItem;
    }

    public LootItem(Item item, int percentage, boolean b) {
        super(item.name,item.id);
        this.dropPrecentage = percentage;
        this.defaultItem = b;
    }

}
