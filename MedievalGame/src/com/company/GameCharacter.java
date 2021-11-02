package com.company;

import java.util.Objects;

/**
 * Super class encapsulating a character in the game.
 */
public class GameCharacter {


    private int HP;
    private int dealDamage;

    /**
     * a method which is overridden in subclasses monster and player
     * to implement attacking each other
     * @return
     */
    public int attack(){
        return 1;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getDealDamage() {
        return dealDamage;
    }

    public void setDealDamage(int dealDamage) {
        this.dealDamage = dealDamage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCharacter that = (GameCharacter) o;
        return HP == that.HP;
    }

    @Override
    public int hashCode() {
        return Objects.hash(HP);
    }
}
