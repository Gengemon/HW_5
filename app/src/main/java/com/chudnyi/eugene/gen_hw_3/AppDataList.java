package com.chudnyi.eugene.gen_hw_3;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.List;


public class AppDataList {
    private ArrayList<AppData> lid = new ArrayList();

    public AppDataList(PackageManager pm){
        fillItemDataList(pm);
    }

    private void fillItemDataList(PackageManager pm){
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> packages = pm.queryIntentActivities(intent, PackageManager.GET_META_DATA);

        for (int i=0; i<packages.size();i++){
            AppData appData = new AppData(packages.get(i).loadLabel(pm).toString()
                                            ,packages.get(i).loadIcon(pm));
            lid.add(appData);
            int iii = 555;
        }
        return;
     }

    public AppData getItem(int position){
        return lid.get(position);
    }

    public int getSize(){
        return lid.size();
    }
}
