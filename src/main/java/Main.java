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
        builder.buildBlocking();

        System.out.println(token);

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.println("Message received from "+event.getAuthor().getName());
        String player = event.getAuthor().getName();

        if (event.getMessage().getContentRaw().equals("!add")) {


            if (world.hasPlayer(player)) {
                event.getChannel().sendMessage(player +" is already registered").queue();
            }else {
                world.addPlayer(player);
                event.getChannel().sendMessage("Added "+player+" to the game").queue();
                world.savePlayers();
            }
        } else  if (event.getMessage().getContentRaw().equals("!info")) {
            if (world.hasPlayer(player)) {
                event.getChannel().sendMessage(world.playerInfo(player)).queue();
            }else {
                event.getChannel().sendMessage(player +" is not registered").queue();
            }
        }


    }
}
