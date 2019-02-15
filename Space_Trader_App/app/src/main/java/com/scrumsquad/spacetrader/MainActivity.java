package com.scrumsquad.spacetrader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        if (true) {
            System.out.println("Mike's code");
        }
        System.out.println("Yeet we goin");
    }
}
