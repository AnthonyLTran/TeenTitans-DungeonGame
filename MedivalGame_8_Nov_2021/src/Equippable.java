import java.util.Objects;

public class Equippable extends Item {
    private int damageEffect;
    private boolean isEquipped;

    public Equippable() {
    }

    public Equippable(String id, String name, String Description, String type, int damageEffect, boolean isAvailable) {
        super(id, name, Description, type,false,false);
        this.damageEffect = damageEffect;
        this.isEquipped = false;
    }

    public int getDamageEffect() {
        return this.damageEffect;
    }

    public void setDamageEffect(int damageEffect) {
        this.damageEffect = damageEffect;
    }

    public boolean isEquipped() {
        return this.isEquipped;
    }

    public void setEquipped(boolean equipped) {
        this.isEquipped = equipped;
    }

    public String toString() {
        String var10000 = super.toString();
        return "Equippable{" + var10000 + "damageEffect=" + this.damageEffect + ", isEquipped=" + this.isEquipped + "}";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            if (!super.equals(o)) {
                return false;
            } else {
                Equippable that = (Equippable)o;
                return this.damageEffect == that.damageEffect && this.isEquipped == that.isEquipped;
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{super.hashCode(), this.damageEffect, this.isEquipped});
    }
}