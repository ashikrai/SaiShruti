/**
 * Created by AshikR1
 * Date: 1/13/2024.
 */

package com.example.saishruti.DataContainer;

import com.example.saishruti.DatabaseHandler.DBModal.DeityModal;

import java.util.ArrayList;
import java.util.Map;

public class BhajanDataContainer {
    ArrayList<DeityModal> deityData;
    Map<Integer, Integer> deityWiseBhajanCount;
    DeityModal selectedDeity;

    private static BhajanDataContainer dataContainer;

    private BhajanDataContainer(){
        this.deityData= null;
        this.deityWiseBhajanCount= null;
    }

    public static BhajanDataContainer getInstance(){
        if (dataContainer == null)
            dataContainer= new BhajanDataContainer();
        return dataContainer;
    }

    // Getter Setter
    public ArrayList<DeityModal> getDeityData() { return deityData; }
    public void setDeityData(ArrayList<DeityModal> deityData) { this.deityData = deityData; }

    public Map<Integer, Integer> getDeityWiseBhajanCount() { return deityWiseBhajanCount; }
    public void setDeityWiseBhajanCount(Map<Integer, Integer> deityWiseBhajanCount) { this.deityWiseBhajanCount = deityWiseBhajanCount; }

    public DeityModal getSelectedDeity() { return selectedDeity; }
    public void setSelectedDeity(DeityModal selectedDeity) { this.selectedDeity = selectedDeity; }

}
