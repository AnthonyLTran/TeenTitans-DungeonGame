package com.company;

import java.util.ArrayList;
import java.util.Objects;
/**
 * A subclass of the GameCharacter class encapsulating the functions of the monster character
 */
public class Monster extends GameCharacter {

    private int monsterID;
    private String monsterName;
    private ArrayList<String> monsterDescription;
    private ArrayList<String> defeatedDescription;
    private int fixNumber;
    private boolean defeated;

    public Monster() {
        super.setHP(0);
    }

    public int getMonsterID() {
        return monsterID;
    }

    public void setMonsterID(int monsterID) {
        this.monsterID = monsterID;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }

    public ArrayList<String> getMonsterDescription() {
        return monsterDescription;
    }

    public void setMonsterDescription(ArrayList<String> monsterDescription) {
        this.monsterDescription = monsterDescription;
    }

    public ArrayList<String> getDefeatedDescription() {
        return defeatedDescription;
    }

    public void setDefeatedDescription(ArrayList<String> defeatedDescription) {
        this.defeatedDescription = defeatedDescription;
    }

    public int getFixNumber() {
        return fixNumber;
    }

    public void setFixNumber(int fixNumber) {
        this.fixNumber = fixNumber;
    }

    public boolean isDefeated() {
        return defeated;
    }

    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
    }

    @Override
    public String toString() {
        return "Monster{" +

                ", monsterID=" + monsterID +
                ", monsterName='" + monsterName + '\'' +
                ", \nmonsterDescription=" + monsterDescription +
                ", \ndefeatedDescription=" + defeatedDescription +
                ", \nHP=" + super.getHP() +
                ", Deal Damage =" + super.getDealDamage() +
                ", Fix number =" + fixNumber +
                ", defeated=" + defeated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Monster monster = (Monster) o;
        return monsterID == monster.monsterID && defeated == monster.defeated && Objects.equals(monsterName, monster.monsterName) && Objects.equals(monsterDescription, monster.monsterDescription) && Objects.equals(defeatedDescription, monster.defeatedDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), monsterID, monsterName, monsterDescription, defeatedDescription, defeated);
    }
}
