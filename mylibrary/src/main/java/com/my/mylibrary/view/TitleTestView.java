package com.my.mylibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.my.mylibrary.R;

public class TitleTestView extends LinearLayout {
    public TitleTestView(Context context) {
        super(context);
        initView(context);

    }

    public TitleTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public TitleTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);

    }

    public TitleTestView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.title_bar_layout, this, true);

    }
}
