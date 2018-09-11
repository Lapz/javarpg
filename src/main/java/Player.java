import Item.Inventory;
import Item.Item;
import java.util.HashMap;
import java.util.Map;

public class Player {


    private String name;
    private Location currentLocation;
    private int hp;
    private int max_hp;
    private int gold;
    private int xp;
    private int level;
    private Inventory inventory;
    private Map<Integer,Quest> quests;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMax_hp() {
        return max_hp;
    }

    public void setMax_hp(int max_hp) {
        this.max_hp = max_hp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Map<Integer, Quest> getQuests() {
        return quests;
    }

    public void setQuests(Map<Integer, Quest> quests) {
        this.quests = quests;
    }

    public Player(String name, Location location) {
        this.name = name;
        this.currentLocation = location;
        this.hp = 10;
        this.max_hp = 10;
        this.gold = 0;
        this.xp = 0;
        this.level = 0;
        this.inventory = new Inventory();
        this.quests = new HashMap <Integer, Quest>();
    }

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


    public static class InfoBuilder {
        private String name;
        private int hp;
        private int gold;
        private int xp;
        private int level;
        private Inventory inventory;
        private Map<Integer,Quest> quests;


        public InfoBuilder(String name, int level,int hp,int gold, int xp) {
            this.name =name;
            this.level =level;
            this.hp = hp;
            this.gold = gold;
            this.xp = xp;

        }


        public InfoBuilder inventory(Inventory inventory) {
            this.inventory = inventory;
            return this;
        }

        public InfoBuilder quests(Map<Integer,Quest> quests) {
            this.quests = quests;
            return this;
        }

        public String build() {
            String info = String.format(
                    "```Name: %s\t Level:%d\tGold: %d\tXP: %d\n```"
                    ,this.name,this.level,this.gold,this.xp);

            if (!(this.inventory == null)) {
                info = info+ this.inventory.toString()+"\n";
            }

            if (!(this.inventory == null)) {
                info = info + this.quests.toString()+"\n";
            }

            return info;

        }
    }
}
