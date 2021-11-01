package com.company;

import java.util.Objects;

/**
 * Encapsulates the functions of Items super class which has subclasses
 * equippable and consumable
 */
public class Item {
    private int itemID;
    private String itemName;
    private String ItemDescription;
    private String itemType;
    //private int pointsGained;
    public Item(){

    }

    public Item(int itemID, String itemName, String itemDescription, String itemType) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.ItemDescription = itemDescription;
        this.itemType = itemType;
        //this.pointsGained=pointsGained;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return ItemDescription;
    }

    public void setItemDescription(String itemDescription) {
        ItemDescription = itemDescription;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemID='" + itemID + '\'' +
                "itemName='" + itemName + '\'' +
                "\n, ItemDescription='" + ItemDescription + '\'' +
                ", itemType='" + itemType + '\'' +
                //", pointsGained=" + pointsGained +
                "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemID == item.itemID &&  Objects.equals(itemName, item.itemName) && Objects.equals(ItemDescription, item.ItemDescription) && Objects.equals(itemType, item.itemType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemID, itemName, ItemDescription, itemType);
    }
}
