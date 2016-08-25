package com.test.wzq.androidgalleryview;

import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.test.wzq.androidgalleryview.view.LoopView;


public class MainActivity extends AppCompatActivity {


    private int[] pictures = new int[]{
            R.mipmap.help_body_1,
            R.mipmap.help_body_2,
            R.mipmap.help_body_3,
            R.mipmap.help_body_4,
            R.mipmap.help_body_5
    };

    private boolean flag = true;

    private LoopView gv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.main_radio);
        ((RadioButton) rg.getChildAt(0)).setChecked(true);
        gv = (LoopView) findViewById(R.id.main_image);
        gv.setViewSize(pictures.length);
        gv.setAction(new LoopView.Action() {
            @Override
            public void nextPicture(int position, ImageView view) {
                //Glide.with(MainActivity.this).load(pictures[position]).fitCenter().into(view);
                view.setImageResource(pictures[position]);
            }

            @Override
            public void onChange(int position) {
                RadioButton rb = (RadioButton) rg.getChildAt(position);
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

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                flag = false;
            }
        });

    }


    private void loop() {
        new Thread() {
            @Override
            public void run() {
                while (flag) {
                    SystemClock.sleep(2000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            gv.startSmooth();
                        }
                    });

                }
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flag = false;
    }
}
