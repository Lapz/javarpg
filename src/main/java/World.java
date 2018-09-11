import Item.Item;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import Item.Weapon;
import Item.Potion;
import Item.LootItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;


public class World {

    public Map<Integer,Quest> quests;
    public Map<Integer,Location> locations;
    public Map<Integer,Monster> monsters;
    public Map<Integer, Item> items;
    public Map<String,Player> players;

    private static Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

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
            String json = new String(Files.readAllBytes(new File("./players.json").toPath()));

            Type player = new TypeToken<HashMap<String,Player>>() {}.getType();

            HashMap<String,Player> players = gson.fromJson(json,player);

            this.players = players;
        } catch (IOException e) {
            System.out.println("Couldn't open the json file of previous players");
            e.printStackTrace();
        } catch (JsonSyntaxException e){
            System.out.println("Couldn't parse the json file of previous players");
            e.printStackTrace();
        }

    }

    public void savePlayers() {
        try {
            BufferedWriter writer = new BufferedWriter( new FileWriter("./players.json"));
            writer.write(gson.toJson(this.players));
            writer.close();
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
                .north(LOCATION_ID_TOWN_SQUARE)
                .build();
        Location townSquare = new Location.Builder(LOCATION_ID_TOWN_SQUARE,"Town square")
                .north(LOCATION_ID_ALCHEMIST_HUT)
                .east(LOCATION_ID_GUARD_POST)
                .south(LOCATION_ID_HOME)
                .west(LOCATION_ID_FARMHOUSE)
                .build();
        Location alchemistHut = new Location.Builder(LOCATION_ID_ALCHEMIST_HUT,"Alchemis'ts hut")
                .south(LOCATION_ID_TOWN_SQUARE)
                .north(LOCATION_ID_ALCHEMISTS_GARDEN)
                .quest(quests.get(QUEST_ID_CLEAR_ALCHEMIST_GARDEN))
                .build();
        Location alchemistsGarden = new Location.Builder(LOCATION_ID_ALCHEMISTS_GARDEN,"Alchemist's garden")
                .south(LOCATION_ID_ALCHEMIST_HUT)
                .monster(monsters.get(MONSTER_ID_RAT))
                .build();
        Location farmHouse = new Location.Builder(LOCATION_ID_FARMHOUSE,"Farmhouse")
                .east(LOCATION_ID_TOWN_SQUARE)
                .west(QUEST_ID_CLEAR_FARMERS_FIELD)
                .quest(quests.get(QUEST_ID_CLEAR_FARMERS_FIELD))
                .build();
        Location farmersField = new Location.Builder(LOCATION_ID_FARM_FIELD,"Farmer's field")
                .east(LOCATION_ID_FARMHOUSE)
                .monster(monsters.get(MONSTER_ID_SNAKE))
                .build();
        Location guardPost = new Location.Builder(LOCATION_ID_GUARD_POST,"Guard post")
                .east(LOCATION_ID_BRIDGE)
                .west(LOCATION_ID_TOWN_SQUARE)
                .requires(items.get(ITEM_ID_ADVENTURER_PASS))
                .build();
        Location bridge = new Location.Builder(LOCATION_ID_BRIDGE,"Bridge")
                .west(LOCATION_ID_GUARD_POST)
                .east(LOCATION_ID_SPIDER_FIELD)
                .build();
        Location spiderField = new Location.Builder(LOCATION_ID_SPIDER_FIELD,"Forest")
                .west(LOCATION_ID_BRIDGE)
                .monster(monsters.get(MONSTER_ID_GIANT_SPIDER))
                .build();

        this.locations.put(LOCATION_ID_HOME,home);
        this.locations.put(LOCATION_ID_TOWN_SQUARE,townSquare);
        this.locations.put(LOCATION_ID_GUARD_POST,guardPost);
        this.locations.put(LOCATION_ID_ALCHEMIST_HUT,alchemistHut);
        this.locations.put(LOCATION_ID_ALCHEMISTS_GARDEN,alchemistsGarden);
        this.locations.put(LOCATION_ID_FARMHOUSE,farmHouse);
        this.locations.put(LOCATION_ID_FARM_FIELD,farmersField);
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


    public class IntegerKeyDeserializer extends KeyDeserializer {
        @Override
        public Object deserializeKey(final String key, final DeserializationContext ctxt ) throws IOException, JsonProcessingException {
            return null;
        }
    }
}
