package Commands;



import World.World;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Add extends Command {
    World world;


    public Add(World world) {
        super("add");
        this.world = world;
    }

    @Override

        public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) {
            return;
        }

        String player = event.getAuthor().getName();


        if (world.hasPlayer(player)) {
            event.getChannel().sendMessage(player +" is already registered").queue();
        }else {
            world.addPlayer(player);
            event.getChannel().sendMessage("Added "+player+" to the game").queue();
            world.savePlayers();
            return;
        }


    }
}






//     @Override

// }
