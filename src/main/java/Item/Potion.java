package Item;

public class Potion extends Item {
    public int heals_by;

    public Potion(String name,int id,int heals_by) {
        super(name,id);
        this.heals_by = heals_by;
    }

    public String toString() {
        return "A "+name+" which heals by "+heals_by;
    }
}
