package Commands;

import World.World;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Info extends Command {
    private World world;
   

    public Info(World world) {
        super("info");
        this.world = world;
        
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String player = event.getAuthor().getName();
        event.getChannel().sendMessage(world.getPlayer(player).embeddedInfoMessage()).queue();
    }
}
