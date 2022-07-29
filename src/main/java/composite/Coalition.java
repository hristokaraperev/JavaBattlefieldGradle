package composite;

import composite.abstractions.Unit;
import factories.CoalitionBuilder;

import java.util.List;

public class Coalition {

    // coalition game unit
    private List<Unit> armies;
    private String name;

    // builds coalition
    public Coalition(int armies, int brigades, int humans, double soldierHealth, double generalHealth, String name) {
        CoalitionBuilder coalitionBuilder = new CoalitionBuilder(armies, brigades, humans, soldierHealth, generalHealth);
        this.armies = coalitionBuilder.createCoalition();
        this.name = name;
    }

    public List<Unit> getArmies() {
        return armies;
    }

    public void setArmies(List<Unit> armies) {
        this.armies = armies;
    }

    @Override
    public String toString() {
        String message = this.name + "\n";
        for (Unit army : armies) {
            message += army.toString() + "\n";
        }
        return message;
    }
}
