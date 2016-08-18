package com.test.wzq.androidgalleryview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzq on 16/8/18.
 */
public class TestView extends ViewGroup {

    private List<TextView> views = new ArrayList<>();

    public TestView(Context context) {
        super(context, null);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        MarginLayoutParams params = new MarginLayoutParams(100,100);
        for (int i = 0; i < 5; i++) {
            TextView t = new TextView(this.getContext());
//            t.setLayoutParams(params);
            t.setBackgroundResource(android.R.color.holo_blue_bright);
            t.setGravity(Gravity.CENTER);
            t.setText("t"+i);
            views.add(t);
            addView(t);
        }
    }

    @Override
    protected void onLayout(boolean c, int l, int t, int r, int b) {
        int total = getChildCount();
        System.out.println(total);
        int cl = 0, ct = 0, cr, cb;
        for (int i = 0; i < total; i++) {
            View v = getChildAt(i);
            cr = v.getWidth();
            cb = v.getHeight();
            System.out.println(cr+"--" + cb);
            v.layout(cl+i*100, ct+i*100, cr+i*100, cb+i*100);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
