package utils;

import combatants.humans.WeaponCarrier;
import units.Army;
import units.Brigade;

public class OverallArmyDurability implements Util{
    @Override
    public void use(Army army) {
        int overallDurability = 0;
        for (Brigade brigade : army.getSubunits()) {
            overallDurability += brigade.getMachine().getDurability();
            overallDurability += brigade.getSubunits().stream().mapToInt(value -> ((WeaponCarrier)value).getWeapon().getDurability()).sum();
        }
        System.out.println("Overall army durability: " + overallDurability);
    }
}
