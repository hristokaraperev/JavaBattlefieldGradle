package weapons;

public class MeleeWeapon extends Weapon{
    protected MeleeWeapon(String name, double damage, int durability) {
        super(name, damage, durability);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MeleeWeapon{");
        sb.append("name='").append(name).append('\'');
        sb.append(", damage=").append(damage);
        sb.append(", durability=").append(durability);
        sb.append('}');
        return sb.toString();
    }
}
