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
import com.chudnyi.eugene.gen_hw_3.Const;

public class MainActivity extends AppCompatActivity {


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
    }

    public void button_call_click(View v){
        Intent intentCall = new Intent(Intent.ACTION_DIAL);
        startActivity(intentCall);
    }
    public void button_program_click(View v){
    }

    public void button_go_click(View v){
        Intent intentSMS = new Intent(Intent.ACTION_SENDTO);
        intentSMS.setData(Uri.parse("smsto:"));
        startActivity(intentSMS);
    }

    public void button_image_delete(View v){
        v.setVisibility(View.INVISIBLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
            appListAdapter.SetVisibleDelete();
            gvApplications.setAdapter(appListAdapter);
        }

        return super.onOptionsItemSelected(item);
    }


}
