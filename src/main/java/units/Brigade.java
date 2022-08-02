package units;

import combatants.humans.Human;
import combatants.machines.Machine;

import java.util.ArrayList;
import java.util.List;

public class Brigade {
    List<Human> soldiers;
    Machine machine;

    public Brigade() {
        this.soldiers = new ArrayList<>();
    }

    public Machine getMachine() {
        return machine;
    }
    public List<Human> getSubunits() {
        return soldiers;
    }

}
