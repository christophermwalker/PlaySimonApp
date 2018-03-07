package edu.apsu.csci.playsimonapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HighscoreActivity extends Activity implements View.OnClickListener {

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
            Intent intent=new Intent(
                    getApplicationContext(),
                    GameActivity.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.highscore_button) {
            Intent intent=new Intent(
                    getApplicationContext(),
                    HighscoreActivity.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.about_button) {
            Intent intent=new Intent(
                    getApplicationContext(),
                    AboutActivity.class);
            startActivity(intent);
        }
    }
}
