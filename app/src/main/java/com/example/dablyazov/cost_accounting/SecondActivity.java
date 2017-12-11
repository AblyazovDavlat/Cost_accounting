package com.example.dablyazov.cost_accounting;

/**
 * Created by ablya on 27.11.2017.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends Activity {

    ArrayList loads = new ArrayList();

    int countloads = 0;

    boolean flagactivity = false;

    private int _ActiveElement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        DataBase.Initialize(this);
        DrawList();

    }


    public void UpdateElements() {
        DrawList();

    }

    public void addClick(View view) {
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void savebase(View view) {
        Info.Get().setlist(loads);
    }

    public void loadbase(View view) {
        loads= Info.Get().getlist();
        UpdateElements();
    }


    private void DrawList() {
        ListView LV = (ListView) findViewById(R.id.lists);

        ArrayList loads = Info.Get().getlist();

        ArrayList loadsbase = DataBase.Get().GetElements();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, loads);
        // присваиваем адаптер списку
        LV.setAdapter(adapter);


        for (int i = 0; i < Info.Get().getcount(); i++) {
            ObjectLV add = (ObjectLV) loadsbase.get(i);
            String addinf = add.getInformation();
            int addsumm = add.getSumm();
            boolean addrashod = add.getRashod();
            if (addrashod) {
                loads.add((String) ("Доход: " + addinf + "  " + addsumm ));
            } else {
                loads.add((String) ("Расход:  " + addinf + "  " + addsumm ));
            }
            System.out.println("ii = " + i);
        }


        // выводить информацию при нажатии на этот элемент
        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                Toast.makeText(getApplicationContext(), ((TextView) itemClicked).getText(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        // выводить информацию при нажатии на этот элемент
        LV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                // TODO Auto-generated method stub
                //   Log.v("long clicked","pos: " + pos);
                _ActiveElement = pos;
                return true;
            }


        });
    }

    //   Удаление элемента

    public void deleteclick(View view) {
        DataBase.Get().DeleteElement(_ActiveElement);
        _ActiveElement = 0;
    }


}