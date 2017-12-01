package com.example.rchavarria.simon;

import android.content.ClipData;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by rchavarria on 29/11/2017.
 */



public class Tablero {

    private ArrayList<String> pads = new ArrayList<String>();

    private ArrayList<String> sequence = new ArrayList<String>();

    public boolean turno;

    private Random random;

    public Tablero() {
        nuevo();
    }

    private void nuevo() {
        this.turno = false;
        this.sequence.clear();
        pads.add("G");
        pads.add("R");
        pads.add("Y");
        pads.add("B");
    }

    public ArrayList<String> jugar (){
        random = new Random();
        int randomIndex = random.nextInt(pads.size());
        String nextPad = pads.get(randomIndex);
        sequence.add(nextPad);
        print();
        return sequence;
    }

    public void playSequence(){

    }

    public void print(){
        String temp ="";
        for(int i = 0 ; i < sequence.size(); i++){
            temp += sequence.get(i);
        }
        Log.d("Secuencia: ",temp);
    }



}
