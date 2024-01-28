package com.example.saishruti.DatabaseHandler.DBModal;

import android.util.Log;

public class BhajanLiteModal {

    private String BhajanName;
    private int bhajanId;

    public BhajanLiteModal( int BhajanID, String BhajanName){
        Log.d("BhajanModal","BhajanLiteModal: ID "+BhajanID+" Name "+BhajanName);
        this.bhajanId= BhajanID;
        this.BhajanName= BhajanName;
    }

    public String getBhajanName() {
        return BhajanName;
    }

    public void setBhajanName(String BhajanName) {
        this.BhajanName = BhajanName;
    }

    public int getBhajanId() {
        return bhajanId;
    }

    public void setBhajanId(int bhajanId) {
        this.bhajanId = bhajanId;
    }
}
