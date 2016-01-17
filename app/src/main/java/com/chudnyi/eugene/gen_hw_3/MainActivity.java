package com.chudnyi.eugene.gen_hw_3;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView gvMain;
    private AppListAdapter appListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //тулбар
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final PackageManager pm = this.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> packages = pm.queryIntentActivities(intent, PackageManager.GET_META_DATA);

        appListAdapter = new AppListAdapter(this, pm, packages);// передача данных адаптеру
        gvMain = (GridView) findViewById(R.id.grid01);
        gvMain.setAdapter(appListAdapter);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            gvMain.setNumColumns(3);
        else
            gvMain.setNumColumns(5);
        gvMain.setVerticalSpacing(2);
        gvMain.setHorizontalSpacing(2);
    }

    public void button_call_click(View v){
        Toast.makeText(getApplicationContext(),"Звонок",Toast.LENGTH_SHORT).show();

        Intent intentCall = new Intent(Intent.ACTION_DIAL);
        startActivity(intentCall);
    }
    public void button_program_click(View v){
        Toast.makeText(getApplicationContext(),"Приложения",Toast.LENGTH_SHORT).show();
    }

    public void button_go_click(View v){
        Toast.makeText(getApplicationContext(),"Запуск",Toast.LENGTH_SHORT).show();

        Intent intentGo = new Intent(Intent.ACTION_SENDTO);
        intentGo.setData(Uri.parse("smsto:" + ""));
        startActivity(intentGo);
    }

    public void button_image_delete(View v){
        Toast.makeText(getApplicationContext(),"Удаление "+v.getId(),Toast.LENGTH_SHORT).show();
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

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_settings1){
            //Toast.makeText(this, "Открывается пустое активити", Toast.LENGTH_LONG).show();// на девайсе всплывающее уведомление внизу
            Intent intent01 = new Intent(MainActivity.this, null_activity.class);
            startActivity(intent01);
            //setContentView(R.layout.layout_null_01);
        }

        if (id == R.id.action_settings2){
            //Toast.makeText(this, "Открывается пустое активити", Toast.LENGTH_LONG).show();// на девайсе всплывающее уведомление внизу
            String settings =  Settings.ACTION_SETTINGS;
            Intent intentSettings = new Intent(settings);
            //Intent intCall = new Intent(Intent.ACTION_CALL);
            startActivity(intentSettings);
        }

        if (id == R.id.action_delete){
//            View view=null;
//            view=view.findViewById(R.id.app_item_image);
//            view.setVisibility(View.VISIBLE);
            //appListAdapter.getItem(1)
            appListAdapter.SetVisibleDelete();
            gvMain.setAdapter(appListAdapter);
            Log.i("Тип лога 1","Включение режима отображения.");
            Toast.makeText(this, "Режим удаления включен", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }


}
