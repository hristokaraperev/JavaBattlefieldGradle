package combatants.humans;

import combatants.machines.Machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Human {
    String name;
    int age;
    double health;

    protected Human(String name, int age, double health) {
        this.name = name;
        this.age = age;
        this.health = health;
    }

    public double getHealth() {
        return health;
    }
    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    public void receiveAttack(WeaponCarrier attacker, double attackerMorale) {
        health -= (attacker.getWeaponDamage() * attackerMorale);
    }

    public void receiveAttack(Machine machine) {
        health -= machine.getMachineDamage();
    }

    public static class Factory {
        static List<String> humanNames;

        static {
            humanNames = loadNames();
        }

        public static Human newMeleeSoldier(double health)
        {
            return new Melee(setName(), setAge(), health);
        }

        public static Human newRangedSoldier(double health)
        {
            return new Ranged(setName(), setAge(), health);
        }

        public static Human newGeneral(double health)
        {
            return new General(setName(), setAge(), health);
        }

        private static String setName() {
            Random rng = new Random();
            return humanNames.get(rng.nextInt(humanNames.size()));
        }
        private static int setAge() {
            Random rng = new Random();
            return rng.nextInt(18, 56);
        }
        private static List<String> loadNames() {

            InputStream inputStream = ClassLoader.getSystemResourceAsStream("HumanNames");

            if (inputStream == null) {
                System.err.println("No file named \"HumanNames\" found!");
                System.exit(1);
            }

            List<String> humanNames = new ArrayList<>();

            try (InputStreamReader reader = new InputStreamReader(inputStream);
                 BufferedReader br = new BufferedReader(reader)) {
                String line;
                while ((line = br.readLine()) != null) {
                    humanNames.add(line);
                }
                return humanNames;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return humanNames;
        }
    }
}
