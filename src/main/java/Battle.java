import combatants.humans.General;
import composite.Army;
import composite.Coalition;
import visitor.BattleLine;

import java.util.Scanner;

public class Battle implements Runnable {

    // initialisation of game loop
    @Override
    public void run() {
        int armies = 0;
        int brigades = 0;
        int humans = 0;
        int soldierHealth = 0;
        int generalHealth = 0;

        Scanner input = new Scanner(System.in);

        while (armies == 0 || brigades == 0 || humans == 0 || soldierHealth == 0 || generalHealth == 0){
            try {
                System.out.print("Enter number of armies per coalition: ");
                armies = Integer.parseInt(input.nextLine());
                System.out.print("Enter number of brigades per army: ");
                brigades = Integer.parseInt(input.nextLine());
                System.out.print("Enter number of humans per brigade: ");
                humans = Integer.parseInt(input.nextLine());
                System.out.print("How much health should the soldiers have: ");
                soldierHealth = Integer.parseInt(input.nextLine());
                System.out.print("How much health should the general have: ");
                generalHealth = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again!");
                System.out.println();
            }
        }

        input.close();

        Coalition good = new Coalition(armies, brigades, humans, soldierHealth, generalHealth, "GOOD");
        Coalition bad = new Coalition(armies, brigades, humans, soldierHealth, generalHealth, "EVIL");


        // check which army has more war Knowledge
        int goodExperience = good.getArmies().stream().mapToInt(value -> ((Army) value).getGeneralExperience()).sum();
        int badExperience = bad.getArmies().stream().mapToInt(value -> ((Army) value).getGeneralExperience()).sum();

        BattleLine battleLine = new BattleLine();

        if (goodExperience > badExperience) {
            battle(good, bad, battleLine);
        } else {
            battle(bad, good, battleLine);
        }

    }

    private void battle(Coalition attacker, Coalition defender, BattleLine battleLine) {

        // game loop with end conditions
        while (true) {
            if (battleLine.visit(attacker, defender) && defender.getArmies().isEmpty()) break;
            if (battleLine.visit(defender, attacker) && attacker.getArmies().isEmpty()) break;

        }

        // checks end condition determining the winner and the loser
        if (attacker.getArmies().size() == 0 || attacker.getArmies().stream().anyMatch(unit -> ((General) ((Army) unit).getGeneral()).getHealthPoints() <= 0)) {
            System.out.println("WINNER");
            System.out.println(defender);
            System.out.println("LOSER");
            if (attacker.getArmies().size() == 0) {
                System.out.println("ARMY WAS DESTROYED");
            } else {
                System.out.println(attacker);
            }
        } else {
            System.out.println("WINNER");
            System.out.println(attacker);
            System.out.println("LOSER");
            if (defender.getArmies().size() == 0) {
                System.out.println("ARMY WAS DESTROYED");
            } else {
                System.out.println(defender);
            }
        }

        System.out.println();
        System.out.println("FINISH");

    }
}
