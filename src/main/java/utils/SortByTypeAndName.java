package utils;

import combatants.humans.Human;
import combatants.humans.Melee;
import combatants.machines.Land;
import combatants.machines.Machine;
import units.Army;
import units.Brigade;

import java.util.ArrayList;
import java.util.List;

public class SortByTypeAndName implements Util {
    @Override
    public void use(Army army) {
        List<Human> humans = new ArrayList<>();
        List<Machine> machines = new ArrayList<>();

        for (Brigade brigade : army.getSubunits()) {
            humans.addAll(brigade.getSubunits());
            machines.add(brigade.getMachine());
        }

        humans.sort((o1, o2) -> {
            if (!o1.getClass().getSimpleName().equals(o2.getClass().getSimpleName())) {
                if (o1.getClass().getSimpleName().equals(Melee.class.getSimpleName())) {
                    return -1;
                } else {
                    return 1;
                }
            }
            return o1.getName().compareTo(o2.getName());
        });
        machines.sort((o1, o2) -> {
            if (!o1.getClass().getSimpleName().equals(o2.getClass().getSimpleName())) {
                if (o1.getClass().getSimpleName().equals(Land.class.getSimpleName())) {
                    return -1;
                } else {
                    return 1;
                }
            }
            return 0;
        });

        for (Human human : humans) {
            System.out.println(human);
        }
        System.out.println();
        for (Machine machine : machines) {
            System.out.println(machine);
        }
    }
}
