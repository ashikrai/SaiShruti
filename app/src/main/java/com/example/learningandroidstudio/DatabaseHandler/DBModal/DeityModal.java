package com.example.learningandroidstudio.DatabaseHandler.DBModal;

import android.util.Log;

public class DeityModal {
    private String DeityName;
    private String DeityCategory;
    private int DeityID;

    public DeityModal( int deityID, String deityName, String deityCategory){
        Log.d("DeityModal","ID "+deityID+" Name "+deityName+" category "+deityCategory);
        this.DeityID =  deityID;
        this.DeityName= deityName;
        this.DeityCategory= deityCategory;
    }

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
