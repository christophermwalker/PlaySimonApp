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
import android.widget.Toast;

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
    int counter = 0;
    int buttonCounter = 0;
    int oldButton = 0;
    int[] gameMemory;
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
    public void onClick(final View view) {

        TextView tv = (TextView) findViewById(R.id.ready_textview);

        Integer[] images = {
                R.drawable.dark_green_button, R.drawable.dark_red_button,
                R.drawable.dark_yellow_button, R.drawable.dark_blue_button,
                R.drawable.bright_green_button, R.drawable.bright_red_button,
                R.drawable.bright_yellow_button, R.drawable.bright_blue_button
        };

        final ImageButton ib = (ImageButton) findViewById(R.id.green_button);
        final ImageButton ib2 = (ImageButton) findViewById(R.id.red_button);
        final ImageButton ib3 = (ImageButton) findViewById(R.id.yellow_button);
        final ImageButton ib4 = (ImageButton) findViewById(R.id.blue_button);
        Button b = (Button) findViewById(R.id.begin_button);

        Integer[] imageButtons = {
                R.id.green_button, R.id.red_button,
                R.id.yellow_button, R.id.blue_button
        };

        Random r = new Random();

        if (view.getId() == R.id.begin_button) {
            view.setVisibility(View.GONE);
            tv.setVisibility(View.GONE);
        }

       // gameMemory[0] = 0;

        //gameMemory[0] != R.id.green_button || gameMemory[0] != R.id.red_button ||
        //gameMemory[0] != R.id.yellow_button || gameMemory[0] != R.id.blue_button

        //if (gameMemory[0] == 0) {
        if (buttonCounter == 0) {
            view.setId(imageButtons[r.nextInt(imageButtons.length) % imageButtons.length]);
            if (view.getId() == R.id.green_button) {
                //  gameMemory[buttonCounter] = view.getId();
                ib.setImageResource(R.drawable.bright_green_button);
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {

                        }
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                ib.setImageResource(R.drawable.dark_green_button);
                            }
                        });
                    }
                })
                ).start();
            } else if (view.getId() == R.id.red_button) {
                //   gameMemory[buttonCounter] = view.getId();
                ib2.setImageResource(R.drawable.bright_red_button);
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {

                        }
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                ib2.setImageResource(R.drawable.dark_red_button);
                            }
                        });
                    }
                })
                ).start();
            } else if (view.getId() == R.id.yellow_button) {
                //   gameMemory[buttonCounter] = view.getId();
                ib3.setImageResource(R.drawable.bright_yellow_button);
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {

                        }
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                ib3.setImageResource(R.drawable.dark_yellow_button);
                            }
                        });
                    }
                })
                ).start();
            } else if (view.getId() == R.id.blue_button) {
                //   gameMemory[buttonCounter] = view.getId();
                ib4.setImageResource(R.drawable.bright_blue_button);
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {

                        }
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                ib4.setImageResource(R.drawable.dark_blue_button);
                            }
                        });
                    }
                })
                ).start();
            }
            //Log.i("OLD", "The Button: " + oldButton);
            //Log.i("New", "New Button: " + oldButton);
            oldButton = view.getId();
            Log.i("BVALUE", "Button: " + oldButton);
            buttonCounter++;
        }

            if (ib.isPressed()) {
                view.setId(R.id.green_button);
                buttonCounter++;

                ib.setImageResource(R.drawable.bright_green_button);
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {

                        }
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                ib.setImageResource(R.drawable.dark_green_button);
                            }
                        });
                    }
                })
                ).start();
            } else if (ib2.isPressed()) {
                view.setId(R.id.red_button);
                buttonCounter++;

                ib2.setImageResource(R.drawable.bright_red_button);
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {

                        }
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                ib2.setImageResource(R.drawable.dark_red_button);
                            }
                        });
                    }
                })
                ).start();
            } else if (ib3.isPressed()) {
                view.setId(R.id.yellow_button);
                buttonCounter++;

                ib3.setImageResource(R.drawable.bright_yellow_button);
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {

                        }
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                ib3.setImageResource(R.drawable.dark_yellow_button);
                            }
                        });
                    }
                })
                ).start();
            } else if (ib4.isPressed()) {
                view.setId(R.id.blue_button);
                buttonCounter++;

                ib4.setImageResource(R.drawable.bright_blue_button);
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {

                        }
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                ib4.setImageResource(R.drawable.dark_blue_button);
                            }
                        });
                    }
                })
                ).start();
            }

            if (buttonCounter == 2) {
               if (view.getId() != oldButton) {
                    String finalScore = "0";
                    Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
                    intent.putExtra(SCORE_KEY, finalScore);
                    startActivity(intent);
                } else if (view.getId() == oldButton) {
                    String finalScore = "100";
                    Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
                    intent.putExtra(SCORE_KEY, finalScore);
                    startActivity(intent);
                }
            }
       /* } else {
            for (int counting = 0; counting <= gameMemory.length; counting++) {
                if (gameMemory[counting] == R.id.green_button) {
                    ib.setImageResource(R.drawable.bright_green_button);
                    (new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {

                            }
                            view.post(new Runnable() {
                                @Override
                                public void run() {
                                    ib.setImageResource(R.drawable.dark_green_button);
                                }
                            });
                        }
                    })
                    ).start();
                } else if (gameMemory[0] == R.id.red_button) {
                    ib2.setImageResource(R.drawable.bright_red_button);
                    (new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {

                            }
                            view.post(new Runnable() {
                                @Override
                                public void run() {
                                    ib2.setImageResource(R.drawable.dark_red_button);
                                }
                            });
                        }
                    })
                    ).start();
                } else if (gameMemory[0] == R.id.yellow_button) {
                    ib3.setImageResource(R.drawable.bright_yellow_button);
                    (new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {

                            }
                            view.post(new Runnable() {
                                @Override
                                public void run() {
                                    ib3.setImageResource(R.drawable.dark_yellow_button);
                                }
                            });
                        }
                    })
                    ).start();
                } else if (gameMemory[0] == R.id.blue_button) {
                    ib4.setImageResource(R.drawable.bright_blue_button);
                    (new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {

                            }
                            view.post(new Runnable() {
                                @Override
                                public void run() {
                                    ib4.setImageResource(R.drawable.dark_blue_button);
                                }
                            });
                        }
                    })
                    ).start();
                }
                oldButton = view.getId();
                Log.i("BVALUE", "Button: " + oldButton);
                buttonCounter++;

                for (int count = 0; count < 1; count++) {
                    if (ib.isPressed()) {
                        view.setId(R.id.green_button);
                        buttonCounter++;
                    } else if (ib2.isPressed()) {
                        view.setId(R.id.red_button);
                        buttonCounter++;
                    } else if (ib3.isPressed()) {
                        view.setId(R.id.yellow_button);
                        buttonCounter++;
                    } else if (ib4.isPressed()) {
                        view.setId(R.id.blue_button);
                        buttonCounter++;
                    }
                }

                if (view.getId() != oldButton) {
                    String etStaticString = "0";
                    Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
                    intent.putExtra(SCORE_KEY, etStaticString);
                    startActivity(intent);
                } else if (view.getId() == oldButton) {
                    Log.i("SUCCESS", "IT WORKED!");
                } else {
                    Log.i("KINDA", "MAYBE IT WORKED!?!?");
                }
            }
            if (view.getId() == R.id.green_button) {
                gameMemory[buttonCounter] += view.getId();
                ib.setImageResource(R.drawable.bright_green_button);
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {

                        }
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                ib.setImageResource(R.drawable.dark_green_button);
                            }
                        });
                    }
                })
                ).start();
            } else if (view.getId() == R.id.red_button) {
                gameMemory[buttonCounter] += view.getId();
                ib2.setImageResource(R.drawable.bright_red_button);
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {

                        }
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                ib2.setImageResource(R.drawable.dark_red_button);
                            }
                        });
                    }
                })
                ).start();
            } else if (view.getId() == R.id.yellow_button) {
                gameMemory[buttonCounter] += view.getId();
                ib3.setImageResource(R.drawable.bright_yellow_button);
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {

                        }
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                ib3.setImageResource(R.drawable.dark_yellow_button);
                            }
                        });
                    }
                })
                ).start();
            } else if (view.getId() == R.id.blue_button) {
                gameMemory[buttonCounter] += view.getId();
                ib4.setImageResource(R.drawable.bright_blue_button);
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {

                        }
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                ib4.setImageResource(R.drawable.dark_blue_button);
                            }
                        });
                    }
                })
                ).start();
            }
            oldButton = view.getId();
            Log.i("BVALUE", "Button: " + oldButton);
            buttonCounter++;

            for (int count = 0; count < 1; count++) {
                if (ib.isPressed()) {
                    view.setId(R.id.green_button);
                    buttonCounter++;
                } else if (ib2.isPressed()) {
                    view.setId(R.id.red_button);
                    buttonCounter++;
                } else if (ib3.isPressed()) {
                    view.setId(R.id.yellow_button);
                    buttonCounter++;
                } else if (ib4.isPressed()) {
                    view.setId(R.id.blue_button);
                    buttonCounter++;
                }
            }

            if (view.getId() != oldButton) {
                String etStaticString = "0";
                Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
                intent.putExtra(SCORE_KEY, etStaticString);
                startActivity(intent);
            } else if (view.getId() == oldButton) {
                Log.i("SUCCESS", "IT WORKED!");
            } else {
                Log.i("KINDA", "MAYBE IT WORKED!?!?");
            }
        } */

   /* ib.setEnabled(false);
    ib2.setEnabled(false);
    ib3.setEnabled(false);
    ib4.setEnabled(false);
    ib.setEnabled(true);
    ib2.setEnabled(true);
    ib3.setEnabled(true);
    ib4.setEnabled(true); */
    }

    public static boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }

    private int getDrawableId(ImageButton ib) {
        return ((Integer) ib.getTag() - 2130903039);
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
