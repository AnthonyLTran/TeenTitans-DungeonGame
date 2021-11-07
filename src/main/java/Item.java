
import java.util.Objects;

public class Item {
    private String itemID;
    private String itemName;
    private String ItemDescription;
    private String itemType;
    private boolean isAvailable;

    public Item() {
    }

    public Item(String itemID, String itemName, String itemDescription, String itemType, boolean isAvailable) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.ItemDescription = itemDescription;
        this.itemType = itemType;
        this.isAvailable = isAvailable;
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

    @Override
    public String toString() {
        return "Item{" +
                "itemID=" + itemID +
                ", itemName='" + itemName + '\'' +
                ", ItemDescription='" + ItemDescription + '\'' +
                ", itemType='" + itemType + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemID == item.itemID && isAvailable == item.isAvailable && Objects.equals(itemName, item.itemName) && Objects.equals(ItemDescription, item.ItemDescription) && Objects.equals(itemType, item.itemType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemID, itemName, ItemDescription, itemType, isAvailable);
    }
}
