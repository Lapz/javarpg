import Item.Item;

import java.util.Map;
import Item.Weapon;
import Item.Potion;


public class World {
    public Location currentLocation;
    public Player player;
    public Quest currentQuest;
    public Map<Integer,Quest> quests;
    public Map<Integer,Location> locations;
    public Map<Integer,Monster> monsters;
    public Map<Integer, Item> items;


  private static final int ITEM_ID_RUSTY_SWORD = 1;
  private static final int ITEM_ID_RAT_TAIL = 2;
  private static final int ITEM_ID_PIECE_OF_FUR = 3;
  private static final int ITEM_ID_SNAKE_FANG = 4;
  private static final int ITEM_ID_SNAKE_SKIN = 5;
  private static final int ITEM_ID_CLUB = 6;
  private static final int ITEM_ID_HEALING_POTION = 7;
  private static final int ITEM_ID_SPIDER_FANG = 8;
  private static final int ITEM_ID_SPIDER_SILK = 9;
  private static final int ITEM_ID_ADVENTURER_PASS = 10;

// Monsters
  private static final int MONSTER_ID_RAT = 1;
  private static final int MONSTER_ID_SNAKE = 2;
  private static final int MONSTER_ID_GIANT_SPIDER = 3;

// Quests
  private static final int QUEST_ID_CLEAR_ALCHEMIST_GARDEN = 1;
  private static final int QUEST_ID_CLEAR_FARMERS_FIELD = 2;

// Locations
  private static final int LOCATION_ID_HOME = 1;
  private static final int LOCATION_ID_TOWN_SQUARE = 2;
  private static final int LOCATION_ID_GUARD_POST = 3;
  private static final int LOCATION_ID_ALCHEMIST_HUT = 4;
  private static final int LOCATION_ID_ALCHEMISTS_GARDEN = 5;
  private static final int LOCATION_ID_FARMHOUSE = 6;
  private static final int LOCATION_ID_FARM_FIELD = 7;
  private static final int LOCATION_ID_BRIDGE = 8;
  private static final int LOCATION_ID_SPIDER_FIELD = 9;

    public World() {
        this.currentLocation = null;
        this.currentQuest = null;
        this.player = new Player();
    }


    private void addItems() {
        //TODO: check for nulls and return an error

        this.items.put(ITEM_ID_RUSTY_SWORD,new Weapon("Rusty Sword",0,5,ITEM_ID_RUSTY_SWORD));
        this.items.put(ITEM_ID_CLUB,new Weapon("Club",3,8,ITEM_ID_CLUB));
        this.items.put(ITEM_ID_PIECE_OF_FUR,new Item("Adventurer pass",ITEM_ID_ADVENTURER_PASS));
        this.items.put(ITEM_ID_RAT_TAIL,new Item("Rat tail",ITEM_ID_RAT_TAIL));
        this.items.put(ITEM_ID_SNAKE_FANG,new Item("Snake fang",ITEM_ID_SNAKE_FANG));
        this.items.put(ITEM_ID_SNAKE_SKIN,new Item("Snake skin",ITEM_ID_SNAKE_SKIN));
        this.items.put(ITEM_ID_SPIDER_FANG,new Item("Spider fang",ITEM_ID_SPIDER_FANG));
        this.items.put(ITEM_ID_SPIDER_SILK, new Item("Spider silk",ITEM_ID_SPIDER_SILK));
        this.items.put(ITEM_ID_HEALING_POTION,new Potion("Healing Potion",ITEM_ID_HEALING_POTION,5));
        this.items.put(ITEM_ID_ADVENTURER_PASS,new Item("Adventure's pass",ITEM_ID_ADVENTURER_PASS));
    }

    private void addLocation() {

    }

    private void addMonster() {

    }



    private void addQuests() {
        //TODO: check for nulls and return an error
        Quest clearAlchemistsGarden = new Quest.Builder(QUEST_ID_CLEAR_ALCHEMIST_GARDEN,"Clear the alchemist's garden",
                "Kill rats in the alchemist's garden and bring back 3 rat tails. You will " +
                        "recevive a healiung potion and 10 gold pieces.")
                .xp(20)
                .gold(10)
                .requires(items.get(ITEM_ID_RAT_TAIL),3)
                .build();
        Quest clearFarmersField = new Quest.Builder(QUEST_ID_CLEAR_FARMERS_FIELD,"Clear the farmer's field","Kill snakes in the" +
                " farmer's field and bring back 3 snake fangs. " +
                "You will receive an adventurer's pass and 20 gold pieces.")
                .xp(20)
                .gold(20)
                .requires(items.get(ITEM_ID_SNAKE_FANG),3)
                .rewards(items.get(ITEM_ID_ADVENTURER_PASS))
                .build();

        this.quests.put(QUEST_ID_CLEAR_ALCHEMIST_GARDEN,clearAlchemistsGarden);
        this.quests.put(QUEST_ID_CLEAR_FARMERS_FIELD,clearFarmersField);
    }



}
