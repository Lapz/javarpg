import Item.Item;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import Item.Weapon;
import Item.Potion;
import Item.LootItem;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



public class World {

    public Map<Integer,Quest> quests;
    public Map<Integer,Location> locations;
    public Map<Integer,Monster> monsters;
    public Map<Integer, Item> items;
    public Map<String,Player> players;

    private static ObjectMapper mapper = new ObjectMapper();

    //Items
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
        this.quests = new HashMap<Integer, Quest>();
        this.locations = new HashMap<Integer, Location>();
        this.monsters = new HashMap <Integer, Monster>();
        this.items = new HashMap<Integer, Item>();
        this.players = new HashMap <String, Player>();

        this.loadPlayers();
        this.addItems();
        this.addQuests();
        this.addMonster();
        this.addLocations();
    }

    public boolean hasPlayer(String id) {
       return players.containsKey(id);
    }

    public void loadPlayers() {

        try {
            HashMap<String,Player> players = mapper.readValue(new File("./players.json"),
                    new TypeReference<Map<String, Player>>() { });
            this.players = players;
        }catch (IOException e){
            System.out.println("Couldn't load previous players");
        }

    }

    public void savePlayers() {
        try {
            this.mapper.writeValue(new File("./players.json"),players);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addPlayer(String id) {
        players.put(id,new Player(id,this.locations.get(LOCATION_ID_HOME)));
    }

    public String playerInfo(String id) {
        Player player = this.players.get(id);

        String info = new Player.InfoBuilder(player.getName(),player.getLevel(),player.getHp(),player.getXp(),player.getGold()).build();
        return info;
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

    private void addLocations() {

        Location home = new Location.Builder(LOCATION_ID_HOME,"Home")
                .build();
        Location townSquare = new Location.Builder(LOCATION_ID_TOWN_SQUARE,"Town square")
                .build();
        Location alchemistHut = new Location.Builder(LOCATION_ID_ALCHEMIST_HUT,"Alchemis'ts hut")
                .quest(quests.get(QUEST_ID_CLEAR_ALCHEMIST_GARDEN))
                .build();
        Location alchemistsGarden = new Location.Builder(LOCATION_ID_ALCHEMISTS_GARDEN,"Alchemist's garden")
                .monster(monsters.get(MONSTER_ID_RAT))
                .build();
        Location farmHouse = new Location.Builder(LOCATION_ID_FARMHOUSE,"Farmhouse")
                .quest(quests.get(QUEST_ID_CLEAR_FARMERS_FIELD))
                .build();
        Location farmersFeild = new Location.Builder(LOCATION_ID_FARM_FIELD,"Farmer's field")
                .monster(monsters.get(MONSTER_ID_SNAKE))
                .build();
        Location guardPost = new Location.Builder(LOCATION_ID_GUARD_POST,"Guard post")
                .requires(items.get(ITEM_ID_ADVENTURER_PASS))
                .build();
        Location bridge = new Location.Builder(LOCATION_ID_BRIDGE,"Bridge")
                .build();
        Location spiderField = new Location.Builder(LOCATION_ID_SPIDER_FIELD,"Forest")
                .monster(monsters.get(MONSTER_ID_GIANT_SPIDER))
                .build();

        home
                .north(townSquare);

        townSquare
                .north(alchemistHut)
                .east(guardPost)
                .south(home)
                .west(farmHouse);

        farmHouse
                .east(townSquare)
                .west(farmersFeild);

        farmersFeild
                .east(farmHouse);

        alchemistHut
                .south(townSquare)
                .north(alchemistsGarden);

        alchemistsGarden
                .south(alchemistHut);

        guardPost
                .east(bridge)
                .west(townSquare);

        bridge
                .west(guardPost)
                .east(spiderField);

        spiderField
                .west(bridge);


        this.locations.put(LOCATION_ID_HOME,home);
        this.locations.put(LOCATION_ID_TOWN_SQUARE,townSquare);
        this.locations.put(LOCATION_ID_GUARD_POST,guardPost);
        this.locations.put(LOCATION_ID_ALCHEMIST_HUT,alchemistHut);
        this.locations.put(LOCATION_ID_ALCHEMISTS_GARDEN,alchemistsGarden);
        this.locations.put(LOCATION_ID_FARMHOUSE,farmHouse);
        this.locations.put(LOCATION_ID_FARM_FIELD,farmersFeild);
        this.locations.put(LOCATION_ID_BRIDGE,bridge);
        this.locations.put(LOCATION_ID_SPIDER_FIELD,spiderField);
    }

    private void addMonster() {

        Monster rat = new Monster(MONSTER_ID_RAT,"A giant rat",5,10,
                3,3);

        LootItem ratTail = new LootItem(items.get(ITEM_ID_RAT_TAIL),75,false); // Upcast to a loot item


        LootItem ratFur = new LootItem(items.get(ITEM_ID_PIECE_OF_FUR),75,true);


        rat.addLoot(ratFur, ratTail);

        this.monsters.put(MONSTER_ID_RAT,rat);

        Monster snake = new Monster(MONSTER_ID_SNAKE,"Snake",5,10,
                3,3);

        LootItem snakeFang = new LootItem (items.get(ITEM_ID_SNAKE_FANG),75,false); // Upcast to a loot item

        LootItem snakeSkin = new LootItem (items.get(ITEM_ID_SNAKE_SKIN),75,true);

        snake.addLoot(snakeFang,snakeSkin);

        this.monsters.put(MONSTER_ID_SNAKE,snake);

        Monster spider = new Monster(MONSTER_ID_GIANT_SPIDER,"Spider",20,40,
                10,10);

        LootItem spiderFang = new LootItem (items.get(ITEM_ID_SPIDER_FANG),75,true); // Upcast to a loot item

        LootItem spiderSilk = new LootItem (items.get(ITEM_ID_SPIDER_SILK),25,false);

        spider.addLoot(spiderFang,spiderSilk);

        this.monsters.put(MONSTER_ID_GIANT_SPIDER,spider);

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
