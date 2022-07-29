package weapons;

import weapons.abstractions.Weapon;

public class MeleeWeapon extends Weapon {
    @Override
    public String toString() {
        return this.getName();
    }
}
