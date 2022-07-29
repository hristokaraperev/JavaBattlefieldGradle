package utils;

import combatants.abstractions.Combatant;
import combatants.abstractions.Human;
import composite.Army;
import composite.Brigade;
import composite.abstractions.Unit;

import java.util.ArrayList;
import java.util.List;

public class AverageArmyAge implements Util{
    @Override
    public void sort(Army army) {
        List<Combatant> humans = new ArrayList<>();
        for (Unit brigade : army.getBrigades()) {
            humans.addAll(((Brigade)brigade).getHumans());
        }
        double averageAge = humans.stream().mapToInt(value -> ((Human)value).getAge()).average().getAsDouble();

        System.out.println("Average age in the army is: " + averageAge);
    }
}
