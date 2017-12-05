package com.example.rchavarria.simon;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_sig;
    Button btn_g;
    Button btn_r;
    Button btn_y;
    Button btn_b;
    Button btn_reset;
    Tablero game = new Tablero();
    ArrayList<String> secuencia = new ArrayList<String>();
    ArrayList<String> secuenciaComp = new ArrayList<String>();
    TextView marcador;



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
        btn_reset = findViewById(R.id.btn_reset);
        marcador = findViewById(R.id.txt_pts);
        marcador.setText("Puntuación: " + game.marcador);


        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.marcador=0;
                marcador.setText("Puntuación: " + game.marcador);
                game.nuevo();
                btn_sig.performClick();
            }
        });

        btn_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_g.setClickable(false);
                btn_g.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.verdePressed));
                mpg.start();
                 mpg.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        btn_g.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.verdeNormal));
                        btn_g.setClickable(true);
                    }
                });
                 if (game.turno==true){
                     entrada("G");
                 }

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
                if (game.turno==true){
                    entrada("R");
                }
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
                if (game.turno==true){
                    entrada("Y");
                }
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
                if (game.turno==true){
                    entrada("B");
                }
            }
        });



        btn_sig.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                btn_g.setClickable(false);
                btn_r.setClickable(false);
                btn_y.setClickable(false);
                btn_b.setClickable(false);
                game.turno = false;
                secuencia = game.jugar();
                secuenciaComp = secuencia;

               LongOperation lo = new LongOperation();
               lo.execute(secuencia);
               veces= 0;
            }
        });
    }//FIN ON CREATE

    Handler handler3 = new Handler();
    int veces = 0;
    public Boolean entrada (String input){
        if(game.inputJugador(secuenciaComp.get(veces), input)){
            if (veces==(secuenciaComp.size()-1)){
                game.score();
                marcador.setText("Puntuación: " + game.marcador);
                handler3.postDelayed(new Runnable() {
                    public void run() {
                        btn_sig.performClick();
                    }

                }, 1500);
            }else{
                //SIGUE
                veces++;

            }


            Log.d("InputCompSize", secuenciaComp.size()+"");

        }else{
            //PIERDE
            new AlertDialog.Builder(this)
                    .setTitle("¡Perdiste!")
                    .setMessage("¿Quieres iniciar un nuevo juevo?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            game.nuevo();
                            marcador.setText("Puntuación: " + game.marcador);
                            btn_sig.performClick();
                            Log.d("Input Res: " , "Nuevo Juego");
                        }
                    }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    game.marcador=0;
                    marcador.setText("Puntuación: " + game.marcador);
                }
            }).show();
        }
        return true;
    }


    Handler handler = new Handler();
    Handler handler2 = new Handler();
    private class LongOperation extends AsyncTask<ArrayList, Void, Boolean> {

        @Override
        protected Boolean doInBackground(ArrayList... params) {
            btn_reset.setClickable(false);
            for (int i=0; i<secuencia.size();i++){


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
            handler2.postDelayed(new Runnable() {
                public void run() {
                    onPostExecute(true);
                }

            }, 800*(secuencia.size()));
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result){

                Log.d("Secuencia", "Terminado");
                btn_g.setClickable(true);
                btn_r.setClickable(true);
                btn_y.setClickable(true);
                btn_b.setClickable(true);
                btn_reset.setClickable(true);

                game.turno = true;
            }


        }

        @Override
        protected void onPreExecute() {
            //Log.d("Secuencia", "Por empezar");
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }



}



