

package Commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import World.World;

public class Location extends Command {
    World world;



    public Location(World world) {
        super("location");
        this.world = world;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String player = event.getAuthor().getName();
        event.getChannel().sendMessage( world.getPlayer(player).getCurrentLocation().embeddedLocationsMessage(world.getLocations())).queue();
    }
}
