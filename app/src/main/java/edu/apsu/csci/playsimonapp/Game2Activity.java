package edu.apsu.csci.playsimonapp;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.function.Predicate.isEqual;


public class Game2Activity extends Activity implements View.OnClickListener {
    public static final String SCORE_KEY = "score";

    private int delay = 300;
    private SoundPool soundPool;
    private Set<Integer> soundsLoaded;
    private int rId = 0;
    private int yId = 0;
    private int gId = 0;
    private int bId = 0;
    private int lId = 0;
    private int wId = 0;
    private int mode = 1;
    private int position = 0;
    private int maxPosition = 0;
    private int maxSeq = 10;
    private String[] compSeq = new String[maxSeq];
    //private String[] playerSeq=new String[maxSeq];
    private RadioGroup radioGroup;

    private ArrayList<Integer> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = new ArrayList<>();

        setContentView(R.layout.activity_game2);
        soundsLoaded = new HashSet<Integer>();
        disableButtons();
        int ids[]={R.id.red_button, R.id.yellow_button, R.id.green_button, R.id.blue_button};

        for (int id : ids) {
            ImageButton ib = (ImageButton) findViewById(id);
            ib.setOnClickListener(this);

            Button b = (Button) findViewById(R.id.begin_button);
            b.setOnClickListener(this);

            Button b2 = (Button) findViewById(R.id.submit_button);
            b2.setOnClickListener(this);

            radioGroup=(RadioGroup) findViewById(R.id.game_buttons);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.game1_button) {
                        mode = 1;
                        Toast.makeText(getApplicationContext(), "choice: One Player",
                                Toast.LENGTH_SHORT).show();
                    } else if (checkedId == R.id.game2_button) {
                        mode = 2;
                        Toast.makeText(getApplicationContext(), "choice: Two Player",
                                Toast.LENGTH_SHORT).show();
                    } else if (checkedId == R.id.game3_button) {
                        mode = 3;
                        Toast.makeText(getApplicationContext(), "choice: Music",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

   // @RequiresApi(api=Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();

        AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
        attrBuilder.setUsage(AudioAttributes.USAGE_GAME);

        SoundPool.Builder spBuilder = new SoundPool.Builder();
        spBuilder.setAudioAttributes(attrBuilder.build());
        spBuilder.setMaxStreams(4);
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
        rId = soundPool.load(this, R.raw.button1, 1);
        yId = soundPool.load(this, R.raw.button2, 1);
        gId = soundPool.load(this, R.raw.button3, 1);
        bId = soundPool.load(this, R.raw.button4, 1);
        lId = soundPool.load(this, R.raw.buzzer, 1);
        wId = soundPool.load(this, R.raw.victory, 1);
    }


    public void onSelect(String x) throws InterruptedException {
        final ImageButton ibR = (ImageButton) findViewById(R.id.red_button);
        final ImageButton ibY = (ImageButton) findViewById(R.id.yellow_button);
        final ImageButton ibG = (ImageButton) findViewById(R.id.green_button);
        final ImageButton ibB = (ImageButton) findViewById(R.id.blue_button);
        if (x.equals("R")) {
            try {
                Log.i("LOADS", "+1");
                Thread.sleep(10);
            } catch (InterruptedException e) {

            }
            playSound(rId);
            ibR.setImageResource(R.drawable.bright_red_button);
            final Handler handler = new Handler();
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            ibR.setImageResource(R.drawable.dark_red_button);
                            try {
                                Log.i("LOADS", "+1");
                                Thread.sleep(10);
                            } catch (InterruptedException e) {

                            }
                        }
                    });
                }
            }, delay);
        } else if (x.equals("Y")) {
            try {
                Log.i("LOADS", "+1");
                Thread.sleep(10);
            } catch (InterruptedException e) {

            }
            playSound(yId);
            ibY.setImageResource(R.drawable.bright_yellow_button);
            final Handler handler = new Handler();
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            ibY.setImageResource(R.drawable.dark_yellow_button);
                            try {
                                Log.i("LOADS", "+1");
                                Thread.sleep(100);
                            } catch (InterruptedException e) {

                            }
                        }
                    });
                }
            }, delay);
        } else if (x.equals("G")) {
            try {
                Log.i("LOADS", "+1");
                Thread.sleep(10);
            } catch (InterruptedException e) {

            }
            playSound(gId);
            ibG.setImageResource(R.drawable.bright_green_button);
            final Handler handler = new Handler();
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            ibG.setImageResource(R.drawable.dark_green_button);
                            try {
                                Log.i("LOADS", "+1");
                                Thread.sleep(100);
                            } catch (InterruptedException e) {

                            }
                        }
                    });
                }
            }, delay);
        } else if (x.equals("B")) {
            try {
                Log.i("LOADS", "+1");
                Thread.sleep(10);
            } catch (InterruptedException e) {

            }
            playSound(bId);
            ibB.setImageResource(R.drawable.bright_blue_button);
            final Handler handler = new Handler();
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            ibB.setImageResource(R.drawable.dark_blue_button);
                            try {
                                Log.i("LOADS", "+1");
                                Thread.sleep(100);
                            } catch (InterruptedException e) {

                            }
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

    private void loadArray() {
        int i = 0;
        do {
            compSeq[i]=randomColor();
            i++;
        } while (i < maxSeq);
    }

    private String randomColor() {
        final int random = new Random().nextInt(4);
        if (random == 0) {
            try {
                Log.i("LOADS", "+1");
                Thread.sleep(10);
            } catch (InterruptedException e) {

            }
            return "Y";
        } else if (random == 1) {
            try {
                Log.i("LOADS", "+1");
                Thread.sleep(10);
            } catch (InterruptedException e) {

            }
            return "G";
        } else if (random == 2) {
            try {
                Log.i("LOADS", "+1");
                Thread.sleep(10);
            } catch (InterruptedException e) {

            }
            return "B";
        } else {
            try {
                Log.i("LOADS", "+1");
                Thread.sleep(10);
            } catch (InterruptedException e) {

            }
            return "R";
        }
    }

    private void compPlay(int x, int y, final View view) throws InterruptedException {
        final ImageButton ibR = (ImageButton) findViewById(R.id.red_button);
        final ImageButton ibY = (ImageButton) findViewById(R.id.yellow_button);
        final ImageButton ibG = (ImageButton) findViewById(R.id.green_button);
        final ImageButton ibB = (ImageButton) findViewById(R.id.blue_button);
        for (int i = x; i <= y; i++) {
            if (compSeq[i].equals("R")) {
                ibR.setImageResource(R.drawable.dark_red_button);
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {

                        }
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                ibR.setImageResource(R.drawable.bright_red_button);
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {

                        }
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                ibR.setImageResource(R.drawable.dark_red_button);
                            }
                        });
                        /*try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {

                        }
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                ibR.setImageResource(R.drawable.dark_red_button);
                            }
                        }); */
                    }
                })
                ).start();
            } else if (compSeq[i].equals("Y")) {
                onSelect("Y");
            } else if (compSeq[i].equals("R")) {
                onSelect("G");
            } else if (compSeq[i].equals("B")) {
                onSelect("B");
            }
            //Thread.currentThread().sleep(10);
            Log.e("a", "b");
        }
    }

    private void disableStart() {
        Button b = (Button) findViewById(R.id.begin_button);
        b.setEnabled(false);
    }

    private void enableStart() {
        Button b = (Button) findViewById(R.id.begin_button);
        b.setEnabled(true);
    }

    private void disableButtons() {
        ImageButton ib = (ImageButton) findViewById(R.id.red_button);
        ib.setClickable(false);
        ib.setEnabled(false);
        ib = (ImageButton) findViewById(R.id.yellow_button);
        ib.setClickable(false);
        ib.setEnabled(false);
        ib = (ImageButton) findViewById(R.id.green_button);
        ib.setClickable(false);
        ib.setEnabled(false);
        ib = (ImageButton) findViewById(R.id.blue_button);
        ib.setClickable(false);
        ib.setEnabled(false);
    }

    private void enableButtons() {
        ImageButton ib = (ImageButton) findViewById(R.id.red_button);
        ib.setClickable(true);
        ib.setEnabled(true);
        ib = (ImageButton) findViewById(R.id.yellow_button);
        ib.setClickable(true);
        ib.setEnabled(true);
        ib = (ImageButton) findViewById(R.id.green_button);
        ib.setClickable(true);
        ib.setEnabled(true);
        ib = (ImageButton) findViewById(R.id.blue_button);
        ib.setClickable(true);
        ib.setEnabled(true);
    }

    @Override
    public void onClick(final View view) {
        TextView et = (TextView) findViewById(R.id.ready_textview);
        final ImageButton ibR = (ImageButton) findViewById(R.id.red_button);
        final ImageButton ibY = (ImageButton) findViewById(R.id.yellow_button);
        final ImageButton ibG = (ImageButton) findViewById(R.id.green_button);
        final ImageButton ibB = (ImageButton) findViewById(R.id.blue_button);
        if (mode == 1) {
            if (view.getId() == R.id.begin_button) {
                loadArray();
                position = 0;
                maxPosition = 0;
                try {
                    compPlay(position, maxPosition, view);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                et = (TextView) findViewById(R.id.ready_textview);
                et.setText("Player One");
                enableButtons();

            } else if (view.getId() == R.id.red_button) {
                et = (TextView) findViewById(R.id.ready_textview);
                et.setText("Position: " + position + " \n Score: " + maxPosition + "\n Computer: " + compSeq[position]);
                if (compSeq[position].equals("R") && position < maxPosition) {
                    position++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition + "\n Computer: " + compSeq[position]);
                } else if (compSeq[position].equals("R") && position >= maxPosition) {
                    ibR.setImageResource(R.drawable.dark_red_button);
                    (new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {

                            }
                            view.post(new Runnable() {
                                @Override
                                public void run() {
                                    ibR.setImageResource(R.drawable.bright_red_button);
                                }
                            });
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {

                            }
                            view.post(new Runnable() {
                                @Override
                                public void run() {
                                    ibR.setImageResource(R.drawable.dark_red_button);
                                }
                            });
                            /*try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {

                            }
                            view.post(new Runnable() {
                                @Override
                                public void run() {
                                    ibR.setImageResource(R.drawable.dark_red_button);
                                }
                            }); */
                        }
                    })
                    ).start();
                    position = 0;
                    maxPosition++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition + "\n Computer: " + compSeq[position]);
                    disableButtons();
                    try {
                        compPlay(position, maxPosition, view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    enableButtons();
                } else if (compSeq[position].equals("R") && position == maxPosition && maxPosition == compSeq.length) {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(wId);
                    et.setText("You Win!!!");
                    disableButtons();
                    enableStart();
                } else {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(lId);
                    et.setText("You Lose - Try Again!");
                    disableButtons();
                    enableStart();
                }
            } else if (view.getId() == R.id.yellow_button) {
                et = (TextView) findViewById(R.id.ready_textview);
                if (compSeq[position].equals("Y") && position < maxPosition) {
                    try {
                        onSelect("Y");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    position++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition + "\n Computer: " + compSeq[position]);
                } else if (compSeq[position].equals("Y") && position >= maxPosition) {
                    position = 0;
                    maxPosition++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition + "\n Computer: " + compSeq[position]);
                    disableButtons();
                    try {
                        compPlay(position, maxPosition, view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    enableButtons();
                } else if (compSeq[position].equals("Y") && position == maxPosition && maxPosition == compSeq.length) {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(wId);
                    et.setText("You Win!!!");
                    disableButtons();
                    enableStart();
                } else {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(lId);
                    et.setText("You Lose - Try Again!");
                    disableButtons();
                    enableStart();
                }
            } else if (view.getId() == R.id.green_button) {
                et = (TextView) findViewById(R.id.ready_textview);
                if (compSeq[position].equals("G") && position < maxPosition) {
                    try {
                        onSelect("G");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    position++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition + "\n Computer: " + compSeq[position]);
                } else if (compSeq[position].equals("G") && position >= maxPosition) {
                    position = 0;
                    maxPosition++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition + "\n Computer: " + compSeq[position]);
                    disableButtons();
                    try {
                        compPlay(position, maxPosition, view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    enableButtons();
                } else if (compSeq[position].equals("G") && position == maxPosition && maxPosition == compSeq.length) {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(wId);
                    et.setText("You Win!!!");
                    disableButtons();
                    enableStart();
                } else {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(lId);
                    et.setText("You Lose - Try Again!");
                    disableButtons();
                    enableStart();
                }
            } else if (view.getId() == R.id.blue_button) {
                et = (TextView) findViewById(R.id.ready_textview);
                if (compSeq[position].equals("B") && position < maxPosition) {
                    try {
                        onSelect("B");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    position++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition + "\n Computer: " + compSeq[position]);
                } else if (compSeq[position].equals("B") && position >= maxPosition) {
                    position = 0;
                    maxPosition++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition + "\n Computer: " + compSeq[position]);
                    disableButtons();
                    try {
                        compPlay(position, maxPosition, view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    enableButtons();
                } else if (compSeq[position].equals("B") && position == maxPosition && maxPosition == compSeq.length) {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(wId);
                    et.setText("You Win!!!");
                    disableButtons();
                    enableStart();
                } else {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(lId);
                    et.setText("You Lose - Try Again!");
                    disableButtons();
                    enableStart();
                }
            } else if (view.getId() == R.id.submit_button) {
                String finalScore = Integer.toString(maxPosition);
                Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
                intent.putExtra(SCORE_KEY, finalScore);
                startActivity(intent);
            }
        } else if (mode == 2) {
        } else if (mode == 3) {
            if (view.getId() == R.id.begin_button) {
                et=(TextView) findViewById(R.id.ready_textview);
                et.setText("Play Music");
                enableButtons();
            } else if (view.getId() == R.id.red_button) {
                try {
                    onSelect("R");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (view.getId() == R.id.yellow_button) {
                try {
                    onSelect("Y");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (view.getId() == R.id.green_button) {
                try {
                    onSelect("G");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (view.getId() == R.id.blue_button) {
                try {
                    onSelect("B");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (view.getId() == R.id.submit_button) {
                String finalScore = Integer.toString(maxPosition);
                Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
                intent.putExtra(SCORE_KEY, finalScore);
                startActivity(intent);
            }
        }
    }
}