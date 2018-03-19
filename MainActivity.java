package edu.apsu.cwalker61.playsimonapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int ids[]={R.id.play_button};
        for (int id : ids) {
            Button btn=(Button) findViewById(id);
            btn.setOnClickListener(this);
        }
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.play_button) {
            Intent intent=new Intent(
                    getApplicationContext(),
                    SimonActivity.class);
            startActivity(intent);
        }
    }
}
