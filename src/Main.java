import Item.Inventory;
import Item.LootItem;
import Item.Potion;
import Item.Weapon;

public class Main {

    public static void main(String[] args) {
        Weapon woodenSword = new Weapon("Wooden Sword",2,5,1);
        Potion healingPotion = new Potion("Lesser Healing Potion",2,10 );
        LootItem ratTail = new LootItem("A giant rat's tails",75,false,3);
        Inventory inventory = new Inventory(healingPotion,woodenSword,ratTail);
        Location home = new Location.Builder(1,"Home").build();
        Location townSquare = new Location.Builder(2,"Town Square").build();
//        Location alchemistsHut = new Location.Builder(3,"Alchemist's hut").quest().build();

        System.out.println(inventory);
    }
}
