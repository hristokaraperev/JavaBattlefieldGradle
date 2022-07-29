package combatants.humans;

import combatants.abstractions.Combatant;
import combatants.abstractions.Human;
import combatants.abstractions.Machine;

public class Melee extends Human {

    // function is called every time a combatant is about to be attacked
    @Override
    public void isFighting(Combatant attacker) {
        if (attacker.getClass().getSuperclass() == Machine.class) {
            this.takeDamage(((Machine) attacker).getDamage());
        } else {
            this.takeDamage(((Human) attacker).getDamage());
        }
    }

    @Override
    public String toString() {
        return "Melee - Name: " + this.getName() + " Weapon: " + this.getWeapon();
    }
}
