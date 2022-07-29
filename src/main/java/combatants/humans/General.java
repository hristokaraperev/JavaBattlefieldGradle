package combatants.humans;

import combatants.abstractions.Combatant;
import combatants.abstractions.Human;

public class General extends Human {
    private int experience;

    // function is called every time a combatant is about to be attacked
    @Override
    public void isFighting(Combatant attacker) {
        this.takeDamage(((Human) attacker).getDamage());
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + " HP: " + this.getHealthPoints() + " War knowledge: " + this.getExperience();
    }
}
