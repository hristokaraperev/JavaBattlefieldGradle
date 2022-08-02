package utils;

import combatants.humans.WeaponCarrier;
import units.Army;
import units.Brigade;

public class OverallArmyDamage implements Util {
    @Override
    public void use(Army army) {
        double overallDamage = 0;
        for (Brigade brigade : army.getSubunits()) {
            overallDamage += brigade.getMachine().getMachineDamage();
            overallDamage += brigade.getSubunits().stream().mapToDouble(value -> ((WeaponCarrier)value).getWeapon().getDamage()).sum();
        }
        System.out.println("Overall army damage: " + overallDamage);
    }
}
