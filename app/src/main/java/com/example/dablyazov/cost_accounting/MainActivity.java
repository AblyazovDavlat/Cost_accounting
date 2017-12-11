package com.example.dablyazov.cost_accounting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    RadioButton radioBD;
    RadioButton radioBR;
    String dt = null;
    Date cal = null;

    EditText SummET;
    EditText InfET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        radioBD = (RadioButton) findViewById(R.id.radioButtonR);
        radioBR = (RadioButton) findViewById(R.id.radioButtonD);
        SummET = (EditText) findViewById(R.id.summa);
        InfET = (EditText) findViewById(R.id.information);

    }

    public void saveClick(View view) {
        Info.Get().setSumm(Integer.parseInt(SummET.getText().toString()));
        Info.Get().setInformation(InfET.getText().toString());
        if (radioBR.isChecked()) {
            Info.Get().isRashod();
        } else Info.Get().isDohod();
        DataBase.Get().AddElement(Info.Get().getInformation(), Info.Get().getRashod(), Info.Get().getSumm());
        Info.Get().Infozero();
        Info.Get().setcount(DataBase.Get().GetCount());
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);

    }

    public void cancelClick(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        Info.Get().Infozero();
        startActivity(intent);
    }



}