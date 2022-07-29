package factories;

import combatants.abstractions.Combatant;
import combatants.abstractions.Machine;
import composite.Army;
import composite.Brigade;
import composite.abstractions.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CoalitionBuilder {

    // variables that control the size of the coalition
    private int ARMIES_PER_COALITION = 0;
    private int BRIGADES_PER_ARMY = 0;
    private int SOLDIERS_PER_BRIGADE = 0;
    private double HEALTH_PER_SOLDIER = 0;
    private double HEALTH_PER_GENERAL = 0;

    public CoalitionBuilder(int armies, int brigades, int humans, double soldierHealth, double generalHealth) {
        this.ARMIES_PER_COALITION = armies;
        this.BRIGADES_PER_ARMY = brigades;
        this.SOLDIERS_PER_BRIGADE = humans;
        this.HEALTH_PER_SOLDIER = soldierHealth;
        this.HEALTH_PER_GENERAL = generalHealth;
    }

    // responsible for building the coalition game unit
    public List<Unit> createCoalition() {
        HumanFactory humanFactory = new HumanFactory();
        MachineFactory machineFactory = new MachineFactory();
        List<Unit> armies = new ArrayList<>();
        String[] humanTypes = {"MELEE", "RANGED"};
        String[] machineTypes = {"LAND", "AIR"};
        Random rng = new Random();
        for (int i = 0; i < ARMIES_PER_COALITION; i++) {
            Army army = new Army();
            List<Unit> brigades = new ArrayList<>();
            List<Machine> machines = new ArrayList<>();
            for (int j = 0; j < BRIGADES_PER_ARMY; j++) {
                Brigade brigade = new Brigade();
                Machine machine = machineFactory.createMachine(machineTypes[rng.nextInt(machineTypes.length)]);
                brigade.setWarMachine(machine);
                machines.add(machine);
                List<Combatant> soldiers = new ArrayList<>();
                for (int k = 0; k < SOLDIERS_PER_BRIGADE; k++) {
                    Combatant soldier = humanFactory.createHuman(humanTypes[rng.nextInt(humanTypes.length)], HEALTH_PER_SOLDIER, HEALTH_PER_GENERAL);
                    soldiers.add(soldier);
                }
                brigade.setHumans(soldiers);
                brigades.add(brigade);
            }
            army.setBrigades(brigades);
            army.setMachines(machines);
            army.setGeneral(humanFactory.createHuman("GENERAL", HEALTH_PER_SOLDIER, HEALTH_PER_GENERAL));
            army.setInitialArmyCapacity(BRIGADES_PER_ARMY * SOLDIERS_PER_BRIGADE);
            armies.add(army);
        }
        return armies;


    }

}
