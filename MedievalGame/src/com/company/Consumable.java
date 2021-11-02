package com.company;

import java.util.Objects;

/**
 * Class encapsulating consumable type of item
 */
public class Consumable extends Item {

    private int healthEffect;
    private boolean isConsumed;

    public Consumable(){};
    public Consumable(int  id, String name, String Description, String type, int healthEffect){
        super(id,name,Description,type);
        this.healthEffect=healthEffect;
        this.isConsumed=false;
    }

    public int getHealthEffect() {
        return healthEffect;
    }

    public void setHealthEffect(int healthEffect) {
        this.healthEffect = healthEffect;
    }

    public boolean isConsumed() {
        return isConsumed;
    }

    public void setConsumed(boolean consumed) {
        isConsumed = consumed;
    }

    @Override
    public String toString() {
        return "Consumable{" + super.toString()+
                " healthEffect=" + healthEffect +
                ", isConsumed=" + isConsumed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Consumable that = (Consumable) o;
        return healthEffect == that.healthEffect && isConsumed == that.isConsumed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), healthEffect, isConsumed);
    }
}
