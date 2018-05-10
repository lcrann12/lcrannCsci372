package com.example.lenny.cards;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;

import android.graphics.BitmapFactory;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    private ImageView playerCard;
    private ImageView cardImage;
    private ArrayList<String> cards;
    private Random rand;
    private int values =0;
    private int clickReset;
    private Button hit;
    //private GridLayout playerGrid;
    private TextView points;
    private Button stay;
    private TextView current;
    private Button retry;
    private int stayValues=0;
   // private SharedPreferences pref;
    //private HashMap<Integer, String> cardMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        //pref = getApplicationContext().getSharedPreferences("savePoints", MODE_PRIVATE);
        //SharedPreferences.Editor editor = pref.edit();
        cardImage = findViewById(R.id.cardImage);
        playerCard = findViewById(R.id.playerCard);
        cards = new ArrayList<String>();
        retry = findViewById(R.id.retry);
        retry.setEnabled(false);
        rand = new Random();
        points = findViewById(R.id.points);
        //playerGrid=findViewById(R.id.playerGrid);
        current = findViewById(R.id.current);
        hit = findViewById(R.id.hit);
        stay = findViewById(R.id.stay);
        //cardMap=new HashMap<>();
        AssetManager manager = getAssets();
        try{
            for(String filename : manager.list("")){
                cards.add(filename);
            }
        }catch (IOException e){
            Toast.makeText(this, "No Card", Toast.LENGTH_SHORT).show();
        }
        //Ignore this part, weird stuff that was added due to my laptop being weird with folders
        //May or may not have to comment this part out if uploaded onto github
        cards.remove("cover_collection_en.html");
        cards.remove("cover_collection_ko.html");
        cards.remove("cover_privacy_en.html");
        cards.remove("cover_privacy_ko.html");
        cards.remove("images");
        cards.remove("pskc_schema.xsd");
        cards.remove("webkit");


    }
    public void hitPressed(View v) throws IOException{
        //Unfortunately the player can hit an infinite amount of times
        //This is kind of non-optimally solved through the stayPressed method
        int r = rand.nextInt(cards.size());
        //hit.setText("");
        SharedPreferences savePoints = this.getSharedPreferences("saveP", 0);
        SharedPreferences.Editor editor = savePoints.edit();
        playerCard.setImageBitmap(BitmapFactory.decodeStream(getAssets().open(cards.get(r))));
        if(cards.get(r).equals("11c.png")||cards.get(r).equals("11d.png")||cards.get(r).equals("11h.png")||cards.get(r).equals("11s.png")){
            values+=5;
        }else if(cards.get(r).equals("12c.png")||cards.get(r).equals("12d.png")||cards.get(r).equals("12h.png")||cards.get(r).equals("12s.png")){
            values+=6;
        }else if(cards.get(r).equals("13c.png")||cards.get(r).equals("13d.png")||cards.get(r).equals("13h.png")||cards.get(r).equals("13s.png")){
            values+=7;
        }else if(cards.get(r).equals("1c.png")||cards.get(r).equals("1d.png")||cards.get(r).equals("1h.png")||cards.get(r).equals("1s.png")){
            values+=1;
        }else{
            values+=2;
        }
        editor.putInt("pointValues", values);
        editor.commit();
        current.setText("Current Values: "+values);
    }
    public void stayPressed(View v) throws IOException{
        //pressing stay should prevent player clicks from affecting anything else
        //Disables Hit Button
        //program should calculate points here
        //SharedPreferences pref = getApplicationContext().getSharedPreferences("savePoints", MODE_PRIVATE);
        //SharedPreferences.Editor editor = pref.edit();
        int pointSystem = 0;
        hit.setEnabled(false);
        //points.setText("Points: "+current);
        int r = rand.nextInt(cards.size());
        SharedPreferences savedPoints = this.getSharedPreferences("pointValues", 0);
        SharedPreferences.Editor pointEdit = savedPoints.edit();
        int val = savedPoints.getInt("pointValues", values);
        cardImage.setImageBitmap(BitmapFactory.decodeStream(getAssets().open(cards.get(r))));
        if(cards.get(r).equals("11c.png")||cards.get(r).equals("11d.png")||cards.get(r).equals("11h.png")||cards.get(r).equals("11s.png")){
            stayValues+=5;
        }else if(cards.get(r).equals("12c.png")||cards.get(r).equals("12d.png")||cards.get(r).equals("12h.png")||cards.get(r).equals("12s.png")){
            stayValues+=6;
        }else if(cards.get(r).equals("13c.png")||cards.get(r).equals("13d.png")||cards.get(r).equals("13h.png")||cards.get(r).equals("13s.png")){
            stayValues+=7;
        }else if(cards.get(r).equals("1c.png")||cards.get(r).equals("1d.png")||cards.get(r).equals("1h.png")||cards.get(r).equals("1s.png")){
            stayValues+=2;
        }else{
            stayValues+=10;
        }
        if(val<=stayValues){
            pointSystem+=val;
            stay.setEnabled(false);
            Toast.makeText(this, "Pretty close!", Toast.LENGTH_LONG).show();

        }else if(val == stayValues){
            pointSystem+=100;
            stay.setEnabled(false);
            Toast.makeText(this, "You win!", Toast.LENGTH_LONG).show();

        }else{
            pointSystem=-1;
            stay.setEnabled(false);
            if(pointSystem <0){
                Toast.makeText(this, "You Lose!", Toast.LENGTH_LONG).show();
                stay.setEnabled(false);
                //allows player to retry. Sets retry button to visible.
                retry.setEnabled(true);
                retry.setVisibility(View.VISIBLE);
            }
        }
        pointEdit.putInt("newPoints", pointSystem);
        points.setText("Points: "+pointSystem);
        pointEdit.commit();



    }
    public void retryPressed(View v){
        //basically restarts the app, making the player have to push Play again to start over
        //Yes. I know the game lacks depth but I thought it was fun at the time of creation.
        Intent i = new Intent(this, MainActivity.class);
        startActivityForResult(i, 1);
    }
    public void onBackPressed(){
        //This should save the most recent score and show it on the menu
        //It's not really a high score but I wanted to get Shared Preferences to work
        SharedPreferences savedPoints = this.getSharedPreferences("pointValues", 0);
        int highscore = savedPoints.getInt("newPoints", 0);
        Intent data = new Intent();
        data.putExtra("SCORE", highscore);
        setResult(RESULT_OK, data);
        finish();
    }


}
