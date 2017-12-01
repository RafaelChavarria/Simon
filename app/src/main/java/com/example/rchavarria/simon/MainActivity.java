package com.example.rchavarria.simon;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
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
                mpg.start();
            }
        });

        btn_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpr.start();
            }
        });

        btn_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpy.start();
            }
        });

        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpb.start();
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
    public void play(ArrayList<String> secuencia){

        MyTaskParams mtp = new MyTaskParams(secuencia, 0);
        lo.doInBackground(mtp);

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



            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            //TextView txt = (TextView) findViewById(R.id.output);
            //txt.setText("Executed"); // txt.setText(result);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }




}



