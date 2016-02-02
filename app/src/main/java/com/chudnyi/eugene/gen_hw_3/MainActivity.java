package com.chudnyi.eugene.gen_hw_3;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int GRID_VERTICAL_COLUMNS = 3;
    private static final int GRID_HORIZONTAL_COLUMNS = 5;

    private GridView gvApplications;
    private AppListAdapter appListAdapter;

    private void fillListAdapter()
    {
        final PackageManager pm = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> packages = pm.queryIntentActivities(intent, PackageManager.GET_META_DATA);
        appListAdapter = new AppListAdapter(this, pm, packages);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fillListAdapter();
        gvApplications = (GridView) findViewById(R.id.grid);
        gvApplications.setAdapter(appListAdapter);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            gvApplications.setNumColumns(GRID_VERTICAL_COLUMNS);
        else
            gvApplications.setNumColumns(GRID_HORIZONTAL_COLUMNS);
    }

    public void button_call_click(View v){
        Toast.makeText(getApplicationContext(),"Open Phone",Toast.LENGTH_SHORT).show();

        Intent intentCall = new Intent(Intent.ACTION_DIAL);
        startActivity(intentCall);
    }
    public void button_program_click(View v){
        Toast.makeText(getApplicationContext(),"Applications",Toast.LENGTH_SHORT).show();
    }

    public void button_go_click(View v){
        Toast.makeText(getApplicationContext(),"Open SMS",Toast.LENGTH_SHORT).show();

        Intent intentSMS = new Intent(Intent.ACTION_SENDTO);
        intentSMS.setData(Uri.parse("smsto:"));
        startActivity(intentSMS);
    }

    public void button_image_delete(View v){
        Toast.makeText(getApplicationContext(),"Delete "+v.getId(),Toast.LENGTH_SHORT).show();
        v.setVisibility(View.INVISIBLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.start_empty_activity){
            Intent intent01 = new Intent(MainActivity.this, ActivityEmpty.class);
            startActivity(intent01);
        }

        if (id == R.id.open_settings){
            String settings =  Settings.ACTION_SETTINGS;
            Intent intentSettings = new Intent(settings);
            startActivity(intentSettings);
        }

        if (id == R.id.action_delete){
//            View view=null;
//            view=view.findViewById(R.id.app_item_image);
//            view.setVisibility(View.VISIBLE);
            //appListAdapter.getItem(1)
            appListAdapter.SetVisibleDelete();
            gvApplications.setAdapter(appListAdapter);
            Toast.makeText(this, "", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }


}
