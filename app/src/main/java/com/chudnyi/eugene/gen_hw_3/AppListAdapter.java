package com.chudnyi.eugene.gen_hw_3;


import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AppListAdapter extends BaseAdapter {

    private List<ResolveInfo> packages;
    private LayoutInflater inflater;
    private PackageManager pm;
    private int visible_delete=View.INVISIBLE;

    public AppListAdapter(Context context, PackageManager pm, List<ResolveInfo> packages) {
        this.packages = packages;
        this.pm = pm;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return packages.size();
    }

    @Override
    public ResolveInfo getItem(int p1) {

        return packages.get(p1);
    }

    @Override
    public long getItemId(int p1) {

        return p1;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        View view = null;
        ViewHolder viewHolder;

        if (v == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.icons, parent, false);
            viewHolder.tvAppLabel = (TextView) view.findViewById(R.id.app_item_text);
            viewHolder.tvAppLabelNumber = (TextView) view .findViewById(R.id.app_item_number);
            viewHolder.ivAppIcon = (ImageView) view.findViewById(R.id.app_item_image);
            viewHolder.ivAppIconDelete = (ImageView) view.findViewById(R.id.app_item_image_delete);
            view.setTag(viewHolder);
        } else {
            view = v;
            viewHolder = (ViewHolder) view.getTag();
        }

        ResolveInfo app = packages.get(position);

        viewHolder.tvAppLabel.setText(app.loadLabel(pm).toString());
        viewHolder.tvAppLabelNumber.setText("" + (position + 1));
        viewHolder.ivAppIcon.setImageDrawable(app.loadIcon(pm));
        viewHolder.ivAppIconDelete.setVisibility(visible_delete);
        //viewHolder.tvAppLabel.setTextColor(Color.rgb(200, 0, 0));

        return view;
    }

    class ViewHolder {
         TextView tvAppLabel;
         TextView tvAppLabelNumber;
         ImageView ivAppIcon;
         ImageView ivAppIconDelete;
    }

    public void SetVisibleDelete() {
        if (visible_delete==View.INVISIBLE)
            visible_delete=View.VISIBLE;
        else
            visible_delete=View.INVISIBLE;

    }

}