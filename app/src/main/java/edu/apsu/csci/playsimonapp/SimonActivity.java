package edu.apsu.csci.playsimonapp;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SimonActivity extends Activity implements View.OnClickListener {
    public static final String SCORE_KEY = "score";

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
    private int maxSeq = 20;
    private String[] compSeq = new String[maxSeq];
    private RadioGroup radioGroup;

    private int secretCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon);
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
                        disableButtons();
                        enableStart();
                    } else if (checkedId == R.id.game2_button) {
                        mode = 2;
                        Toast.makeText(getApplicationContext(), "choice: Same Button",
                                Toast.LENGTH_SHORT).show();
                        disableButtons();
                        enableStart();
                    } else if (checkedId == R.id.game3_button) {
                        mode = 3;
                        Toast.makeText(getApplicationContext(), "choice: Only Sounds",
                                Toast.LENGTH_SHORT).show();
                        disableButtons();
                        enableStart();
                    } else if (checkedId == R.id.game4_button) {
                        mode = 4;
                        Toast.makeText(getApplicationContext(), "choice: Free Play",
                                Toast.LENGTH_SHORT).show();
                        disableButtons();
                        enableStart();
                    }
                }
            });
        }
    }

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
                if (status == 0) {
                    soundsLoaded.add(sampleId);
                } else {

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


    public void onSelect(String x, final View view) throws InterruptedException {
        final ImageButton ibR = (ImageButton) findViewById(R.id.red_button);
        final ImageButton ibY = (ImageButton) findViewById(R.id.yellow_button);
        final ImageButton ibG = (ImageButton) findViewById(R.id.green_button);
        final ImageButton ibB = (ImageButton) findViewById(R.id.blue_button);
        if (x.equals("R")) {
            try {
                Thread.sleep(100);
                playSound(rId);
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
            (new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            ibR.setImageResource(R.drawable.bright_red_button);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            ibR.setImageResource(R.drawable.dark_red_button);
                        }
                    });
                }
            })
            ).start();
        } else if (x.equals("Y")) {
            try {
                Thread.sleep(100);
                playSound(yId);
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
            (new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            ibY.setImageResource(R.drawable.bright_yellow_button);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            ibY.setImageResource(R.drawable.dark_yellow_button);
                        }
                    });
                }
            })
            ).start();
        } else if (x.equals("G")) {
            try {
                Thread.sleep(100);
                playSound(gId);
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
            (new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            ibG.setImageResource(R.drawable.bright_green_button);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            ibG.setImageResource(R.drawable.dark_green_button);
                        }
                    });
                }
            })
            ).start();
        } else if (x.equals("B")) {
            try {
                Thread.sleep(100);
                playSound(bId);
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
            (new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            ibB.setImageResource(R.drawable.bright_blue_button);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            ibB.setImageResource(R.drawable.dark_blue_button);
                        }
                    });
                }
            })
            ).start();
        }

    }

    public void onSelect2(String x, final View view) throws InterruptedException {
        final ImageButton ibR = (ImageButton) findViewById(R.id.red_button);
        final ImageButton ibY = (ImageButton) findViewById(R.id.yellow_button);
        final ImageButton ibG = (ImageButton) findViewById(R.id.green_button);
        final ImageButton ibB = (ImageButton) findViewById(R.id.blue_button);
        if (x.equals("R")) {
            try {
                Thread.sleep(100);
                playSound(yId);
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
            (new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            ibR.setImageResource(R.drawable.bright_yellow_button);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            ibR.setImageResource(R.drawable.dark_yellow_button);
                        }
                    });
                }
            })
            ).start();
        } else if (x.equals("Y")) {
            try {
                Thread.sleep(100);
                playSound(yId);
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
            (new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            ibY.setImageResource(R.drawable.bright_yellow_button);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            ibY.setImageResource(R.drawable.dark_yellow_button);
                        }
                    });
                }
            })
            ).start();
        } else if (x.equals("G")) {
            try {
                Thread.sleep(100);
                playSound(yId);
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
            (new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            ibG.setImageResource(R.drawable.bright_yellow_button);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            ibG.setImageResource(R.drawable.dark_yellow_button);
                        }
                    });
                }
            })
            ).start();
        } else if (x.equals("B")) {
            try {
                Thread.sleep(100);
                playSound(yId);
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
            (new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            ibB.setImageResource(R.drawable.bright_yellow_button);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            ibB.setImageResource(R.drawable.dark_yellow_button);
                        }
                    });
                }
            })
            ).start();
        }
    }

    public void onSelect3(String x, final View view) throws InterruptedException {
        if (x.equals("R")) {
            try {
                Thread.sleep(100);
                playSound(rId);
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
        } else if (x.equals("Y")) {
            try {
                Thread.sleep(100);
                playSound(yId);
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
        } else if (x.equals("G")) {
            try {
                Thread.sleep(100);
                playSound(gId);
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
        } else if (x.equals("B")) {
            try {
                Thread.sleep(100);
                playSound(bId);
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
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
            compSeq[i] = randomColor();
            i++;
        } while (i < maxSeq)
                ;
    }

    private String randomColor() {
        final int random = new Random().nextInt(4);
        if (random == 0) {
            return "Y";
        } else if (random == 1) {
            return "G";
        } else if (random == 2) {
            return "B";
        } else if (random == 3) {
            return "R";
        } else {
            return "B";
        }
    }

    private void compPlay(int x, int y, final View view) throws InterruptedException {
        for (int i = x; i <= y; i++) {
            if (compSeq[i].equals("R")) {
                onSelect("R", view);
            } else if (compSeq[i].equals("Y")) {
                onSelect("Y", view);
            } else if (compSeq[i].equals("G")) {
                onSelect("G", view);
            } else if (compSeq[i].equals("B")) {
                onSelect("B", view);
            }
            Thread.currentThread().sleep(100);
        }
    }

    private void compPlay2(int x, int y, final View view) throws InterruptedException {
        for (int i = x; i <= y; i++) {
            if (compSeq[i].equals("R")) {
                onSelect2("R", view);
            } else if (compSeq[i].equals("Y")) {
                onSelect2("Y", view);
            } else if (compSeq[i].equals("G")) {
                onSelect2("G", view);
            } else if (compSeq[i].equals("B")) {
                onSelect2("B", view);
            }
            Thread.currentThread().sleep(100);
        }
    }

    private void compPlay3(int x, int y, final View view) throws InterruptedException {
        for (int i = x; i <= y; i++) {
            if (compSeq[i].equals("R")) {
                onSelect3("R", view);
            } else if (compSeq[i].equals("Y")) {
                onSelect3("Y", view);
            } else if (compSeq[i].equals("G")) {
                onSelect3("G", view);
            } else if (compSeq[i].equals("B")) {
                onSelect3("B", view);
            }
            Thread.currentThread().sleep(100);
        }
    }

    private void enableStart() {
        Button btn=(Button) findViewById(R.id.begin_button);
        btn.setEnabled(true);
    }

    private void disableButtons() {
        ImageButton ib=(ImageButton) findViewById(R.id.red_button);
        ib.setClickable(false);
        ib.setEnabled(false);
        ib=(ImageButton) findViewById(R.id.yellow_button);
        ib.setClickable(false);
        ib.setEnabled(false);
        ib=(ImageButton) findViewById(R.id.green_button);
        ib.setClickable(false);
        ib.setEnabled(false);
        ib=(ImageButton) findViewById(R.id.blue_button);
        ib.setClickable(false);
        ib.setEnabled(false);
    }

    private void enableButtons() {
        ImageButton ib=(ImageButton) findViewById(R.id.red_button);
        ib.setClickable(true);
        ib.setEnabled(true);
        ib=(ImageButton) findViewById(R.id.yellow_button);
        ib.setClickable(true);
        ib.setEnabled(true);
        ib=(ImageButton) findViewById(R.id.green_button);
        ib.setClickable(true);
        ib.setEnabled(true);
        ib=(ImageButton) findViewById(R.id.blue_button);
        ib.setClickable(true);
        ib.setEnabled(true);
    }

    private void normalButtons() {
        ImageButton ib=(ImageButton) findViewById(R.id.red_button);
        ib.setImageResource(R.drawable.dark_red_button);
        ImageButton ib2=(ImageButton) findViewById(R.id.yellow_button);
        ib2.setImageResource(R.drawable.dark_yellow_button);
        ImageButton ib3=(ImageButton) findViewById(R.id.green_button);
        ib3.setImageResource(R.drawable.dark_green_button);
        ImageButton ib4=(ImageButton) findViewById(R.id.blue_button);
        ib4.setImageResource(R.drawable.dark_blue_button);
    }

    private void yellowButtons() {
        ImageButton ib=(ImageButton) findViewById(R.id.red_button);
        ib.setImageResource(R.drawable.dark_yellow_button);
        ImageButton ib2=(ImageButton) findViewById(R.id.yellow_button);
        ib2.setImageResource(R.drawable.dark_yellow_button);
        ImageButton ib3=(ImageButton) findViewById(R.id.green_button);
        ib3.setImageResource(R.drawable.dark_yellow_button);
        ImageButton ib4=(ImageButton) findViewById(R.id.blue_button);
        ib4.setImageResource(R.drawable.dark_yellow_button);
    }

    @Override
    public void onClick(View view) {
        TextView et = (TextView) findViewById(R.id.ready_textview);
        TextView et2 = (TextView) findViewById(R.id.secret_textview);
        if (mode == 1) {
            normalButtons();
            if (view.getId() == R.id.begin_button) {
                if (secretCounter == 4) {
                    Toast.makeText(this, "Here you go.", Toast.LENGTH_LONG).show();
                    et2.setVisibility(View.VISIBLE);
                }
                secretCounter++;
                loadArray();
                position=0;
                maxPosition=0;
                try {
                    compPlay(position, maxPosition, view);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                et = (TextView) findViewById(R.id.ready_textview);
                et.setText("Player One");
                enableButtons();

            } else if (view.getId() == R.id.red_button) {
                secretCounter = 0;
                et = (TextView) findViewById(R.id.ready_textview);
                if (compSeq[position].equals("R") && position < maxPosition) {
                    try {
                        onSelect("R", view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    position++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                } else if (maxPosition == (maxSeq - 1)) {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(wId);
                    et.setText("You Win! Submit your score!");
                    disableButtons();
                    enableStart();
                } else if (compSeq[position].equals("R") && position >= maxPosition) {
                    position=0;
                    maxPosition++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                    disableButtons();
                    try {
                        compPlay(position, maxPosition,view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    enableButtons();
                } else {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(lId);
                    et.setText("You Lose - Try Again!");
                    disableButtons();
                    enableStart();
                }
            } else if (view.getId() == R.id.yellow_button) {
                secretCounter = 0;
                et = (TextView) findViewById(R.id.ready_textview);
                if (compSeq[position].equals("Y") && position < maxPosition) {
                    try {
                        onSelect("Y", view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    position++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                } else if (maxPosition == (maxSeq - 1)) {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(wId);
                    et.setText("You Win! Submit your score!");
                    disableButtons();
                    enableStart();
                } else if (compSeq[position].equals("Y") && position >= maxPosition) {
                    position=0;
                    maxPosition++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                    disableButtons();
                    try {
                        compPlay(position, maxPosition,view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    enableButtons();
                } else {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(lId);
                    et.setText("You Lose - Try Again!");
                    disableButtons();
                    enableStart();
                }
            } else if (view.getId() == R.id.green_button) {
                secretCounter = 0;
                et = (TextView) findViewById(R.id.ready_textview);
                if (compSeq[position].equals("G") && position < maxPosition) {
                    try {
                        onSelect("G", view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    position++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                } else if (maxPosition == (maxSeq - 1)) {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(wId);
                    et.setText("You Win! Submit your score!");
                    disableButtons();
                    enableStart();
                } else if (compSeq[position].equals("G") && position >= maxPosition) {
                    position=0;
                    maxPosition++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                    disableButtons();
                    try {
                        compPlay(position, maxPosition, view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    enableButtons();
                } else {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(lId);
                    et.setText("You Lose - Try Again!");
                    disableButtons();
                    enableStart();
                }
            } else if (view.getId() == R.id.blue_button) {
                secretCounter = 0;
                et = (TextView) findViewById(R.id.ready_textview);
                if (compSeq[position].equals("B") && position < maxPosition) {
                    try {
                        onSelect("B", view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    position++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                } else if (maxPosition == (maxSeq - 1)) {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(wId);
                    et.setText("You Win! Submit your score!");
                    disableButtons();
                    enableStart();
                } else if (compSeq[position].equals("B") && position >= maxPosition) {
                    position=0;
                    maxPosition++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                    disableButtons();
                    try {
                        compPlay(position, maxPosition, view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    enableButtons();
                } else {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(lId);
                    et.setText("You Lose - Try Again!");
                    disableButtons();
                    enableStart();
                }
            } else if (view.getId() == R.id.submit_button) {
                secretCounter = 0;
                if (maxPosition != 0) {
                    try {
                        compPlay(position, maxPosition, view);
                        String finalScore = Integer.toString(maxPosition);
                        Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
                        intent.putExtra(SCORE_KEY, finalScore);
                        startActivity(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    maxPosition = 0;
                    String finalScore = Integer.toString(maxPosition);
                    Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
                    intent.putExtra(SCORE_KEY, finalScore);
                    startActivity(intent);
                }
            }
        } else if (mode == 2) {
            yellowButtons();
            if (view.getId() == R.id.begin_button) {
                if (secretCounter == 4) {
                    Toast.makeText(this, "Here you go.", Toast.LENGTH_LONG).show();
                    et2.setVisibility(View.VISIBLE);
                }
                secretCounter++;
                loadArray();
                position=0;
                maxPosition=0;
                try {
                    compPlay2(position, maxPosition, view);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                et = (TextView) findViewById(R.id.ready_textview);
                et.setText("Player One");
                enableButtons();

            } else if (view.getId() == R.id.red_button) {
                secretCounter = 0;
                et = (TextView) findViewById(R.id.ready_textview);
                if (compSeq[position].equals("R") && position < maxPosition) {
                    try {
                        onSelect2("R", view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    position++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                } else if (maxPosition == (maxSeq - 1)) {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(wId);
                    et.setText("You Win! Submit your score!");
                    disableButtons();
                    enableStart();
                } else if (compSeq[position].equals("R") && position >= maxPosition) {
                    position=0;
                    maxPosition++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                    disableButtons();
                    try {
                        compPlay2(position, maxPosition,view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    enableButtons();
                } else {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(lId);
                    et.setText("You Lose - Try Again!");
                    disableButtons();
                    enableStart();
                }
            } else if (view.getId() == R.id.yellow_button) {
                secretCounter = 0;
                et = (TextView) findViewById(R.id.ready_textview);
                if (compSeq[position].equals("Y") && position < maxPosition) {
                    try {
                        onSelect2("Y", view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    position++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                } else if (maxPosition == (maxSeq - 1)) {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(wId);
                    et.setText("You Win! Submit your score!");
                    disableButtons();
                    enableStart();
                } else if (compSeq[position].equals("Y") && position >= maxPosition) {
                    position=0;
                    maxPosition++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                    disableButtons();
                    try {
                        compPlay2(position, maxPosition,view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    enableButtons();
                } else {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(lId);
                    et.setText("You Lose - Try Again!");
                    disableButtons();
                    enableStart();
                }
            } else if (view.getId() == R.id.green_button) {
                secretCounter = 0;
                et = (TextView) findViewById(R.id.ready_textview);
                if (compSeq[position].equals("G") && position < maxPosition) {
                    try {
                        onSelect2("G", view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    position++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                } else if (maxPosition == (maxSeq - 1)) {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(wId);
                    et.setText("You Win! Submit your score!");
                    disableButtons();
                    enableStart();
                } else if (compSeq[position].equals("G") && position >= maxPosition) {
                    position=0;
                    maxPosition++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                    disableButtons();
                    try {
                        compPlay2(position, maxPosition, view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    enableButtons();
                } else {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(lId);
                    et.setText("You Lose - Try Again!");
                    disableButtons();
                    enableStart();
                }
            } else if (view.getId() == R.id.blue_button) {
                secretCounter = 0;
                et = (TextView) findViewById(R.id.ready_textview);
                if (compSeq[position].equals("B") && position < maxPosition) {
                    try {
                        onSelect2("B", view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    position++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                } else if (maxPosition == (maxSeq - 1)) {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(wId);
                    et.setText("You Win! Submit your score!");
                    disableButtons();
                    enableStart();
                } else if (compSeq[position].equals("B") && position >= maxPosition) {
                    position=0;
                    maxPosition++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                    disableButtons();
                    try {
                        compPlay2(position, maxPosition, view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    enableButtons();
                } else {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(lId);
                    et.setText("You Lose - Try Again!");
                    disableButtons();
                    enableStart();
                }
            } else if (view.getId() == R.id.submit_button) {
                secretCounter = 0;
                if (maxPosition != 0) {
                    try {
                        compPlay2(position, maxPosition, view);
                        String finalScore = Integer.toString(maxPosition);
                        Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
                        intent.putExtra(SCORE_KEY, finalScore);
                        startActivity(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    maxPosition = 0;
                    String finalScore = Integer.toString(maxPosition);
                    Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
                    intent.putExtra(SCORE_KEY, finalScore);
                    startActivity(intent);
                }
            }
        } else if (mode == 3) {
            normalButtons();
            if (view.getId() == R.id.begin_button) {
                if (secretCounter == 4) {
                    Toast.makeText(this, "Here you go.", Toast.LENGTH_LONG).show();
                    et2.setVisibility(View.VISIBLE);
                }
                secretCounter++;
                loadArray();
                position=0;
                maxPosition=0;
                try {
                    compPlay3(position, maxPosition, view);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                et = (TextView) findViewById(R.id.ready_textview);
                et.setText("Player One");
                enableButtons();

            } else if (view.getId() == R.id.red_button) {
                secretCounter = 0;
                et = (TextView) findViewById(R.id.ready_textview);
                if (compSeq[position].equals("R") && position < maxPosition) {
                    try {
                        onSelect3("R", view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    position++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                } else if (maxPosition == (maxSeq - 1)) {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(wId);
                    et.setText("You Win! Submit your score!");
                    disableButtons();
                    enableStart();
                } else if (compSeq[position].equals("R") && position >= maxPosition) {
                    position=0;
                    maxPosition++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                    disableButtons();
                    try {
                        compPlay3(position, maxPosition,view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    enableButtons();
                } else {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(lId);
                    et.setText("You Lose - Try Again!");
                    disableButtons();
                    enableStart();
                }
            } else if (view.getId() == R.id.yellow_button) {
                secretCounter = 0;
                et = (TextView) findViewById(R.id.ready_textview);
                if (compSeq[position].equals("Y") && position < maxPosition) {
                    try {
                        onSelect3("Y", view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    position++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                } else if (maxPosition == (maxSeq - 1)) {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(wId);
                    et.setText("You Win! Submit your score!");
                    disableButtons();
                    enableStart();
                } else if (compSeq[position].equals("Y") && position >= maxPosition) {
                    position=0;
                    maxPosition++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                    disableButtons();
                    try {
                        compPlay3(position, maxPosition,view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    enableButtons();
                } else {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(lId);
                    et.setText("You Lose - Try Again!");
                    disableButtons();
                    enableStart();
                }
            } else if (view.getId() == R.id.green_button) {
                secretCounter = 0;
                et = (TextView) findViewById(R.id.ready_textview);
                if (compSeq[position].equals("G") && position < maxPosition) {
                    try {
                        onSelect3("G", view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    position++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                } else if (maxPosition == (maxSeq - 1)) {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(wId);
                    et.setText("You Win! Submit your score!");
                    disableButtons();
                    enableStart();
                } else if (compSeq[position].equals("G") && position >= maxPosition) {
                    position=0;
                    maxPosition++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                    disableButtons();
                    try {
                        compPlay3(position, maxPosition, view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    enableButtons();
                } else {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(lId);
                    et.setText("You Lose - Try Again!");
                    disableButtons();
                    enableStart();
                }
            } else if (view.getId() == R.id.blue_button) {
                secretCounter = 0;
                et = (TextView) findViewById(R.id.ready_textview);
                if (compSeq[position].equals("B") && position < maxPosition) {
                    try {
                        onSelect3("B", view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    position++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                } else if (maxPosition == (maxSeq - 1)) {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(wId);
                    et.setText("You Win! Submit your score!");
                    disableButtons();
                    enableStart();
                } else if (compSeq[position].equals("B") && position >= maxPosition) {
                    position=0;
                    maxPosition++;
                    et.setText("Position: " + position + " \n Score: " + maxPosition);
                    et2.setText("Computer: " + compSeq[position]);
                    disableButtons();
                    try {
                        compPlay3(position, maxPosition, view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    enableButtons();
                } else {
                    et = (TextView) findViewById(R.id.ready_textview);
                    playSound(lId);
                    et.setText("You Lose - Try Again!");
                    disableButtons();
                    enableStart();
                }
            } else if (view.getId() == R.id.submit_button) {
                secretCounter = 0;
                if (maxPosition != 0) {
                    try {
                        compPlay3(position, maxPosition, view);
                        String finalScore = Integer.toString(maxPosition);
                        Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
                        intent.putExtra(SCORE_KEY, finalScore);
                        startActivity(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    maxPosition = 0;
                    String finalScore = Integer.toString(maxPosition);
                    Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
                    intent.putExtra(SCORE_KEY, finalScore);
                    startActivity(intent);
                }
            }
        } else if (mode == 4) {
            normalButtons();
            if (view.getId() == R.id.begin_button) {
                et=(TextView) findViewById(R.id.ready_textview);
                et.setText("Free Play");
                enableButtons();
            } else if (view.getId() == R.id.red_button) {
                try {
                    onSelect("R", view);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (view.getId() == R.id.yellow_button) {
                try {
                    onSelect("Y", view);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (view.getId() == R.id.green_button) {
                try {
                    onSelect("G", view);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (view.getId() == R.id.blue_button) {
                try {
                    onSelect("B", view);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (view.getId() == R.id.submit_button) {
                try {
                    compPlay(position, maxPosition, view);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                maxPosition = 0;
                String finalScore = Integer.toString(maxPosition);
                Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
                intent.putExtra(SCORE_KEY, finalScore);
                startActivity(intent);
            }
        }
    }
}
