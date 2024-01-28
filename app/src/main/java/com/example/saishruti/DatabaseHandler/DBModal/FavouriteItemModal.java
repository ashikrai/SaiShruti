package com.example.saishruti.DatabaseHandler.DBModal;

public class FavouriteItemModal {
    private int ItemId;
    private String ItemType;
    private String ItemTitle;

    public FavouriteItemModal(int itemId, String itemType, String itemTitle) {
        this.ItemId= itemId;
        this.ItemType= itemType;
        this.ItemTitle= itemTitle;
    }

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }

    public String getItemType() {
        return ItemType;
    }

    public void setItemType(String itemType) {
        ItemType = itemType;
    }

    public String getItemTitle() {
        return ItemTitle;
    }

    public void setItemTitle(String itemTitle) {
        ItemTitle = itemTitle;
    }
}
