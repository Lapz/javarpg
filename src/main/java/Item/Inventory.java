package Item;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Inventory {

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public Inventory(Item... initialItems) {

        this.items = new ArrayList<Item>();
        for (Item item : initialItems) {
            item.setQuantity(1);
            this.items.add(item);
        }
    }

    public boolean containsValue(Item item) {

        return this.items.contains(item);
    }

    public void addItem(Item item,int quantity) {
        this.items.add(item);
    }


    public MessageEmbed embeddedMessage() {
        EmbedBuilder em = new EmbedBuilder();
        em.setTitle("Inventory:");


        for (Item entry: items) {

                em.addField(""+entry.getName(),String.format("ID:%d\tQuantity",entry.getId(),entry.getQuantity()),false);
        }


        return em.build();
    }
}
