package edu.apsu.csci.playsimonapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends Activity implements View.OnClickListener {

    public static final String SCORE_KEY = "score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        int ids[]={R.id.play_button, R.id.highscore_button, R.id.about_button};
        for (int id : ids) {
            Button b =(Button) findViewById(id);
            b.setOnClickListener(this);
        }
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.play_button) {
            String etStaticString = "0";
            Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
            intent.putExtra(SCORE_KEY, etStaticString);
            startActivity(intent);
        }

        if (view.getId() == R.id.highscore_button) {
            String etStaticString = "85";
            Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
            intent.putExtra(SCORE_KEY, etStaticString);
            startActivity(intent);
        }

        if (view.getId() == R.id.about_button) {
            String etStaticString = "102";
            Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
            intent.putExtra(SCORE_KEY, etStaticString);
            startActivity(intent);
        }
    }
}
