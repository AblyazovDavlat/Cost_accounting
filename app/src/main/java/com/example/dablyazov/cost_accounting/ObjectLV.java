package com.example.dablyazov.cost_accounting;

/**
 * Created by ablya on 27.11.2017.
 */
public class ObjectLV {

    public String Information;
    public boolean Rashod;
    public int Summ;


    public ObjectLV(String element, boolean active, int money ) {
        Information = element;
        Rashod = active;
        Summ = money;
    }

    public String getInformation() {
        return Information;
    }
    public int getSumm() {
        return Summ;
    }
    public boolean getRashod() {
        return Rashod;
    }

}
