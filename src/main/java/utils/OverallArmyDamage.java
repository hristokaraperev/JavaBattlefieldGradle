package utils;

import combatants.abstractions.Combatant;
import combatants.abstractions.Human;
import composite.Army;
import composite.Brigade;
import composite.abstractions.Unit;

import java.util.ArrayList;
import java.util.List;

public class OverallArmyDamage implements Util{
    @Override
    public void sort(Army army) {
        double overallArmyDamage = 0;
        List<Combatant> humans = new ArrayList<>();
        for (Unit brigade : army.getBrigades()) {
            overallArmyDamage += ((Brigade)brigade).getWarMachine().getDamage();
            humans.addAll(((Brigade)brigade).getHumans());
        }
        overallArmyDamage += humans.stream().mapToDouble(value -> ((Human)value).getDamage()).sum();

        System.out.println("Overall army damage : " + overallArmyDamage);
    }
}
