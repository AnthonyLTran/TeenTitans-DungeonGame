import java.util.Objects;

public class Item {
    private String itemID;
    private String itemName;
    private String ItemDescription;
    private String itemType;
    private boolean isAvailable;
    private boolean isUsed;
    public Item() {
    }

    public Item(String itemID, String itemName, String itemDescription,
                String itemType, boolean isAvailable,boolean isUsed) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.ItemDescription = itemDescription;
        this.itemType = itemType;
        this.isAvailable = isAvailable;
        this.isUsed=isUsed;
    }

    public String getItemID() {
        return this.itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return this.ItemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.ItemDescription = itemDescription;
    }

    public String getItemType() {
        return this.itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemID=" + itemID +
                ", itemName='" + itemName + '\'' +
                ", ItemDescription='" + ItemDescription + '\'' +
                ", itemType='" + itemType + '\'' +
                ", isAvailable=" + isAvailable +
                ", isUsed=" + isUsed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return isAvailable == item.isAvailable && isUsed == item.isUsed && Objects.equals(itemID, item.itemID) && Objects.equals(itemName, item.itemName) && Objects.equals(ItemDescription, item.ItemDescription) && Objects.equals(itemType, item.itemType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemID, itemName, ItemDescription, itemType, isAvailable, isUsed);
    }
}
