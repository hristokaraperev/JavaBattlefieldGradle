package factories;

import weapons.MeleeWeapon;
import weapons.RangedWeapon;
import weapons.abstractions.Weapon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WeaponFactory {

    // maps referenced in weapon and machine creation
    // public so that MachineFactory can have easy access
    // to the values
    public final Map<String, Integer> weaponDamageValues = new HashMap<>();
    public final Map<String, Integer> weaponDurabilityValues = new HashMap<>();

    public WeaponFactory() {
        weaponDamageValues.put("Knife", 15);
        weaponDamageValues.put("Baton", 10);
        weaponDamageValues.put("Axe", 25);
        weaponDamageValues.put("Rifle", 20);
        weaponDamageValues.put("Handgun", 10);
        weaponDamageValues.put("Flamethrower", 5);
        weaponDamageValues.put("Sniper", 25);
        weaponDamageValues.put("LAND", 50);
        weaponDamageValues.put("AIR", 50);

        weaponDurabilityValues.put("Knife", 15);
        weaponDurabilityValues.put("Baton", 20);
        weaponDurabilityValues.put("Axe", 10);
        weaponDurabilityValues.put("Rifle", 20);
        weaponDurabilityValues.put("Handgun", 15);
        weaponDurabilityValues.put("Flamethrower", 15);
        weaponDurabilityValues.put("Sniper", 20);
        weaponDurabilityValues.put("LAND", 10);
        weaponDurabilityValues.put("AIR", 10);
    }


    // responsible for creating weapons for humans and helps with the creation of war machines
    // by providing public access to maps
    public Weapon createWeapon(String type) {


        if (type == null) {
            return null;
        }
        switch (type.toUpperCase()) {
            case "MELEE":
                Weapon meleeWeapon = new MeleeWeapon();
                meleeWeapon.setName(setMeleeWeaponName());
                meleeWeapon.setDamage(weaponDamageValues.get(meleeWeapon.getName()));
                meleeWeapon.setDurability(weaponDurabilityValues.get(meleeWeapon.getName()));
                return meleeWeapon;
            case "RANGED":
                Weapon rangedWeapon = new RangedWeapon();
                rangedWeapon.setName(setRangedWeaponName());
                rangedWeapon.setDamage(weaponDamageValues.get(rangedWeapon.getName()));
                rangedWeapon.setDurability(weaponDurabilityValues.get(rangedWeapon.getName()));
                return rangedWeapon;
            default:
                return null;
        }
    }

    // function that takes a text file of weapon names
    // and picks one at random to be assigned to the weapon
    // based on weapon name, the weapon get its damage and durability values
    private static String setRangedWeaponName() {
        File file = new File("resources/RangedWeapons");
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


    // function that takes a text file of weapon names
    // and picks one at random to be assigned to the weapon
    // based on weapon name, the weapon get its damage and durability values
    private static String setMeleeWeaponName() {
        File file = new File("resources/MeleeWeapons");
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
