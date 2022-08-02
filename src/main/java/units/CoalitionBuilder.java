package units;

import combatants.humans.Human;
import combatants.machines.Machine;
import globals.GlobalVariables;

import java.util.Random;

public class CoalitionBuilder {
    Random rng = new Random();
    Coalition coalition = new Coalition();

    public ArmyBuilder setCoalitionName(String name) {
        if (!coalition.armies.isEmpty()) {
            coalition = new Coalition();
        }
        coalition.coalitionName = name;
        return new ArmyBuilder(coalition);
    }

    public Coalition build() {
        return coalition;
    }

    public static class ArmyBuilder extends CoalitionBuilder{
        ArmyBuilder(Coalition coalition) {
            this.coalition = coalition;
        }

        public BrigadeBuilder recruitArmies() {
            for (int armyIndex = 0; armyIndex < GlobalVariables.armiesLimit; armyIndex++) {
                Army army = new Army();
                army.general = Human.Factory.newGeneral(GlobalVariables.generalHealth);
                coalition.armies.add(army);
            }
            return new BrigadeBuilder(coalition);
        }
    }

    public static class BrigadeBuilder extends CoalitionBuilder {
        BrigadeBuilder(Coalition coalition) {
            this.coalition = coalition;
        }

        public BrigadeBuilder recruitBrigades() {
            for (Army army : coalition.armies) {
                for (int brigadeIndex = 0; brigadeIndex < GlobalVariables.brigadesLimit; brigadeIndex++) {
                    Brigade brigade = new Brigade();
                    switch (rng.nextInt(2)) {
                        case 0 -> {
                            brigade.machine = Machine.Factory.newLandMachine();
                            army.machines.add(brigade.machine);
                        }
                        case 1 -> {
                            brigade.machine = Machine.Factory.newAirMachine();
                            army.machines.add(brigade.machine);
                        }
                    }
                    for (int soldierIndex = 0; soldierIndex < GlobalVariables.soldiersLimit; soldierIndex++) {
                        Human human;
                        switch (rng.nextInt(2)) {
                            case 0 -> {
                                human = Human.Factory.newMeleeSoldier(GlobalVariables.soldierHealth);
                                brigade.soldiers.add(human);
                            }
                            case 1 -> {
                                human = Human.Factory.newRangedSoldier(GlobalVariables.soldierHealth);
                                brigade.soldiers.add(human);
                            }
                        }
                    }
                    army.brigades.add(brigade);
                }
            }
            return this;
        }
    }
}
