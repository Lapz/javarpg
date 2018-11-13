package Monster;

import Item.LootItem;

import java.util.ArrayList;

public class Monster {
    private int id;
    private String name;
    private int hp;
    private int maxHP;
    private int maxDamage;
    private int rewardXP;
    private int rewardGold;
    private ArrayList<LootItem> lootTable;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getRewardXP() {
        return rewardXP;
    }

    public int getRewardGold() {
        return rewardGold;
    }

    public ArrayList<LootItem> getLootTable() {
        return lootTable;
    }

    public Monster(int id, String name, int maxHP, int maxDamage, int rewardGold, int rewardXP) {
        this.id = id;
        this.name = name;
        this.hp = maxHP;
        this.maxHP = maxHP;
        this.maxDamage = maxDamage;
        this.rewardGold = rewardGold;
        this.rewardXP = rewardXP;
        this.lootTable = new ArrayList<LootItem>();
    }

    public void addLoot(LootItem loot) {
        this.lootTable.add(loot);
    }

    public void addLoot(LootItem... loot) {
        for (LootItem item: this.lootTable)
            lootTable.add(item);
    }



}
