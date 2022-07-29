package weapons;

import weapons.abstractions.Weapon;

public class RangedWeapon extends Weapon {

    @Override
    public String toString() {
        return this.getName();
    }
}
