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

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.g);

        btn_sig = findViewById(R.id.btn_sig);
        btn_g = findViewById(R.id.btn_g);
        btn_r = findViewById(R.id.btn_r);
        btn_y = findViewById(R.id.btn_y);
        btn_b = findViewById(R.id.btn_b);

        btn_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
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

    Handler h = new Handler();
    public void play(ArrayList<String> secuencia){
        for (int i= 0; i < secuencia.size(); i++){
            switch (secuencia.get(i)){
                case "G":
                    btn_g.performClick();
                    break;
                case "R":
                    btn_r.setText("X");
                    break;
                case "Y":
                    btn_y.setText("X");
                    break;
                case "B":
                    btn_b.setText("X");
                    break;

            }
            try{
                Thread.sleep(1000);
            }catch  (Exception e){

            }

        }
    }



    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
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



