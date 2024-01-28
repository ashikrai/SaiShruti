package com.example.saishruti.DatabaseHandler.DBModal;

import android.util.Log;

public class DeityModal {
    private String DeityName;
    private String DeityCategory;
    private int DeityID;
    private byte[] DeityImage;

    public DeityModal(int deityID, String deityName, String deityCategory, byte[] deityImage){
        Log.d("DeityModal","ID "+deityID+" Name "+deityName+" category "+deityCategory);
        this.DeityID =  deityID;
        this.DeityName= deityName;
        this.DeityCategory = deityCategory;
        this.DeityImage = deityImage;
    }

    public byte[] getDeityImage() { return DeityImage; }

    public void setDeityImage(byte[] deityImage) { DeityImage = deityImage; }

    public String getDeityName() {
        return DeityName;
    }

    public void setDeityName(String deityName) {
        DeityName = deityName;
    }

    public String getDeityCategory() {
        return DeityCategory;
    }

    public void setDeityCategory(String deityCategory) {
        DeityCategory = deityCategory;
    }

    public int getDeityID() {
        return DeityID;
    }

    public void setDeityID(int deityID) {
        DeityID = deityID;
    }
}
