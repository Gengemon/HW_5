package com.chudnyi.eugene.gen_hw_3;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.chudnyi.eugene.gen_hw_3.Const;

public class EmptyActivity extends AppCompatActivity {
    private GridView gvMain;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        //table with test data
        String[] data = new String[ 1200 ];
        for (int i = 0; i < data.length; i++) {
            data[i] = "" + (i + 1);
        }
        adapter = new ArrayAdapter<String>(this, R.layout.grid, R.id.textGreed, data);
        gvMain = (GridView) findViewById(R.id.grid);
        gvMain.setAdapter(adapter);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            gvMain.setNumColumns(Const.GRID_VERTICAL_COLUMNS);
        else
            gvMain.setNumColumns(Const.GRID_HORIZONTAL_COLUMNS);
    }


}
