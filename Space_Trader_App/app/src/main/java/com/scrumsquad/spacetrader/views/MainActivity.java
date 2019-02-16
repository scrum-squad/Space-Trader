package com.scrumsquad.spacetrader.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.scrumsquad.spacetrader.R;
import com.scrumsquad.spacetrader.views.TitleActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent startIntent = new Intent(getApplicationContext(), TitleActivity.class);
        startActivity(startIntent);
    }
}
