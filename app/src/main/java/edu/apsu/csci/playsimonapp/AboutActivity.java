package edu.apsu.csci.playsimonapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends Activity {

    public static final String KEEPSCORE_KEY = "keepscore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Intent intent = getIntent();
        String score = "0";
        final String keepscore;
        if (intent.getStringExtra(SimonActivity.SCORE_KEY) != null) {
            score = intent.getStringExtra(SimonActivity.SCORE_KEY);
        }
        keepscore = score;

        Button b =(Button) findViewById(R.id.start_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                intent.putExtra(KEEPSCORE_KEY, keepscore);
                startActivity(intent);
            }
        });
    }
}
