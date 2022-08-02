package combatants.machines;

public class Air extends Machine{
    protected Air(double damage, int durability) {
        super(damage, durability);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Air{");
        sb.append("damage=").append(damage);
        sb.append(", durability=").append(durability);
        sb.append('}');
        return sb.toString();
    }
}
