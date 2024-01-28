package com.example.saishruti.DatabaseHandler.DBModal;

import android.util.Log;

import com.example.saishruti.Utils.constants.Constants;

public class BhajanModal extends BhajanLiteModal {

    private String bhajanLyrics;
    private String bhajanRaag;
    private String bhajanCategory;
    private String bhajanStatus;
    private int bhajanContributedBy;
    private int bhajanLikeCount;

    public BhajanModal(int BhajanID, String BhajanName, String BhajanLyrics, int BhajanContributedBy){
        super(BhajanID,BhajanName);
        Log.d("BhajanModal","ID "+BhajanID+" Name "+BhajanName);
        this.bhajanLyrics= BhajanLyrics;
//        this.bhajanRaag= BhajanRag;
//        this.bhajanCategory= BhajanCategory;
        this.bhajanContributedBy= BhajanContributedBy;
    }

    public String getBhajanLyrics() {
        return bhajanLyrics;
    }

    public void setBhajanLyrics(String bhajanLyrics) {
        this.bhajanLyrics = bhajanLyrics;
    }

    public String getBhajanRaag() {
        return bhajanRaag;
    }

    public void setBhajanRaag(String bhajanRaag) {
        if (bhajanRaag == null || bhajanRaag.length() == 0)
            bhajanRaag= "NA";
        this.bhajanRaag = bhajanRaag;
    }

    public String getBhajanCategory() {
        return bhajanCategory;
    }

    public void setBhajanCategory(String bhajanCategory) {
        if (bhajanCategory == null || bhajanCategory.length() == 0)
            bhajanCategory= "NA";
        this.bhajanCategory = bhajanCategory;
    }

    public int getBhajanContributedBy() {
        return bhajanContributedBy;
    }

    public void setBhajanContributedBy(int bhajanContributedBy) {
        this.bhajanContributedBy = bhajanContributedBy;
    }

    public String getBhajanStatus() {
        return bhajanStatus;
    }

    public void setBhajanStatus(String bhajanStatus) {
        if (bhajanStatus == null || bhajanStatus.length() == 0)
            bhajanStatus= "NA";

        switch(bhajanStatus) {
            case Constants.bhajanStatusApproved:
                bhajanStatus= "Approved";
                break;
            case Constants.bhajanStatusPending:
                bhajanStatus= "Pending";
                break;
            case Constants.bhajanStatusCorrection:
                bhajanStatus= "Correction Required";
                break;
            case Constants.bhajanStatusRejected:
                bhajanStatus= "Rejected";
                break;
            default:
                bhajanStatus= "NA";
                break;
        }
        this.bhajanStatus = bhajanStatus;
    }

    public int getBhajanLikeCount() {
        return bhajanLikeCount;
    }

    public void setBhajanLikeCount(String bhajanLikeCount) {
        if (bhajanLikeCount == null || bhajanLikeCount.length() == 0)
            this.bhajanLikeCount= 0;
        else {
            try {
                this.bhajanLikeCount = Integer.parseInt(bhajanLikeCount);
            } catch (NumberFormatException ex) {
                this.bhajanLikeCount= 0;
            }
        }
    }
}
