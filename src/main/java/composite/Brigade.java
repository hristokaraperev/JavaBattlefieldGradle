package composite;

import combatants.abstractions.Combatant;
import combatants.abstractions.Human;
import combatants.abstractions.Machine;
import composite.abstractions.Unit;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Brigade extends Unit {
    // building block of the army
    private Machine warMachine;
    private List<Combatant> humans;

    // similar to coalition and army loop
    // checks for empty lists to prevent errors
    // during gameplay
    @Override
    public int isGettingEngagedBy(Unit unit, Combatant defendingArmyGeneral) {
        // preventive check
        if (((Brigade) unit).getHumans().isEmpty() || humans.isEmpty()) {
            return 0;
        }


        Random rng = new Random();

        humans.get(rng.nextInt(humans.size())).isFighting(((Brigade) unit).warMachine);

        List<Combatant> attackingHumans = ((Brigade) unit).getHumans();

        Iterator<Combatant> defendingHumansIterator = humans.iterator();

        // iterates through humans in a brigade
        while (defendingHumansIterator.hasNext()) {
            Combatant nextCombatant = defendingHumansIterator.next();
            if (!(((Human) nextCombatant).getHealthPoints() > 0)) {
                continue;
            }
            int indexOfAttackingHuman = rng.nextInt(attackingHumans.size());

            if (rng.nextInt(100) < 1) {
                if (defendingArmyGeneral == null) { return 0; }
                defendingArmyGeneral.isFighting(attackingHumans.get(indexOfAttackingHuman));
                if (((Human) defendingArmyGeneral).getHealthPoints() <= 0) {
                    return 0;
                }
                continue;
            }

            nextCombatant.isFighting(attackingHumans.get(indexOfAttackingHuman));
        }

        // updates brigades in the army
        // and calculates army casualties to be passed to
        // army loop
        List<Combatant> updatedDefendingHumans = humans.stream().filter(combatant -> ((Human) combatant).getHealthPoints() > 0).toList();

        int defendingCasualties = humans.size() - updatedDefendingHumans.size();

        humans = updatedDefendingHumans;

        return defendingCasualties;
    }


    public List<Combatant> getHumans() {
        return humans;
    }

    public void setHumans(List<Combatant> humans) {
        this.humans = humans;
    }

    public void setWarMachine(Machine warMachine) {
        this.warMachine = warMachine;
    }

    public Machine getWarMachine() {
        return warMachine;
    }
}
