package utils;

import combatants.abstractions.Combatant;
import combatants.abstractions.Human;
import combatants.abstractions.Machine;
import composite.Army;
import composite.Brigade;
import composite.abstractions.Unit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortByName implements Util{
    @Override
    public void sort(Army army) {
        List<Combatant> humans =  new ArrayList<>();
        List<Machine> machines = new ArrayList<>();
        for (Unit brigade : army.getBrigades()) {
            humans.addAll(((Brigade)brigade).getHumans());
            machines.add(((Brigade)brigade).getWarMachine());
        }

        humans.sort((Comparator.comparing(human -> ((Human) human).getName())));
        machines.sort((Comparator.comparing(Machine::getName)));

        for (Combatant human : humans) {
            System.out.println(human);
        }
        System.out.println();
        for (Machine machine : machines) {
            System.out.println(machine);
        }
    }
}
