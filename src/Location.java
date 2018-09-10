import Item.Item;

public class Location {
    private int id;
    private String name;
    private Item itemNeededToEnter;
    private Quest avaiableQuest;
    private Location[] otherLocations;
    private Monster monster;


    public static class Builder {
        private int id;
        private String name;
        private Item itemNeededToEnter;
        private Quest avaiableQuest;
        private Location[] otherLocations;
        private Monster monster;


        public Builder(int id,String name) {
            this.id = id;
            this.name = name;
        }

        public Builder item(Item item) {
            this.itemNeededToEnter = item;
            return this;
        }

        public Builder quest(Quest quest) {
            this.avaiableQuest= quest;
            return this;
        }

        public Builder location(Location location) {

            assert this.otherLocations.length <= 4;
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
            location.otherLocations = this.otherLocations;

            return location;

        }

    }
}
