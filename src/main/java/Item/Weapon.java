package Item;

public class Weapon extends Item {
    public int minDmg;
    public int maxDmg;

    public Weapon(String name, int minDmg,int maxDmg,int id) {
        super(name,id);
        this.minDmg = minDmg;
        this.maxDmg = maxDmg;
    }

    public int getMinDmg() {
        return minDmg;
    }

    public int getMaxDmg() {
        return maxDmg;
    }

    public String toString() {
       return "A "+name+" which does between "+minDmg+" and "+maxDmg+" damage";
    }
}
