package edu.apsu.cwalker61.playsimonapp;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
//import java.util.Timer;
//import java.util.TimerTask;


public class SimonActivity extends AppCompatActivity implements View.OnClickListener {
    public int delay = 300;
    private SoundPool soundPool;
    private Set<Integer> soundsLoaded;
    public int rId = 0;
    public int yId = 0;
    public int gId = 0;
    public int bId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon);
        soundsLoaded = new HashSet<Integer>();

        int ids[]={R.id.red_imgButton, R.id.yellow_imgButton, R.id.green_imgButton, R.id.blue_imgButton};

        for (int id : ids) {
            ImageButton ib =(ImageButton) findViewById(id);
            ib.setOnClickListener(this);

        }
    }
    @RequiresApi(api=Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();

        AudioAttributes.Builder attrBuilder=new AudioAttributes.Builder();
        attrBuilder.setUsage(AudioAttributes.USAGE_GAME);

        SoundPool.Builder spBuilder=new SoundPool.Builder();
        spBuilder.setAudioAttributes(attrBuilder.build());
        spBuilder.setMaxStreams(4);
        soundPool=spBuilder.build();

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if (status == 0) { // success
                    soundsLoaded.add(sampleId);
                    Log.i("SOUND", "Sound loaded " + sampleId);
                } else {
                    Log.i("SOUND", "Error cannot load sound status = " + status);
                }
            }
        });
        rId=soundPool.load(this, R.raw.chord1, 1);
        yId=soundPool.load(this, R.raw.chord2, 1);
        gId=soundPool.load(this, R.raw.chord3, 1);
        bId=soundPool.load(this, R.raw.chord4, 1);
    }
    public void onSelect(char x) {
        final ImageButton ibR=(ImageButton) findViewById(R.id.red_imgButton);
        final ImageButton ibY=(ImageButton) findViewById(R.id.yellow_imgButton);
        final ImageButton ibG=(ImageButton) findViewById(R.id.green_imgButton);
        final ImageButton ibB=(ImageButton) findViewById(R.id.blue_imgButton);
        if (x == 'R') {
            ibR.setImageResource(R.drawable.circle_red);
            playSound(rId);
            final Handler handler=new Handler();
            Timer t=new Timer();
            t.schedule(new TimerTask() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            ibR.setImageResource(R.drawable.circle_grey);
                        }
                    });
                }
            }, delay);
        } else if (x == 'Y') {
            ibY.setImageResource(R.drawable.circle_yellow);
            playSound(yId);
            final Handler handler=new Handler();
            Timer t=new Timer();
            t.schedule(new TimerTask() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            ibY.setImageResource(R.drawable.circle_grey);
                        }
                    });
                }
            }, delay);
        } else if (x == 'G') {
            ibG.setImageResource(R.drawable.circle_green);
            playSound(gId);
            final Handler handler=new Handler();
            Timer t=new Timer();
            t.schedule(new TimerTask() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            ibG.setImageResource(R.drawable.circle_grey);
                        }
                    });
                }
            }, delay);
        } else if (x == 'B') {
            ibB.setImageResource(R.drawable.circle_blue);
            playSound(bId);
            final Handler handler=new Handler();
            Timer t=new Timer();
            t.schedule(new TimerTask() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            ibB.setImageResource(R.drawable.circle_grey);
                        }
                    });
                }
            }, delay);
        }

    }
    @Override
    protected void onPause() {
        super.onPause();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;

            soundsLoaded.clear();
        }
    }

    private void playSound(int soundId) {
        if (soundsLoaded.contains(soundId)) {
            soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.red_imgButton) {
            onSelect('R');
        }
        else if (view.getId() == R.id.yellow_imgButton) {
            onSelect('Y');
        }
        else if (view.getId() == R.id.green_imgButton) {
            onSelect('G');
        }
        else if (view.getId() == R.id.blue_imgButton) {
            onSelect('B');
        }
    }


}