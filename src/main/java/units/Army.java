package units;

import combatants.humans.Human;
import combatants.machines.Machine;
import globals.GlobalVariables;

import java.util.ArrayList;
import java.util.List;

public class Army {
    List<Brigade> brigades;
    List<Machine> machines;
    Human general;
    double morale = 1;
    int casualties = 0;
    int originalArmySize = GlobalVariables.brigadesLimit * GlobalVariables.soldiersLimit;

    public Army() {
        this.brigades = new ArrayList<>();
        this.machines = new ArrayList<>();
    }

    public int getOriginalArmySize(){
        return originalArmySize;
    }
    public Human getGeneral() {
        return general;
    }
    public List<Brigade> getSubunits() {
        return brigades;
    }
    public double getMorale() {
        return morale;
    }
    public void updateMorale() {
        if (morale > 0.2) {
            morale = morale - (morale * 0.01);
        }
    }
    public void updateMorale(int multiplier) {
        morale *= multiplier;
    }
    public int getCasualties() {
        return casualties;
    }
    public void incrementCasualties(){
        casualties++;
    }

    public void printArmy(){
        System.out.println("GENERAL");
        System.out.println(general);
        for (Brigade brigade : brigades) {
            System.out.println("BRIGADE");
            System.out.println(brigade.machine);
            for (Human human : brigade.getSubunits()) {
                System.out.println(human);
            }
        }
    }
}
