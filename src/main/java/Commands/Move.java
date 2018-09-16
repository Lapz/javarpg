package Commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Move extends ListenerAdapter {
    String name;
    String shorthand;

    public Move(String name,String shorthand) {
        this.name = name;
        this.shorthand = shorthand;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equals(this.name) || event.getMessage().getContentRaw().equals(this.shorthand) ) {
            event.getChannel().sendMessage("The location ID is needed when moving. You can find it by using the `!location` or `!l` command").queue();
        }
    }
}
