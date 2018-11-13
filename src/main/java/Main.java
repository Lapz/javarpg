// import Commands.Add;
import Commands.Move;
import Item.Inventory;
import Item.LootItem;
import Item.Potion;
import Item.Weapon;
import Location.Location;
import World.World;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.HashMap;
import Commands.Command;
import Commands.Commands;

public class Main extends ListenerAdapter {
    public static World world = new World();
    public static Commands commands = new Commands(world);


    public static void main(String[] args) throws InterruptedException,LoginException

    {

        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = Dotenv
                .load()
                .get("DISCORD_TOKEN");
        builder.setToken(token);


        builder.addEventListener(new Main());
        // builder.addEventListener(new Move("move","m",world));

        // builder.addEventListener(new Main());
        builder.buildBlocking();

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.println("Message received from "+event.getAuthor().getName());
       
        String commandString = event.getMessage().getContentRaw().split(" ")[0]; // split it because the command may have args and we only need the first part

        String player =event.getAuthor().getName();

        if (event.getAuthor().isBot()) {
            return;
        }

        if (!commands.hasCommand(commandString)) {
            event.getChannel().sendMessage(String.format("The command `%s` is unrecognized",commandString)).queue();
        } else {

             if (!(world.hasPlayer(player))) {
                 event.getChannel().sendMessage(player +" is not registered. You can register by saying !add").queue();
             }else {

                 Command command = commands.getCommand(commandString);

                 command.onMessageReceived(event);
             }
           
        }

        world.savePlayers();

    }
}
