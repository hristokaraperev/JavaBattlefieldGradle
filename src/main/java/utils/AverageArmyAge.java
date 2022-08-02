package utils;

import combatants.humans.Human;
import units.Army;
import units.Brigade;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class AverageArmyAge implements Util{
    @Override
    public void use(Army army) {
        double averageArmyAge = 0;
        List<Human> allHumans = new ArrayList<>();
        allHumans.add(army.getGeneral());
        for (Brigade brigade : army.getSubunits()) {
            allHumans.addAll(brigade.getSubunits());
        }
        averageArmyAge = allHumans.stream().mapToInt(Human::getAge).average().getAsDouble();
        String format = "0.00";
        NumberFormat formatter = new DecimalFormat(format);
        System.out.println("Average age in the army is: " + formatter.format(averageArmyAge));
    }
}
