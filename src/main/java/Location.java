import Item.Item;

public class Location {
    private int id;
    private String name;
    private Item itemNeededToEnter;
    private Quest avaiableQuest;
    private Monster monster;
    private Location locationToNorth;
    private Location locationToEast ;
    private Location locationToSouth;
    private Location locationToWest;


    public Location north(Location location) {

        this.locationToNorth = location;
        return this;
    }

    public Location east(Location location) {

        this.locationToEast = location;
        return this;
    }

    public Location south(Location location) {
        this.locationToSouth = location;
        return this;
    }

    public Location west(Location location) {
        this.locationToWest = location;
        return this;
    }



    public static class Builder {
        private int id;
        private String name;
        private Item itemNeededToEnter;
        private Quest avaiableQuest;
        private Monster monster;
        private Location locationToNorth;
        private Location locationToEast ;
        private Location locationToSouth;
        private Location locationToWest;


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
            return location;

        }

    }
}
