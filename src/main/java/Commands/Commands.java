package Commands;

import java.util.HashMap;


import World.World;

public class Commands {
    private HashMap<String,Command> commands;
    private World world;

    public Commands(World world) {
        HashMap<String,Command> commands = new HashMap<String,Command>();
        
        commands.put("!move", new Move(world));
        commands.put("!m",new Move(world));

        commands.put("!loc",new Location(world));
        commands.put("!location",new Location(world));

        commands.put("!info",new Info(world));
        commands.put("!info",new Info(world));

        commands.put("!inventory",new Inventory(world));
        commands.put("!inv",new Inventory(world));

        commands.put("!add",new Add(world));
        commands.put("!a",new Add(world));


        this.commands = commands;
    }

    public Command getCommand(String name) {
        if (commands.containsKey(name)) {
            return commands.get(name);
        }else {
            return null;
        }
    }

    public boolean hasCommand(String name) {
        return commands.containsKey(name);
    }

    


}