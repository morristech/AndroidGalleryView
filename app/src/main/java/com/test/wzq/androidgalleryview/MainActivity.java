package com.test.wzq.androidgalleryview;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Activity1.class));
            }
        });
    }

    private void test() {
        Intent intent = new Intent(this, Activity1.class);
        System.out.println(intent);
        startActivity(intent);
        Node n = new Node();
        n.name = 'a';
        n.nodes = new Node[2];
        init(n, n.name);
        init(n.nodes[0], 'c');
        init(n.nodes[1], 'e');

        System.out.println(n.name);
        go2(n);

        System.out.println("----------------------");
    }

    private void go2(Node n) {
        if (n.nodes != null) {
            for (Node item : n.nodes) {
                if (item == null) return;
                System.out.println(item.name);
            }
            for (Node item : n.nodes) {
                go2(item);
            }
        }
    }

    private void go(Node n) {
        System.out.println(n.name);
        for (Node item : n.nodes) {
            if (item != null) {
                go(item);
            } else {
                return;
            }
        }
    }


    private void init(Node n, char c) {
        for (int i = 0; i < n.nodes.length; i++) {
            n.nodes[i] = new Node();
            n.nodes[i].name = (char) (c + i + 1);
            n.nodes[i].nodes = new Node[2];
        }
    }


    private void handlerTest() {
    }
}
