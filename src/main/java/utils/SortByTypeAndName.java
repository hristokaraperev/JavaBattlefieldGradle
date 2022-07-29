package utils;

import combatants.abstractions.Combatant;
import combatants.abstractions.Human;
import combatants.abstractions.Machine;
import composite.Army;
import composite.Brigade;
import composite.abstractions.Unit;
import weapons.MeleeWeapon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortByTypeAndName implements Util{
    @Override
    public void sort(Army army) {
        List<Combatant> humans =  new ArrayList<>();
        List<Machine> machines = new ArrayList<>();
        for (Unit brigade : army.getBrigades()) {
            humans.addAll(((Brigade)brigade).getHumans());
            machines.add(((Brigade)brigade).getWarMachine());
        }

        humans.sort(((o1, o2) -> {
            if (!((Human)o1).getWeapon().getClass().equals(((Human)o2).getWeapon().getClass())) {
                if (((Human)o2).getWeapon().getClass() == MeleeWeapon.class) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return ((Human)o1).getName().compareTo(((Human)o2).getName());
        }));
        machines.sort(((o1, o2) -> {
            if (!o1.getName().equals(o2.getName())) {
                if (o1.getName().equals("LAND")) {
                    return -1;
                } else {
                    return 1;
                }
            }
            return 0;
        }));

        for (Combatant human : humans) {
            System.out.println(human);
        }
        System.out.println();
        for (Machine machine : machines) {
            System.out.println(machine);
        }
    }
}
