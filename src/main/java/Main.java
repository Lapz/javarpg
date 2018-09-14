import Item.Inventory;
import Item.LootItem;
import Item.Potion;
import Item.Weapon;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;

public class Main extends ListenerAdapter {
    public static World world = new World();


    public static void main(String[] args) throws InterruptedException,LoginException

    {

        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = Dotenv
                .load()
                .get("DISCORD_TOKEN");

        builder.setToken(token);
        builder.addEventListener(new Main());
        builder.buildAsync();

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.println("Message received from "+event.getAuthor().getName());
        String player = event.getAuthor().getName();

        if (event.getAuthor().isBot()) {
            return;
        }


        if (event.getMessage().getContentRaw().equals("!add")) {

            if (world.hasPlayer(player)) {
                event.getChannel().sendMessage(player +" is already registered").queue();
            }else {
                world.addPlayer(player);
                event.getChannel().sendMessage("Added "+player+" to the game").queue();
                world.savePlayers();
                return;
            }
        } else if (event.getMessage().getContentRaw().equals("!info")) {

                event.getChannel().sendMessage(world.getPlayer(player).embeddedInfoMessage()).queue();

        } else if (event.getMessage().getContentRaw().equals("!inventory")) {

                event.getChannel().sendMessage(world.getPlayer(player).getInventory().embeddedMessage()).queue();

        } else if (event.getMessage().getContentRaw().equals("!locations") || event.getMessage().getContentRaw().equals("!l") ) {

                event.getChannel().sendMessage( world.getPlayer(player).getCurrentLocation().embeddedLocationsMessage(world.getLocations())).queue();

        } else if (event.getMessage().getContentRaw().equals("!move") || event.getMessage().getContentRaw().equals("!m") ) {

            event.getChannel().sendMessage("The location ID is needed when moving. You can find it by using the `!location` or `!l` command").queue();

        } else if (event.getMessage().getContentRaw().startsWith("!move") || event.getMessage().getContentRaw().startsWith("!m")) {
            String[] segements = event.getMessage().getContentRaw().split(" ");


            if (segements.length < 2) {
                event.getChannel().sendMessage("The command `!move` requires two arguments.").queue();
                return;
            }

            try {
                int id = Integer.parseInt(segements[1]);

                Location loc = world.getLocation(id);

                if (loc == null) {
                    event.getChannel().sendMessage("No location exists with the id:`"+id+"`").queue();
                    return;
                } else {

                    if (loc.getItemNeededToEnter() != null) {

                        if (!world.getPlayer(player).hasItem(loc.getItemNeededToEnter())) {
                            event.getChannel().sendMessage("You require `"+loc.getItemNeededToEnter().getName()+"` to move to `"+loc.getName()+"`").queue();
                            return;
                        }
                    }


                    Location currentPlayerLoc = world.getPlayer(player).getCurrentLocation();


                    if (!currentPlayerLoc.getLocations().contains(loc.getId())) {

                        event.getChannel().sendMessage("You cannot move to `"+loc.getName()+"` but can " +
                                "instead move to the following locations").queue();
                        event.getChannel().sendMessage(currentPlayerLoc.embeddedLocationsMessage(world.getLocations())).queue();
                        return;

                    }

                    world.getPlayer(player).setCurrentLocation(loc);
                    event.getChannel().sendMessage("You have moved to `"+loc.getName()+"`").queue();
                }

            } catch (NumberFormatException e) {
                event.getChannel().sendMessage("`"+segements[1]+"` is not a valid argument for the command `!move` ").queue();
            }

        } else if (event.getMessage().getContentRaw().startsWith("!")){

            event.getChannel().sendMessage(String.format("The command `%s` is unrecognized",event.getMessage().getContentRaw())).queue();
        }else if (event.getMessage().getContentRaw().startsWith("!") && !(world.hasPlayer(player))) {
            event.getChannel().sendMessage(player +" is not registered. You can register by saying !add").queue();
        }


        world.savePlayers();

    }
}
