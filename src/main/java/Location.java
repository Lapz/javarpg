import Item.Item;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private int id;
    private String name;
    private Item itemNeededToEnter;
    private Quest avaiableQuest;
    private Monster monster;
    private List<Location> locations;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Item getItemNeededToEnter() {
        return itemNeededToEnter;
    }

    public Quest getAvailableQuest() {
        return avaiableQuest;
    }

    public Monster getMonster() {
        return monster;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public Location getLocationToNorth() {
        return this.locations.get(0);
    }

    public Location getLocationToEast() {
        return this.locations.get(1);
    }

    public Location getLocationToSouth() {
        return this.locations.get(2);
    }

    public Location getLocationToWest() {
        return this.locations.get(3);
    }


    public Location north(Location location) {
        this.locations.add(0,location);
        return this;
    }

    public Location east(Location location) {

        this.locations.add(1,location);
        return this;
    }

    public Location south(Location location) {
        this.locations.add(2,location);
        return this;
    }

    public Location west(Location location) {
        this.locations.add(3,location);
        return this;
    }



    public static class Builder {
        private int id;
        private String name;
        private Item itemNeededToEnter;
        private Quest avaiableQuest;
        private Monster monster;


        public Builder(int id,String name) {
            this.id = id;
            this.name = name;
        }

        public Builder requires(Item item) {
            this.itemNeededToEnter = item;
            return this;
        }

        public Builder quest(Quest quest) {
            this.avaiableQuest= quest;
            return this;
        }


        public Builder monster(Monster monster) {
            this.monster = monster;
            return this;
        }

        public Location build() {

            Location location = new Location();
            location.name = this.name;
            location.id = this.id;
            location.avaiableQuest = this.avaiableQuest;
            location.itemNeededToEnter = this.itemNeededToEnter;
            location.monster = this.monster;
            ArrayList<Location> locations = new ArrayList<Location>();
            locations.add(0,null);
            locations.add(1,null);
            locations.add(2,null);
            locations.add(3,null);
            location.locations = locations;
            return location;

        }

    }
}
