package combatants.machines;

public class Land extends Machine{
    protected Land(double damage, int durability) {
        super(damage, durability);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Land{");
        sb.append("damage=").append(damage);
        sb.append(", durability=").append(durability);
        sb.append('}');
        return sb.toString();
    }
}
