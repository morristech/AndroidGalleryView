package com.test.wzq.androidgalleryview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by wzq on 16/8/10.
 */
public class Activity1 extends AppCompatActivity {

    private String[] mImgs = new String[]{"http://cdn.firstlinkapp.com/real/img/2016_8/1470024069956_83198.jpg",
            "http://cdn.firstlinkapp.com/real/img/2016_6/1467272485840_74123.jpg",
            "http://cdn.firstlinkapp.com/real/img/2016_8/1470707433746_37883.jpg",
            "http://cdn.firstlinkapp.com/real/img/2016_8/1470707433746_37883.jpg",
            "http://cdn.firstlinkapp.com/real/img/2016_8/1470707433746_37883.jpg"};

    boolean flag = true;

    LoopView gv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        gv = (LoopView) findViewById(R.id.main_image);
        gv.setImgList(Arrays.asList(mImgs));

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gv.startSmooth();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flag = false;
    }
}
