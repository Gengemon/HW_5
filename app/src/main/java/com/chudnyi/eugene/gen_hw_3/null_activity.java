package com.chudnyi.eugene.gen_hw_3;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.GridView;

/**
 * Created by Gengemon on 20.12.2015.
 */
public class null_activity extends AppCompatActivity {
    GridView gvMain;
    ArrayAdapter<String> adapter;
    String[] data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_null_01);

        //таблица во фрагменте
        data = new String[ 1200 ];
        for (int i = 0; i < data.length; i++) {
            data[i] = "" + (i + 1);
        }
        adapter = new ArrayAdapter<String>(this, R.layout.layout_grid_01, R.id.textGreed, data);
        gvMain = (GridView) findViewById(R.id.grid01);
        gvMain.setAdapter(adapter);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            gvMain.setNumColumns(3);
        else
            gvMain.setNumColumns(5);
        gvMain.setVerticalSpacing(2);
        gvMain.setHorizontalSpacing(2);
    }


//    @Override
//    public void onBackPressed() {
//        //setContentView(R.layout.activity_main);
//
//        finish();
//    }

}
