import combatants.humans.Human;
import combatants.humans.WeaponCarrier;
import units.Army;
import units.Brigade;
import units.Coalition;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {
    Random rng = new Random();
    public Game(Coalition attackingCoalition, Coalition defendingCoalition) throws InterruptedException {

        Coalition winner = null;

        while ((winner = haveWinner(attackingCoalition, defendingCoalition)) == null) {
            attackTurn(attackingCoalition, defendingCoalition);
            attackTurn(defendingCoalition, attackingCoalition);

            AtomicInteger attackingCoalitionSoldiersAlive = new AtomicInteger();
            attackingCoalition
                    .getSubunits()
                    .forEach(army -> {attackingCoalitionSoldiersAlive.addAndGet(army
                                                                        .getSubunits().stream()
                                                                        .mapToInt(value -> value.getSubunits().size())
                                                                        .sum());
            });

            AtomicInteger defendingCoalitionSoldiersAlive = new AtomicInteger();
            defendingCoalition
                    .getSubunits()
                    .forEach(army -> {defendingCoalitionSoldiersAlive.addAndGet(army
                                                                        .getSubunits().stream()
                                                                        .mapToInt(value -> value.getSubunits().size())
                                                                        .sum());
            });


            StringBuffer sb = new StringBuffer();
            sb
                .append(attackingCoalition.getCoalitionName())
                .append(" soldiers left: ")
                .append(attackingCoalitionSoldiersAlive)
                .append(" ")
                .append(defendingCoalition.getCoalitionName())
                .append(" soldiers left: ")
                .append(defendingCoalitionSoldiersAlive)
                .append("\r");
            System.out.print(sb);
            Thread.sleep(1000);
        }
        System.out.println("WINNER");
        System.out.println(winner);
    }

    private void attackTurn(Coalition attackingCoalition, Coalition defendingCoalition)
    {
        for (Army attackingCoalitionArmy : attackingCoalition.getSubunits())
        {
            int defendingCoalitionArmy = rng.nextInt(defendingCoalition.getSubunits().size());
            List<Brigade> attackingCoalitionArmyBrigades = attackingCoalitionArmy.getSubunits();
            List<Brigade> defendingCoalitionArmyBrigades = defendingCoalition
                                                            .getSubunits()
                                                            .get(defendingCoalitionArmy)
                                                            .getSubunits();

            for (Brigade attackingCoalitionArmyBrigade : attackingCoalitionArmyBrigades)
            {
                int defendingCoalitionArmyBrigade = rng.nextInt(defendingCoalitionArmyBrigades.size());
                List<Human> attackingCoalitionArmyBrigadeSoldiers = attackingCoalitionArmyBrigade.getSubunits();
                List<Human> defendingCoalitionArmyBrigadeSoldiers = defendingCoalitionArmyBrigades
                                                                        .get(defendingCoalitionArmyBrigade)
                                                                        .getSubunits();

                for (Human attackingCoalitionArmyBrigadeSoldier : attackingCoalitionArmyBrigadeSoldiers)
                {
                    int defendingCoalitionArmyBrigadeSoldierIndex = rng.nextInt(defendingCoalitionArmyBrigadeSoldiers.size());
                    Human defendingCoalitionArmyBrigadeSoldier = defendingCoalitionArmyBrigadeSoldiers.get(defendingCoalitionArmyBrigadeSoldierIndex);

                    if (rng.nextInt(100) < 49)
                    {
                        defendingCoalitionArmyBrigadeSoldier.receiveAttack(attackingCoalitionArmyBrigade.getMachine());
                    }
                    if (rng.nextInt(defendingCoalition
                            .getSubunits()
                            .get(defendingCoalitionArmy)
                            .getOriginalArmySize()
                        - defendingCoalition
                            .getSubunits()
                            .get(defendingCoalitionArmy)
                            .getCasualties()) < 1)
                    {
                        defendingCoalition.getSubunits()
                                .get(defendingCoalitionArmy)
                                .getGeneral()
                                .receiveAttack((WeaponCarrier) attackingCoalitionArmyBrigadeSoldier,
                                                attackingCoalitionArmy.getMorale());
                        if (defendingCoalition
                                .getSubunits()
                                .get(defendingCoalitionArmy)
                                .getGeneral()
                                .getHealth() <= 0)
                        {
                            break;
                        }
                    } else
                    {
                        defendingCoalitionArmyBrigadeSoldier.receiveAttack((WeaponCarrier) attackingCoalitionArmyBrigadeSoldier,
                                                                            attackingCoalitionArmy.getMorale());
                    }

                    if (defendingCoalitionArmyBrigadeSoldier.getHealth() <= 0)
                    {
                        defendingCoalition.getSubunits()
                                .get(defendingCoalitionArmy)
                                .incrementCasualties();
                        if (defendingCoalition
                                .getSubunits()
                                .get(defendingCoalitionArmy)
                                .getCasualties() % 50 == 0 &&
                            defendingCoalition
                                .getSubunits()
                                .get(defendingCoalitionArmy)
                                .getCasualties() != 0)
                        {
                            defendingCoalition
                                    .getSubunits()
                                    .get(defendingCoalitionArmy)
                                    .updateMorale();
                        }
                        if ((defendingCoalition
                                .getSubunits()
                                .get(defendingCoalitionArmy)
                                .getOriginalArmySize()
                            - defendingCoalition
                                .getSubunits()
                                .get(defendingCoalitionArmy)
                                .getCasualties()) <
                                (defendingCoalition
                                        .getSubunits()
                                        .get(defendingCoalitionArmy)
                                        .getOriginalArmySize() * 0.2))
                        {
                            if (rng.nextInt(100) < 49)
                            {
                                defendingCoalition
                                        .getSubunits()
                                        .get(defendingCoalitionArmy)
                                        .updateMorale(2);
                            }
                        }
                        defendingCoalitionArmyBrigadeSoldiers.remove(defendingCoalitionArmyBrigadeSoldier);
                        if (defendingCoalitionArmyBrigadeSoldiers.isEmpty())
                        {
                            break;
                        }
                    }
                }
                if (defendingCoalition
                        .getSubunits()
                        .get(defendingCoalitionArmy)
                        .getGeneral()
                        .getHealth() <= 0)
                {
                    break;
                }
                if (defendingCoalitionArmyBrigadeSoldiers.isEmpty())
                {
                    defendingCoalitionArmyBrigades.remove(defendingCoalitionArmyBrigade);
                }
                if (defendingCoalitionArmyBrigades.isEmpty())
                {
                    break;
                }
            }
            if (defendingCoalition
                    .getSubunits()
                    .get(defendingCoalitionArmy)
                    .getGeneral()
                    .getHealth() <= 0)
            {
                defendingCoalition.getSubunits().remove(defendingCoalitionArmy);
                if (defendingCoalition.getSubunits().isEmpty())
                {
                    break;
                }
                break;
            }
            if (defendingCoalitionArmyBrigades.isEmpty())
            {
                defendingCoalition.getSubunits().remove(defendingCoalitionArmy);
                if (defendingCoalition.getSubunits().isEmpty())
                {
                    break;
                }
            }
        }
    }

    public Coalition haveWinner(Coalition attackingCoalition, Coalition defendingCoalition) {
        if (attackingCoalition.getSubunits().isEmpty())
        {
            return defendingCoalition;
        } else if (defendingCoalition.getSubunits().isEmpty())
        {
            return attackingCoalition;
        } else
        {
            return null;
        }
    }
}
