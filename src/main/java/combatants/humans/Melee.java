package combatants.humans;

import weapons.Weapon;

public class Melee extends Human implements WeaponCarrier{
    Weapon weapon;
    protected Melee(String name, int age, double health)
    {
        super(name, age, health);
        this.weapon = Weapon.Factory.newMeleeWeapon();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Melee{");
        sb.append("weapon=").append(weapon);
        sb.append(", name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", health=").append(health);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public double getWeaponDamage() {
        return weapon.getDamage();
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }
}
