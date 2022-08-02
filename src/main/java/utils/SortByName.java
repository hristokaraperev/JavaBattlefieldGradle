package utils;

import combatants.humans.Human;
import combatants.machines.Machine;
import units.Army;
import units.Brigade;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortByName implements Util{
    @Override
    public void use(Army army) {
        List<Human> humans = new ArrayList<>();
        List<Machine> machines = new ArrayList<>();

        for (Brigade brigade : army.getSubunits()) {
            humans.addAll(brigade.getSubunits());
            machines.add(brigade.getMachine());
        }

        humans.sort(Comparator.comparing(Human::getName));
        machines.sort(Comparator.comparing(o -> o.getClass().getSimpleName()));

        for (Human human : humans) {
            System.out.println(human);
        }
        System.out.println();
        for (Machine machine : machines) {
            System.out.println(machine);
        }
    }
}
