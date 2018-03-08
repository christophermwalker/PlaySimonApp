package edu.apsu.csci.playsimonapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameSelectionActivity extends Activity implements View.OnClickListener {

    public static final String SCORE_KEY = "score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameselection);

        int ids[]={R.id.game1_button, R.id.game2_button, R.id.game3_button};
        for (int id : ids) {
            Button b =(Button) findViewById(id);
            b.setOnClickListener(this);
        }
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.game1_button) {
            Intent intent = new Intent(
                    getApplicationContext(),
                    Game1Activity.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.game2_button) {
            Intent intent = new Intent(
                    getApplicationContext(),
                    Game2Activity.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.game3_button) {
            Intent intent = new Intent(
                    getApplicationContext(),
                    Game3Activity.class);
            startActivity(intent);
        }
    }
}