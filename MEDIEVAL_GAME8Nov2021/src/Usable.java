import java.util.Objects;

public class Usable extends Item {
    private int healthEffect;
    private boolean isConsumed;

    public Usable() {
    }

    public Usable(String id, String name, String Description, String type, int healthEffect) {
        super(id, name, Description, type, false,false);
        this.healthEffect = healthEffect;
        this.isConsumed = false;
    }

    public int getHealthEffect() {
        return this.healthEffect;
    }

    public void setHealthEffect(int healthEffect) {
        this.healthEffect = healthEffect;
    }

    public boolean isConsumed() {
        return this.isConsumed;
    }

    public void setConsumed(boolean consumed) {
        this.isConsumed = consumed;
    }

    public String toString() {
        String var10000 = super.toString();
        return "Consumable{" + var10000 + " healthEffect=" + this.healthEffect + ", isConsumed=" + this.isConsumed + "}";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            if (!super.equals(o)) {
                return false;
            } else {
                Usable that = (Usable)o;
                return this.healthEffect == that.healthEffect && this.isConsumed == that.isConsumed;
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{super.hashCode(), this.healthEffect, this.isConsumed});
    }
}