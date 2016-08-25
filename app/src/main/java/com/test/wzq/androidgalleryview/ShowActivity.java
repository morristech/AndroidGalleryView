package com.test.wzq.androidgalleryview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.test.wzq.androidgalleryview.view.LoopView;

import java.util.Arrays;

/**
 * Created by wzq on 16/8/10.
 */
public class ShowActivity extends AppCompatActivity {

    private String[] mImgs = new String[]{
            "http://cdn.firstlinkapp.com/real/img/2016_8/1470024069956_83198.jpg",
            "http://cdn.firstlinkapp.com/real/img/2016_8/1470707433746_37883.jpg",
            "http://cdn.firstlinkapp.com/real/img/2016_8/1470024069956_83198.jpg",
            "http://cdn.firstlinkapp.com/real/img/2016_8/1470707433746_37883.jpg",
            "http://cdn.firstlinkapp.com/real/img/2016_8/1470024069956_83198.jpg"
    };

    boolean flag = true;

    LoopView gv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.main_radio);
        ((RadioButton)rg.getChildAt(0)).setChecked(true);
        gv = (LoopView) findViewById(R.id.main_image);
        gv.setImgList(Arrays.asList(mImgs));
        gv.setAction(new LoopView.Action() {
            @Override
            public void loadPicture(String url, ImageView view) {
                Glide.with(ShowActivity.this).load(url).into(view);
            }

            @Override
            public void onChange(int position) {
                RadioButton rb = (RadioButton) rg.getChildAt(position%mImgs.length);
                rb.setChecked(true);
            }
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = true;
                loop();
            }
        });

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                flag = false;
            }
        });

    }


    private void loop(){
        if (flag) {
            gv.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gv.startSmooth();
                    loop();
                }
            }, 2000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flag = false;
    }
}
