package weapons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public abstract class Weapon {
    String name;
    double damage;
    int durability;

    protected Weapon(String name, double damage, int durability) {
        this.name = name;
        this.damage = damage;
        this.durability = durability;
    }

    public double getDamage() {
        if (durability <= 0) {
            return damage * 0.2;
        }
        durability--;
        return damage;
    }
    public String getName() {
        return name;
    }
    public int getDurability() {
        return durability;
    }

    public static class Factory {

        public static Weapon newMeleeWeapon()
        {
            String weaponName = setWeaponName(meleeWeaponNames);
            return new MeleeWeapon(weaponName, setWeaponDamage(weaponName), setWeaponDurability(weaponName));
        }

        public static Weapon newRangedWeapon()
        {
            String weaponName = setWeaponName(rangedWeaponNames);
            return new RangedWeapon(weaponName, setWeaponDamage(weaponName), setWeaponDurability(weaponName));
        }

        enum WeaponDurability {
            VERY_LOW(15),
            LOW(20),
            MEDIUM(25),
            HIGH(30),
            VERY_HIGH(35);

            public final int value;

            WeaponDurability(int value) {
                this.value = value;
            }
        }
        enum WeaponDamage {
            VERY_LOW(15),
            LOW(20),
            MEDIUM(25),
            HIGH(30),
            VERY_HIGH(35);

            public final double value;

            WeaponDamage(double value) {
                this.value = value;
            }
        }
        static Map<String, WeaponDamage> weaponDamageMap;
        static Map<String, WeaponDurability> weaponDurabilityMap;
        static List<String> meleeWeaponNames;
        static List<String> rangedWeaponNames;

        static {
            meleeWeaponNames = loadMeleeWeaponNames();
            rangedWeaponNames = loadRangedWeaponsNames();

            weaponDamageMap = new HashMap<>();
            weaponDurabilityMap = new HashMap<>();

            weaponDamageMap.put("Axe", WeaponDamage.VERY_HIGH);
            weaponDamageMap.put("Baton", WeaponDamage.MEDIUM);
            weaponDamageMap.put("Knife", WeaponDamage.HIGH);
            weaponDamageMap.put("Flamethrower", WeaponDamage.LOW);
            weaponDamageMap.put("Rifle", WeaponDamage.MEDIUM);
            weaponDamageMap.put("Handgun", WeaponDamage.VERY_LOW);
            weaponDamageMap.put("Sniper", WeaponDamage.HIGH);

            weaponDurabilityMap.put("Axe", WeaponDurability.MEDIUM);
            weaponDurabilityMap.put("Baton", WeaponDurability.VERY_HIGH);
            weaponDurabilityMap.put("Knife", WeaponDurability.HIGH);
            weaponDurabilityMap.put("Flamethrower", WeaponDurability.MEDIUM);
            weaponDurabilityMap.put("Rifle", WeaponDurability.LOW);
            weaponDurabilityMap.put("Handgun", WeaponDurability.HIGH);
            weaponDurabilityMap.put("Sniper", WeaponDurability.VERY_LOW);
        }

        private static int setWeaponDurability(String weaponName) {
            return weaponDurabilityMap.get(weaponName).value;
        }

        private static double setWeaponDamage(String weaponName) {
            return  weaponDamageMap.get(weaponName).value;
        }

        private static String setWeaponName(List<String> weaponNames) {
            Random rng = new Random();
            return weaponNames.get(rng.nextInt(weaponNames.size()));
        }

        private static List<String> loadMeleeWeaponNames() {

            InputStream inputStream = ClassLoader.getSystemResourceAsStream("MeleeWeapons");

            if (inputStream == null) {
                System.err.println("No file named \"MeleeWeapons\" found!");
                System.exit(1);
            }

            List<String> meleeWeaponNames = new ArrayList<>();

            try (InputStreamReader reader = new InputStreamReader(inputStream);
                 BufferedReader br = new BufferedReader(reader)) {
                String line;
                while ((line = br.readLine()) != null) {
                    meleeWeaponNames.add(line);
                }
                return meleeWeaponNames;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return meleeWeaponNames;
        }

        private static List<String> loadRangedWeaponsNames() {

            InputStream inputStream = ClassLoader.getSystemResourceAsStream("RangedWeapons");

            if (inputStream == null) {
                System.err.println("No file named \"RangedWeapons\" found!");
                System.exit(1);
            }

            List<String> rangedWeaponsNames = new ArrayList<>();

            try (InputStreamReader reader = new InputStreamReader(inputStream);
                 BufferedReader br = new BufferedReader(reader)) {
                String line;
                while ((line = br.readLine()) != null) {
                    rangedWeaponsNames.add(line);
                }
                return rangedWeaponsNames;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return rangedWeaponsNames;
        }
    }
}
