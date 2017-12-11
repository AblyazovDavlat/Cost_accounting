package com.example.dablyazov.cost_accounting;

/**
 * Created by ablya on 27.11.2017.
 */
import java.util.ArrayList;


public class Info {

    private static Info _Instance;

    private ArrayList loads = new ArrayList();
    private  String Information;
    private int Summ;
    private boolean Rashod;
    private int count;

    public Info() {
        Information=null;
        Summ = 0;
        Rashod = false;
        count = 0;
    }

    public void Infozero() {
        Information=null;
        Summ = 0;
        Rashod = false;
    }

    public static Info Get() {
        if (_Instance == null)
            _Instance = new Info();
        return _Instance;
    }

    public void setInformation(String inf) {
        Information=inf;
    }
    public void setSumm(int s) {
        Summ=s;
    }
    public void isRashod() {
        Rashod = true;
    }
    public void isDohod() {
        Rashod = false;
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
    public ArrayList getlist() { return loads; }
    public void setlist(ArrayList al) {loads = al;}
    public void setcount(int i) {count=i;}
    public int getcount() {return count;}
}
