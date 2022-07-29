package factories;

import combatants.abstractions.Machine;
import combatants.machines.AirMachine;
import combatants.machines.LandMachine;

public class MachineFactory {

    // responsible for creating war machines
    public Machine createMachine(String type) {
        if (type == null) {
            return null;
        }
        WeaponFactory weaponFactory = new WeaponFactory();
        switch (type.toUpperCase()) {
            case "LAND":
                Machine landMachine = new LandMachine();
                landMachine.setName(type);
                landMachine.setDamage(weaponFactory.weaponDamageValues.get(type));
                landMachine.setDurability(weaponFactory.weaponDurabilityValues.get(type));
                return landMachine;
            case "AIR":
                Machine airMachine = new AirMachine();
                airMachine.setName(type);
                airMachine.setDamage(weaponFactory.weaponDamageValues.get(type));
                airMachine.setDurability(weaponFactory.weaponDurabilityValues.get(type));
                return airMachine;
            default:
                return null;

        }
    }
}
