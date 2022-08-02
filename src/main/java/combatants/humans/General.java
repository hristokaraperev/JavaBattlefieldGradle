package combatants.humans;

import java.util.Random;

public class General extends Human{
    int experience;
    protected General(String name, int age, double health)
    {
        super(name, age, health);
        Random rng = new Random();
        this.experience = rng.nextInt(1, 101);
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("General{");
        sb.append("experience=").append(experience);
        sb.append(", name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", health=").append(health);
        sb.append('}');
        return sb.toString();
    }
}
