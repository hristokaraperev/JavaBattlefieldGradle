package utils;

import combatants.abstractions.Combatant;
import combatants.abstractions.Human;
import composite.Army;
import composite.Brigade;
import composite.abstractions.Unit;

import java.util.ArrayList;
import java.util.List;

public class OverallArmyDurability implements Util{
    @Override
    public void sort(Army army) {
        int overallDurability = 0;
        List<Combatant> humans = new ArrayList<>();
        for (Unit brigade : army.getBrigades()) {
            humans.addAll(((Brigade)brigade).getHumans());
            overallDurability += ((Brigade)brigade).getWarMachine().getDurability();
        }
        overallDurability += humans.stream().mapToInt(value -> ((Human)value).getWeapon().getDurability()).sum();

        System.out.println("Overall army durability: " + overallDurability);
    }
}
