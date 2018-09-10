import Item.Inventory;
import Item.Item;

import java.util.Map;

public class Player {
    private String name;
    private int hp;
    private int max_hp;
    private int gold;
    private int xp;
    private int level;
    private Inventory inventory;
    private Map<Integer,Quest> quests;

    public boolean isAlive() {
        return this.hp >= 0;
    }


    public void heal(int by) {
        if (this.hp == this.max_hp) {
            return;
        }else if (this.hp + by > this.max_hp) {
            return;
        }else {
            this.hp += by;
            return;
        }
    }

    public boolean hasItem(int id) {
        return inventory.containsValue(id);
    }

    public boolean hasQuest(int id) {
        return quests.containsValue(id);
    }

    public void addItem(Item item,int quantity) {
        this.inventory.addItem(item,quantity);
    }
}
