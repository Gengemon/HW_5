package com.chudnyi.eugene.gen_hw_3.table_item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.chudnyi.eugene.gen_hw_3.R;

/**
 * Created by Ольга on 09.02.2016.
 */
public class TableItem extends FrameLayout {

    private ImageView imageView;

    public TableItem(Context context) {
        super(context);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.icons, this);
        if (isInEditMode())
            return;
        imageView = (ImageView) findViewById(R.id.image);
    }

}
