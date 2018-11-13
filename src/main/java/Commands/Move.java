package Commands;

import Location.Location;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import World.World;

public class Move extends Command {
    World world;



    public Move(World world) {
        super("move");
        this.world = world;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        
        String player = event.getAuthor().getName();
        

        if (event.getMessage().getContentRaw().equals(this.name)) {
            event.getChannel().sendMessage("The location ID is needed when moving. You can find it by using the `!location` or `!l` command").queue();
        } else {
            String[] segments = event.getMessage().getContentRaw().split(" ");


            if (segments.length < 2) {
                event.getChannel().sendMessage("The command `!move` requires two arguments.").queue();
                return;
            }

            try {
                int id = Integer.parseInt(segments[1]);
                System.out.println(world);
                Location loc = world.getLocation(id);

                if (loc == null) {
                    event.getChannel().sendMessage("No location exists with the id:`" + id + "`").queue();
                    return;
                } else {

                    if (loc.getItemNeededToEnter() != null) {

                        if (!world.getPlayer(player).hasItem(loc.getItemNeededToEnter())) {
                            event.getChannel().sendMessage("You require `" + loc.getItemNeededToEnter().getName() + "` to move to `" + loc.getName() + "`").queue();
                            return;
                        }
                    }


                    Location currentPlayerLoc = world.getPlayer(player).getCurrentLocation();


                    if (!currentPlayerLoc.getLocations().contains(loc.getId())) {

                        event.getChannel().sendMessage("You cannot move to `" + loc.getName() + "` but can " +
                                "instead move to the following locations").queue();
                        event.getChannel().sendMessage(currentPlayerLoc.embeddedLocationsMessage(world.getLocations())).queue();
                        return;

                    }

                    world.getPlayer(player).setCurrentLocation(loc);


                    event.getChannel().sendMessage("You have moved to `" + loc.getName() + "`").queue();

                    if (loc.getAvailableQuest() != null) {
                        event.getChannel().sendMessage(loc.embeddedLocationsMessage(world.getLocations())).queue();
                    }
                }
            } catch (NumberFormatException e) {
                event.getChannel().sendMessage("`" + segments[1] + "` is not a valid argument for the command `!move` ").queue();
            }

        }
    }
}
