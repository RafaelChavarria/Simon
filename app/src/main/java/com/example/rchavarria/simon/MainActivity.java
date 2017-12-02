package com.example.rchavarria.simon;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_sig;
    Button btn_g;
    Button btn_r;
    Button btn_y;
    Button btn_b;
    Tablero game = new Tablero();
    ArrayList<String> secuencia = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer mpg = MediaPlayer.create(this, R.raw.snd_g);
        final MediaPlayer mpr = MediaPlayer.create(this, R.raw.snd_r);
        final MediaPlayer mpy = MediaPlayer.create(this, R.raw.snd_y);
        final MediaPlayer mpb = MediaPlayer.create(this, R.raw.snd_b);

        btn_sig = findViewById(R.id.btn_sig);
        btn_g = findViewById(R.id.btn_g);
        btn_r = findViewById(R.id.btn_r);
        btn_y = findViewById(R.id.btn_y);
        btn_b = findViewById(R.id.btn_b);




        btn_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btn_g.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.verdePressed));
                mpg.start();
                 mpg.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        btn_g.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.verdeNormal));
                    }
                });
            }
        });

        btn_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_r.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.rojoPressed));
                mpr.start();
                mpr.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        btn_r.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.rojoNormal));
                    }
                });
            }
        });

        btn_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_y.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.yellowPressed));
                mpy.start();
                mpy.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        btn_y.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.yellowNormal));
                    }
                });
            }
        });

        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_b.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.bluePressed));
                mpb.start();
                mpb.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        btn_b.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.blueNormal));
                    }
                });
            }
        });





        btn_sig.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                boolean x = false;
                Log.d("Bool", x+"");
                btn_g.setClickable(false);
                btn_r.setClickable(false);
                btn_y.setClickable(false);
                btn_b.setClickable(false);
              //  Log.d("clic1", btn_g.isClickable()+"");
                secuencia = game.jugar();

                x = play(secuencia);
                Log.d("Bool2", x+"");

                  /* if (game.turno==true){
                       btn_g.setEnabled(true);
                       btn_r.setEnabled(true);
                       btn_y.setEnabled(true);
                       btn_b.setEnabled(true);
                }*/

            }
        });
    }//FIN ON CREATE




    public Boolean play(ArrayList<String> secuencia){

        for (int i=0; i<secuencia.size();i++){

            Handler handler = new Handler();
            final ArrayList<String> s = secuencia;
            final int j = i;
            handler.postDelayed(new Runnable() {
                public void run() {
                    switch (s.get(j)){
                        case "G":
                            btn_g.performClick();
                            break;
                        case "R":
                            btn_r.performClick();
                            break;
                        case "Y":
                            btn_y.performClick();
                            break;
                        case "B":
                            btn_b.performClick();
                            break;
                    }//FIN SWITCH
                }
            }, 800*(i));

        }//FIN CICLO FOR

        btn_g.setClickable(true);
        btn_r.setClickable(true);
        btn_y.setClickable(true);
        btn_b.setClickable(true);
       // Log.d("clic2", btn_g.isClickable()+"");
    return true;
    }//FIN PLAY()






}



