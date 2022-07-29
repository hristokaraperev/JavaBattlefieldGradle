package utils;

import combatants.abstractions.Combatant;
import combatants.abstractions.Human;
import composite.Army;
import composite.Brigade;
import composite.abstractions.Unit;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HumansAboveAge implements Util{
    @Override
    public void sort(Army army) {
        List<Combatant> humans = new ArrayList<>();
        for (Unit brigade : army.getBrigades()) {
            humans.addAll(((Brigade)brigade).getHumans());
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Enter age: ");
        try {
            int age = Integer.parseInt(input.nextLine());
            List<Combatant> filteredHumans = humans.stream().filter(combatant -> ((Human)combatant).getAge() > age).toList();
            for (Combatant human : filteredHumans) {
                System.out.println(human);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, try again");
            this.sort(army);
        }

        input.close();
    }
}
