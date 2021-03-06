import Item.Item;

import java.util.Map;

public class Quest {
    public int id;
    public String name;
    public String description;
    public Item rewardItem;
    public int rewardXP;
    public int rewardGold;
    public Map<Item,Integer> completionItems;

    public static class Builder {
        public int id;
        public String name;
        public String description;
        public Item rewardItem;
        public int rewardXP;
        public int rewardGold;
        public Map<Item,Integer> completionItems;


        public Builder(int id,String name,String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }

        public Builder rewards(Item item) {
            this.rewardItem = item;
            return this;
        }

        public Builder xp(int xp) {
            this.rewardXP = xp;
            return this;
        }

        public  Builder gold(int gold) {
            this.rewardGold = gold;
            return this;
        }

        public Builder requires(Item item,int quantity) {
            this.completionItems.put(item,quantity);
            return this;
        }


        public Quest build() {
            Quest quest = new Quest();
            quest.id = this.id;
            quest.description = this.description;
            quest.rewardGold = this.rewardGold;
            quest.rewardXP = this.rewardXP;
            quest.rewardItem = this.rewardItem;
            quest.completionItems = this.completionItems;

            return quest;
        }

    }
}
