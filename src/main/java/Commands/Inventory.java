package Commands;




import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import World.World;

public class Inventory extends Command {
    World world;



    public Inventory(World world) {
        super("inventory");
        this.world = world;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String player = event.getAuthor().getName();
        event.getChannel().sendMessage(world.getPlayer(player).getInventory().embeddedMessage()).queue();
    }
}
