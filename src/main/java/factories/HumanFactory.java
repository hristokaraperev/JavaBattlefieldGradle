package factories;

import combatants.abstractions.Combatant;
import combatants.humans.General;
import combatants.humans.Melee;
import combatants.humans.Ranged;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HumanFactory {

    // responsible for creating individual human entities
    public Combatant createHuman(String type, double soldierHealth, double generalHealth) {

        if (type == null) {
            return null;
        }
        WeaponFactory weaponFactory = new WeaponFactory();
        switch (type.toUpperCase()) {
            case "MELEE":
                Melee melee = new Melee();
                melee.setName(setCombatantName());
                melee.setAge(setCombatantAge());
                melee.setHealthPoints(soldierHealth);
                melee.setWeapon(weaponFactory.createWeapon(type));
                melee.setDamage(melee.getWeapon().getDamage());
                return melee;
            case "RANGED":
                Ranged ranged = new Ranged();
                ranged.setName(setCombatantName());
                ranged.setAge(setCombatantAge());
                ranged.setHealthPoints(soldierHealth);
                ranged.setWeapon(weaponFactory.createWeapon(type));
                ranged.setDamage(ranged.getWeapon().getDamage());
                return ranged;
            case "GENERAL":
                General general = new General();
                general.setName(setCombatantName());
                general.setAge(setCombatantAge());
                general.setHealthPoints(generalHealth);
                general.setExperience(setGeneralExperience());
                general.setWeapon(null);
                return general;
            default:
                return null;

        }
    }

    private static int setGeneralExperience() {
        Random rng = new Random();
        return rng.nextInt(50, 101);
    }

    private static int setCombatantAge() {
        Random rng = new Random();
        return rng.nextInt(18, 40);
    }


    // function that takes a text file of human names
    // and picks one at random to be assigned to the human
    private static String setCombatantName() {
        File file = new File("resources/SoldierNames");
        try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(" ");
            }
            StringTokenizer st = new StringTokenizer(sb.toString(), " ");
            List<String> names = new ArrayList<>();
            while (st.hasMoreTokens()) {
                names.add(st.nextToken());
            }
            Random rng = new Random();
            int indexOfName = rng.nextInt(0, names.size());
            return names.get(indexOfName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
