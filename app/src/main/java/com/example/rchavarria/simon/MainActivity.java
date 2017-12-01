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

        final MediaPlayer mpg = MediaPlayer.create(this, R.raw.g_note);
        final MediaPlayer mpr = MediaPlayer.create(this, R.raw.a_note);
        final MediaPlayer mpy = MediaPlayer.create(this, R.raw.gs_note);
        final MediaPlayer mpb = MediaPlayer.create(this, R.raw.e_note);

        btn_sig = findViewById(R.id.btn_sig);
        btn_g = findViewById(R.id.btn_g);
        btn_r = findViewById(R.id.btn_r);
        btn_y = findViewById(R.id.btn_y);
        btn_b = findViewById(R.id.btn_b);




        btn_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //btn_g.setBackgroundResource(R.drawable.gpressed);
                btn_g.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.verdePressed));

                mpb.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        btn_g.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.verdeNormal));
                    }
                });

                mpg.start();


                /*try {

                    Thread.sleep(800);

                } catch (InterruptedException e) {
                    Thread.interrupted();
                }*/

                //btn_g.setBackgroundResource(R.drawable.gpressed);
                /*try {

                    Thread.sleep(1000);
                    btn_g.setBackgroundResource(R.drawable.gnormal);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }*/


                //btn_g.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
                //btn_g.setBackgroundColor((Color.BLUE));


            }
        });

        btn_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpr.start();
                try {

                    Thread.sleep(800);

                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }
        });

        btn_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpy.start();
                try {

                    Thread.sleep(800);

                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }
        });

        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpb.start();
                try {

                    Thread.sleep(800);

                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }
        });





        btn_sig.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                secuencia = game.jugar();
                play(secuencia);

            }
        });

    }//FIN ON CREATE

    LongOperation lo = new LongOperation();
    final Handler handler = new Handler();
    public void play(ArrayList<String> secuencia){

        //MyTaskParams mtp = new MyTaskParams(secuencia, 0);
        //lo.doInBackground(mtp);
        for (int i=0; i<secuencia.size();i++){
            switch (secuencia.get(i)){
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
            }
           /* handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btn_g.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.verdeNormal));

                }
            }, 800);*/


            try {

                Thread.sleep(800);

            } catch (InterruptedException e) {
                Thread.interrupted();
            }

        }


    }


    private static class MyTaskParams {
        ArrayList<String> secuencia;
        int position;

        MyTaskParams(ArrayList<String> secuencia, int position) {
            this.secuencia = secuencia;
            this.position = position;
        }
    }


    private class LongOperation extends AsyncTask<MyTaskParams, Void, String> {

        @Override
        protected String doInBackground(MyTaskParams... params) {
            ArrayList<String> secuencia = params[0].secuencia;
            int position = params[0].position;

            for (int i=0; i<secuencia.size();i++){
                switch (secuencia.get(i)){
                    case "G":
                        btn_g.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.verdePressed));
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
                }

                try {

                    Thread.sleep(800);

                } catch (InterruptedException e) {
                    Thread.interrupted();
                }

            }


            onPostExecute("E");
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            //TextView txt = (TextView) findViewById(R.id.output);
            //txt.setText("Executed"); // txt.setText(result);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you

            /*try {

                Thread.sleep(2000);
                //btn_g.setBackgroundResource(R.drawable.gnormal);
            } catch (InterruptedException e) {
                Thread.interrupted();
            }*/
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }




}



