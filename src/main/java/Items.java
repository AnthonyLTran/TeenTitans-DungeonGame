
import java.util.Objects;

public class Items {
        private int itemID;
        private String itemName;
        private String ItemDescription;
        private String itemType;

        public Items() {
        }

        public Items(int itemID, String itemName, String itemDescription, String itemType) {
            this.itemID = itemID;
            this.itemName = itemName;
            this.ItemDescription = itemDescription;
            this.itemType = itemType;
        }

        public int getItemID() {
            return this.itemID;
        }

        public void setItemID(int itemID) {
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

        public String toString() {
            return "Item{itemID='" + this.itemID + "'itemName='" + this.itemName + "'\n, ItemDescription='" + this.ItemDescription + "', itemType='" + this.itemType + "'\n";
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            } else if (o != null && this.getClass() == o.getClass()) {
                Items items = (Items)o;
                return this.itemID == items.itemID && Objects.equals(this.itemName, items.itemName) && Objects.equals(this.ItemDescription, items.ItemDescription) && Objects.equals(this.itemType, items.itemType);
            } else {
                return false;
            }
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.itemID, this.itemName, this.ItemDescription, this.itemType});
        }
    }
