package weapons.abstractions;

public abstract class Weapon {
    double damage;
    int durability;
    String name;

    // function called every time a weapon is used
    public void updateDurability() {
        this.durability--;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }


}
