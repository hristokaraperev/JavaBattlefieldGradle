package composite.abstractions;

import combatants.abstractions.Combatant;

public abstract class Unit {
    // building block of the coalition
    public abstract int isGettingEngagedBy(Unit unit, Combatant general);

}
