package Commands;

import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class Command {
    String name;
   

    public Command(String name) {
        this.name = name;
    }

    public void onMessageReceived(MessageReceivedEvent event) {
        return;
    }
}
