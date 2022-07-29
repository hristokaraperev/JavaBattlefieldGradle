package utils;

import combatants.abstractions.Combatant;
import combatants.abstractions.Human;
import composite.Army;
import composite.Brigade;
import composite.abstractions.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArmySoldiersByWeaponType implements Util{
    @Override
    public void sort(Army army) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter weapon type: ");
        String type = input.nextLine();

        List<Combatant> humans =  new ArrayList<>();
        for (Unit brigade : army.getBrigades()) {
            humans.addAll(((Brigade)brigade).getHumans());
        }

        List<Combatant> filteredHumans = humans.stream().filter(combatant -> ((Human)combatant).getWeapon().getName().equalsIgnoreCase(type)).toList();

        for (Combatant human : filteredHumans) {
            System.out.println(human);
        }

        input.close();
    }
}
