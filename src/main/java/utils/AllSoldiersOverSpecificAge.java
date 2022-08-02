package utils;

import combatants.humans.Human;
import units.Army;
import units.Brigade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AllSoldiersOverSpecificAge implements Util{
    @Override
    public void use(Army army) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter age: ");
        String s;
        int age;
        List<Human> filteredHumans = new ArrayList<>();
        try {
            if ((s = br.readLine()) != null &&
                (age = Integer.parseInt(s)) > 0) {
                for (Brigade brigade : army.getSubunits()) {
                    filteredHumans.addAll(brigade.getSubunits().stream().filter(human -> human.getAge() > age).toList());
                }
                for (Human human : filteredHumans) {
                    System.out.println(human);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
