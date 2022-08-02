package combatants.machines;

public abstract class Machine {
    double damage;
    int durability;

    protected Machine(double damage, int durability) {
        this.damage = damage;
        this.durability = durability;
    }

    public double getMachineDamage() {
        if (durability <= 0) {
            return damage * 0.2;
        }
        durability--;
        return damage;
    }
    public int getDurability() {
        return durability;
    }

    public static class Factory {
        public static Machine newLandMachine()
        {
            return new Land(MachineDamage.LAND.value, MachineDurability.LAND.value);
        }

        public static Machine newAirMachine()
        {
            return new Air(MachineDamage.AIR.value, MachineDurability.AIR.value);
        }

        enum MachineDamage {
            LAND(50),
            AIR(40);

            public final double value;

            MachineDamage(double value) {
                this.value = value;
            }
        }

        enum MachineDurability {
            LAND(20),
            AIR (30);

            public final int value;

            MachineDurability(int value){
                this.value = value;
            }
        }
    }
}
