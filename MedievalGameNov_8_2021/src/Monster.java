import java.util.ArrayList;
import java.util.Objects;

/**
 * A subclass of the GameCharacter class encapsulating the functions of the monster character
 */
public class Monster {

    private String monsterID;
    private String monsterName;
    private ArrayList<String> monsterDescription;
    private ArrayList<String> defeatedDescription;
    private int currentHP;
    private int dealDamage;
    private String droppedItem;
    private int fixNumber;
    private boolean defeated = true;

    public String getMonsterID() {
        return monsterID;
    }

    public void setMonsterID(String monsterID) {
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

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getDealDamage() {
        return dealDamage;
    }

    public void setDealDamage(int dealDamage) {
        this.dealDamage = dealDamage;
    }

    public String getDroppedItem() {
        return droppedItem;
    }

    public void setDroppedItem(String droppedItem) {
        this.droppedItem = droppedItem;
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
                "monsterID=" + monsterID +
                ", monsterName='" + monsterName + '\'' +
                ", monsterDescription=" + monsterDescription +
                ", defeatedDescription=" + defeatedDescription +
                ", currentHP=" + currentHP +
                ", dealDamage=" + dealDamage +
                ", droppedItem=" + droppedItem +
                ", fixNumber=" + fixNumber +
                ", defeated=" + defeated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monster monster = (Monster) o;
        return monsterID == monster.monsterID && defeated == monster.defeated && Objects.equals(monsterName, monster.monsterName) && Objects.equals(monsterDescription, monster.monsterDescription) && Objects.equals(defeatedDescription, monster.defeatedDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monsterID, monsterName, monsterDescription, defeatedDescription, defeated);
    }
}