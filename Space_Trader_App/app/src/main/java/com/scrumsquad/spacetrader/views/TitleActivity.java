package com.scrumsquad.spacetrader.views;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scrumsquad.spacetrader.R;
import com.scrumsquad.spacetrader.model.Game;
import com.scrumsquad.spacetrader.views.ConfigurationActivity;

public class TitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        ImageButton startBtn = findViewById(R.id.title_start_player_config);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ConfigurationActivity.class);
                startActivity(startIntent);
            }
        });

        ImageButton loadBtn = findViewById(R.id.title_load_player);
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("User");

                DatabaseReference defaultRef = database.getReference();
                myRef.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Game myGame = dataSnapshot.getValue(Game.class);
                        Game.makeGame(myGame);
                        System.out.println(Game.getGame());
                        System.out.println("game created");
                        Intent startIntent = new Intent(getApplicationContext(), GameActivity.class);
                        startActivity(startIntent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
    }
}