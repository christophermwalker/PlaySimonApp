package edu.apsu.csci.playsimonapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends Activity {

    public static final String SCORE_KEY = "score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Intent intent = getIntent();
        String keepscore = "0";
        if (intent.getStringExtra(HighscoreActivity.KEEPSCORE_KEY) != null) {
            keepscore = intent.getStringExtra(HighscoreActivity.KEEPSCORE_KEY);
        }
        final String keepscore2;
        keepscore2 = keepscore;

        Button b1 = (Button) findViewById(R.id.play_button);
        Button b2 = (Button) findViewById(R.id.highscore_button);
        Button b3 = (Button) findViewById(R.id.about_button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        SimonActivity.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
                intent.putExtra(SCORE_KEY, keepscore2);
                startActivity(intent);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                intent.putExtra(SCORE_KEY, keepscore2);
                startActivity(intent);
            }
        });
    }
}
