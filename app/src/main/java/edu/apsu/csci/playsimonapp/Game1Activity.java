package edu.apsu.csci.playsimonapp;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class Game1Activity extends Activity implements View.OnClickListener {

    public static final String SCORE_KEY = "score";
    private Timer timer;
    /*private SoundPool soundPool;
    private Set<Integer> soundsLoaded; */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        Integer[] images = {
                R.drawable.dark_green_button, R.drawable.dark_red_button,
                R.drawable.dark_yellow_button, R.drawable.dark_blue_button,
                R.drawable.bright_green_button, R.drawable.bright_red_button,
                R.drawable.bright_yellow_button, R.drawable.bright_blue_button
        };

        Integer[] imageButtons = {
                R.id.green_button, R.id.red_button,
                R.id.yellow_button, R.id.blue_button
        };

        Random r = new Random();

        ImageButton ib = (ImageButton) findViewById(R.id.green_button);
        ImageButton ib2 = (ImageButton) findViewById(R.id.red_button);
        ImageButton ib3 = (ImageButton) findViewById(R.id.yellow_button);
        ImageButton ib4 = (ImageButton) findViewById(R.id.blue_button);
        Button b = (Button) findViewById(R.id.begin_button);
        TextView tv = (TextView) findViewById(R.id.ready_textview);

        ib.setOnClickListener(this);
        ib2.setOnClickListener(this);
        ib3.setOnClickListener(this);
        ib4.setOnClickListener(this);
        b.setOnClickListener(this);

        // soundsLoaded = new HashSet<Integer>();

       /* int ids[]={R.id.green_button, R.id.red_button, R.id.yellow_button, R.id.blue_button};
        for (int id : ids) {
            ImageButton ib =(ImageButton) findViewById(id);
            ib.setOnClickListener(this);
        } */
    }
    @Override
    public void onClick(View view) {

        Integer[] images = {
                R.drawable.dark_green_button, R.drawable.dark_red_button,
                R.drawable.dark_yellow_button, R.drawable.dark_blue_button,
                R.drawable.bright_green_button, R.drawable.bright_red_button,
                R.drawable.bright_yellow_button, R.drawable.bright_blue_button
        };

        ImageButton ib = (ImageButton) findViewById(R.id.green_button);
        ImageButton ib2 = (ImageButton) findViewById(R.id.red_button);
        ImageButton ib3 = (ImageButton) findViewById(R.id.yellow_button);
        ImageButton ib4 = (ImageButton) findViewById(R.id.blue_button);
        Button b = (Button) findViewById(R.id.begin_button);

        Integer[] imageButtons = {
                R.id.green_button, R.id.red_button,
                R.id.yellow_button, R.id.blue_button
        };

        Random r = new Random();

        TextView tv = (TextView) findViewById(R.id.ready_textview);

        if (view.getId() == R.id.begin_button) {
            view.setVisibility(View.GONE);
            tv.setVisibility(View.GONE);

            /*ib.setTag(images[r.nextInt(images.length)]);
            ib.setImageResource(images[getDrawableId(ib) % images.length]);
            ib2.setTag(images[r.nextInt(images.length)]);
            ib2.setImageResource(images[getDrawableId(ib2) % images.length]);
            ib3.setTag(images[r.nextInt(images.length)]);
            ib3.setImageResource(images[getDrawableId(ib3) % images.length]);
            ib4.setTag(images[r.nextInt(images.length)]);
            ib4.setImageResource(images[getDrawableId(ib4) % images.length]); */
        }

       /* ib.setTag(images[r.nextInt(images.length)]);
        ib.setImageResource(images[getDrawableId(ib) % images.length]); */
        view.setId(R.id.green_button);

        Log.i("ID", "New ID: " + view.getId());
        if (view.getId() == R.id.green_button) {
            if (timer == null) {
                timer = new Timer();
                timer.schedule(new ClockTask(), 1000);
            }
        } else if (view.getId() == R.id.red_button) {
            ib2.setImageResource(images[5]);
            try {
                //  ib2.setTag(images[1]);
                //  ib2.setImageResource(images[5]);
                Thread.sleep(1000);
                //  ib2.setImageResource(images[1]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (view.getId() == R.id.yellow_button) {
            try {
                //  ib3.setTag(images[2]);
                //  ib3.setImageResource(images[6]);
                Thread.sleep(1000);
                //  ib3.setImageResource(images[2]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (view.getId() == R.id.blue_button) {
            try {
                //  ib4.setTag(images[3]);
                //  ib4.setImageResource(images[7]);
                Thread.sleep(1000);
                //  ib4.setImageResource(images[3]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

       /* ib.setEnabled(false);
        ib2.setEnabled(false);
        ib3.setEnabled(false);
        ib4.setEnabled(false);
        ib.setEnabled(true);
        ib2.setEnabled(true);
        ib3.setEnabled(true);
        ib4.setEnabled(true); */
        int count = 0;

    }

    public static boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }

    private int getDrawableId(ImageButton ib) {
        return ((Integer) ib.getTag() - 2130903039);
    }

    class ClockTask extends TimerTask {
        @Override
        public void run() {

            final Integer[] images = {
                    R.drawable.dark_green_button, R.drawable.dark_red_button,
                    R.drawable.dark_yellow_button, R.drawable.dark_blue_button,
                    R.drawable.bright_green_button, R.drawable.bright_red_button,
                    R.drawable.bright_yellow_button, R.drawable.bright_blue_button
            };

            final ImageButton ib = (ImageButton) findViewById(R.id.green_button);
            ImageButton ib2 = (ImageButton) findViewById(R.id.red_button);
            ImageButton ib3 = (ImageButton) findViewById(R.id.yellow_button);
            ImageButton ib4 = (ImageButton) findViewById(R.id.blue_button);
            Button b = (Button) findViewById(R.id.begin_button);

            final Integer[] imageButtons = {
                    R.id.green_button, R.id.red_button,
                    R.id.yellow_button, R.id.blue_button
            };

            final Random r = new Random();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ib.setTag(images[r.nextInt(images.length)]);
                    ib.setImageResource(images[4]);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ib.setTag(images[r.nextInt(images.length)]);
                    ib.setImageResource(images[0]);
                }
            });
        }
    }

   /* Sounds for API 21
   @Override
    protected void onResume() {
        super.onResume();

        AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
        attrBuilder.setUsage(AudioAttributes.USAGE_GAME);

        SoundPool.Builder spBuilder = new SoundPool.Builder();
        spBuilder.setAudioAttributes(attrBuilder.build());
        spBuilder.setMaxStreams(2);
        soundPool = spBuilder.build();

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

        final int greenButtonId = soundPool.load(this, R.raw.button1, 1);
        findViewById(R.id.green_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(greenButtonId);
            }
        });

        final int redButtonId = soundPool.load(this, R.raw.button2, 1);
        findViewById(R.id.red_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(redButtonId);
            }
        });

        final int yellowButtonId = soundPool.load(this, R.raw.button3, 1);
        findViewById(R.id.yellow_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(yellowButtonId);
            }
        });

        final int blueButtonId = soundPool.load(this, R.raw.button4, 1);
        findViewById(R.id.blue_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(blueButtonId);
            }
        });
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
    } */
}
