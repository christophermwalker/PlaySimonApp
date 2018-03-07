package edu.apsu.csci.playsimonapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class GameActivity extends Activity implements View.OnClickListener {

    public static final String SCORE_KEY = "score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ImageButton ib = (ImageButton) findViewById(R.id.green_button);
        ib.setOnClickListener(this);

        ImageButton ib2 = (ImageButton) findViewById(R.id.red_button);
        ib2.setOnClickListener(this);

        ImageButton ib3 = (ImageButton) findViewById(R.id.yellow_button);
        ib3.setOnClickListener(this);

        ImageButton ib4 = (ImageButton) findViewById(R.id.blue_button);
        ib4.setOnClickListener(this);

       /* int ids[]={R.id.green_button, R.id.red_button, R.id.yellow_button, R.id.blue_button};
        for (int id : ids) {
            ImageButton ib =(ImageButton) findViewById(id);
            ib.setOnClickListener(this);
        } */
    }
    @Override
    public void onClick(View view) {
        Integer[] images = {
                R.drawable.real_green_button, R.drawable.real_red_button,
                R.drawable.real_yellow_button, R.drawable.real_blue_button,
                R.drawable.real_bright_green_button, R.drawable.real_bright_red_button,
                R.drawable.real_bright_yellow_button, R.drawable.real_bright_blue_button
        };

        ImageButton ib = (ImageButton) findViewById(R.id.green_button);
        ImageButton ib2 = (ImageButton) findViewById(R.id.red_button);
        ImageButton ib3 = (ImageButton) findViewById(R.id.yellow_button);
        ImageButton ib4 = (ImageButton) findViewById(R.id.blue_button);

        int count = 0;

        if (view.getId() == R.id.green_button) {
            count = 1;
        }

        if (view.getId() == R.id.red_button) {
            count = 2;
        }

        if (view.getId() == R.id.yellow_button) {
            count = 3;
        }

        if (view.getId() == R.id.blue_button) {
            count = 4;
        }
    }
}
