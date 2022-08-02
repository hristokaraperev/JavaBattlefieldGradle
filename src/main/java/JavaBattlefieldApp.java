import combatants.humans.General;
import globals.GlobalVariables;
import units.Coalition;
import units.CoalitionBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JavaBattlefieldApp {
    public static void main(String[] args) throws IOException, InterruptedException {
        GlobalVariables.soldierHealth = setSoldierHealth();
        GlobalVariables.generalHealth = setGeneralHealth();
        GlobalVariables.armiesLimit = setArmiesLimit();
        GlobalVariables.brigadesLimit = setBrigadesLimit();
        GlobalVariables.soldiersLimit = setSoldiersLimit();

        CoalitionBuilder builder = new CoalitionBuilder();
        Coalition goodCoalition = builder
                .setCoalitionName("Good Coalition")
                .recruitArmies()
                .recruitBrigades()
                .build();

        Coalition badCoalition = builder
                .setCoalitionName("Bad Coalition")
                .recruitArmies()
                .recruitBrigades()
                .build();

        System.out.println();

        System.out.println();

        int goodCoalitionWarExperience = goodCoalition
                .getSubunits().stream()
                .mapToInt(value -> ((General)value.getGeneral()).getExperience())
                .sum();
        int badCoalitionWarExperience = badCoalition
                .getSubunits().stream()
                .mapToInt(value -> ((General)value.getGeneral()).getExperience())
                .sum();

        Game game;

        if (goodCoalitionWarExperience > badCoalitionWarExperience) {
            game = new Game(goodCoalition, badCoalition);
        } else {
            game = new Game(badCoalition, goodCoalition);
        }
    }

    public static int setSoldiersLimit() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int soldiersLimit;
        while (true) {
            try {
                System.out.print("Enter the limit of soldiers per brigade: ");
                if ((s = reader.readLine()) != null
                        && (soldiersLimit = Integer.parseInt(s)) > 0) {
                    return soldiersLimit;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
    }

    public static int setBrigadesLimit() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int brigadesLimit;
        while (true) {
            try {
                System.out.print("Enter the limit of brigades per army: ");
                if ((s = reader.readLine()) != null
                        && (brigadesLimit = Integer.parseInt(s)) > 0) {
                    return brigadesLimit;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
    }

    public static int setArmiesLimit() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int armiesLimit;
        while (true) {
            try {
                System.out.print("Enter the limit of armies per coalition: ");
                if ((s = reader.readLine()) != null
                        && (armiesLimit = Integer.parseInt(s)) > 0) {
                    return armiesLimit;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
    }

    public static double setSoldierHealth() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        double health;
        while (true) {
            try {
                System.out.print("Enter health value for soldiers: ");
                if ((s = reader.readLine()) != null
                        && (health = Double.parseDouble(s)) > 0) {
                    return health;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
    }

    public static double setGeneralHealth() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        double health;
        while (true) {
            try {
                System.out.print("Enter health value for generals: ");
                if ((s = reader.readLine()) != null
                        && (health = Double.parseDouble(s)) > 0) {
                    return health;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
    }
}
