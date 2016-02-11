package com.chudnyi.eugene.gen_hw_3;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AppListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private int visible_delete=View.INVISIBLE;
    AppDataList appDataList;

    public AppListAdapter(Context context, AppDataList appDataList) {
        this.appDataList = appDataList;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return appDataList.getSize();
    }

    @Override
    public Object getItem(int position) {

        return appDataList.getItem(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
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

        viewHolder.tvAppLabel.setText(appDataList.getItem(position).getAppName());
        viewHolder.tvAppLabelNumber.setText("" + (position + 1));
        viewHolder.ivAppIcon.setImageDrawable(appDataList.getItem(position).getAppIcon());
        viewHolder.ivAppIconDelete.setVisibility(visible_delete);

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