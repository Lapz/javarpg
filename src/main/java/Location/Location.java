package Location;

import Item.Item;

import Monster.Monster;
import Quest.Quest;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public MessageEmbed embeddedLocationsMessage(Map<Integer,Location> locations) {
        EmbedBuilder em = new EmbedBuilder();

        em.setTitle("Locations to travel to :");

        for (Integer id:this.locations) {

            String value = String.format("ID:%d\n\tAvailable quest:%s\n\tRequires:%s",id,
                    (locations.get(id).getAvailableQuest() == null ? "None"  :locations.get(id).getAvailableQuest().getName()),
                    (locations.get(id).getItemNeededToEnter() == null ? "Nothing"  :locations.get(id).getItemNeededToEnter().getName())
            );

            em.addField(locations.get(id).getName(),value,false);
        }

        return  em.build();
    }


    public static class Builder {
        private int id;
        private String name;
        private Item itemNeededToEnter;
        private Quest availableQuest;
        private Monster monster;
        private List<Integer> locations;


        public Builder(int id,String name) {
            this.id = id;
            this.name = name;
            ArrayList<Integer> locations = new ArrayList<Integer>(4);
            this.locations = locations;

        }

        public Builder requires(Item item) {
            this.itemNeededToEnter = item;
            return this;
        }

        public Builder quest(Quest quest) {
            this.availableQuest = quest;
            return this;
        }


        public Builder monster(Monster monster) {
            this.monster = monster;
            return this;
        }

        public Builder north(Integer location) {
            this.locations.add(location);
            return this;
        }

        public Builder east(Integer location) {

            this.locations.add(location);
            return this;
        }

        public Builder south(Integer location) {
            this.locations.add(location);
            return this;
        }

        public Builder west(Integer location) {
            this.locations.add(location);
            return this;
        }

        public Location build() {

            Location location = new Location();
            location.name = this.name;
            location.id = this.id;
            location.avaiableQuest = this.availableQuest;
            location.itemNeededToEnter = this.itemNeededToEnter;
            location.monster = this.monster;
            location.locations = this.locations;
            return location;

        }

    }
}
