package edu.apsu.csci.playsimonapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends Activity implements View.OnClickListener {

    public static final String SCORE_KEY = "score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        int ids[]={R.id.play_button, R.id.highscore_button, R.id.about_button};
        for (int id : ids) {
            Button b =(Button) findViewById(id);
            b.setOnClickListener(this);
        }
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.play_button) {
            Intent intent = new Intent(
                    getApplicationContext(),
                    Game2Activity.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.highscore_button) {
            String finalScore = "0";
            Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
            intent.putExtra(SCORE_KEY, finalScore);
            startActivity(intent);
        }

        if (view.getId() == R.id.about_button) {
            Intent intent = new Intent(
                    getApplicationContext(),
                    AboutActivity.class);
            startActivity(intent);
        }
    }
}
