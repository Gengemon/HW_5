package com.chudnyi.eugene.gen_hw_3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private GridView gvApplications;
    private AppListAdapter appListAdapter;

    private ImageButton btnLaunchPhone;
    private Button btnLaunchApp;
    private ImageButton btnLaunchSMS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppDataList appDataList = new AppDataList(getPackageManager());

        appListAdapter = new AppListAdapter(this, appDataList);

        gvApplications = (GridView) findViewById(R.id.grid);
        gvApplications.setAdapter(appListAdapter);

        btnLaunchPhone = (ImageButton) findViewById(R.id.button_phone);
        btnLaunchApp = (Button) findViewById(R.id.button_app);
        btnLaunchSMS = (ImageButton) findViewById(R.id.button_sms);
        btnLaunchPhone.setOnClickListener(this);
        btnLaunchApp.setOnClickListener(this);
        btnLaunchSMS.setOnClickListener(this);
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
            Intent intent01 = new Intent(MainActivity.this, EmptyActivity.class);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_phone:
                Intent intentCall = new Intent(Intent.ACTION_DIAL);
                startActivity(intentCall);
                break;
            case R.id.button_app:
                Toast.makeText(getApplicationContext(),R.string.applications,Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_sms:
                Intent intentSMS = new Intent(Intent.ACTION_SENDTO);
                intentSMS.setData(Uri.parse("smsto:"));
                startActivity(intentSMS);
                break;
        }
    }
}
