package combatants.abstractions;

// machine class implements combatant interface
// so that it can attack other combatants
public abstract class Machine implements Combatant{
    int durability;
    double damage;
    String name;

    // function that is called every time a machine attacks
    // to check whether the machine has depleted or not
    public double getDamage() {
        if (durability > 0) {
            durability--;
            return damage;
        }
        return 0;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getDurability() {
        return durability;
    }

    @Override
    public String toString() {
        return "Machine - Name: " + this.name;
    }
}
