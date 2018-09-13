import Item.Item;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private int id;
    private String name;
    private Item itemNeededToEnter;
    private Quest avaiableQuest;
    private Monster monster;
    private List<Integer> locations;

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

    public List<Integer> getLocations() {
        return locations;
    }

    public Integer getLocationToNorth() {
        return this.locations.get(0);
    }

    public Integer getLocationToEast() {
        return this.locations.get(1);
    }

    public Integer getLocationToSouth() {
        return this.locations.get(2);
    }

    public Integer getLocationToWest() {
        return this.locations.get(3);
    }


    public static class Builder {
        private int id;
        private String name;
        private Item itemNeededToEnter;
        private Quest avaiableQuest;
        private Monster monster;
        private List<Integer> locations;


        public Builder(int id,String name) {
            this.id = id;
            this.name = name;
            ArrayList<Integer> locations = new ArrayList<Integer>();
            locations.add(0,null);
            locations.add(1,null);
            locations.add(2,null);
            locations.add(3,null);
            this.locations = locations;

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

        public Builder north(Integer location) {
            this.locations.add(0,location);
            return this;
        }

        public Builder east(Integer location) {

            this.locations.add(1,location);
            return this;
        }

        public Builder south(Integer location) {
            this.locations.add(2,location);
            return this;
        }

        public Builder west(Integer location) {
            this.locations.add(3,location);
            return this;
        }

        public Location build() {

            Location location = new Location();
            location.name = this.name;
            location.id = this.id;
            location.avaiableQuest = this.avaiableQuest;
            location.itemNeededToEnter = this.itemNeededToEnter;
            location.monster = this.monster;
            location.locations = this.locations;
            return location;

        }

    }
}
