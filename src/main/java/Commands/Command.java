package Commands;

import net.dv8tion.jda.core.hooks.ListenerAdapter;

public abstract class Command extends ListenerAdapter {
    String name;
    String shorthand;

    public Command(String name,String shorthand) {
        this.name = name;
        this.shorthand = shorthand;
    }

}
