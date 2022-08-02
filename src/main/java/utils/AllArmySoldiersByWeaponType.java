package utils;

import combatants.humans.Human;
import combatants.humans.WeaponCarrier;
import units.Army;
import units.Brigade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AllArmySoldiersByWeaponType implements Util{
    @Override
    public void use(Army army) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter weapon type: ");
        String s;
        List<Human> filteredHumans = new ArrayList<>();
        try {
            if ((s = br.readLine()) != null) {
                for (Brigade brigade : army.getSubunits()) {
                    filteredHumans.addAll(brigade.getSubunits().stream().filter(human -> ((WeaponCarrier)human).getWeapon().getName().equalsIgnoreCase(s)).toList());
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
