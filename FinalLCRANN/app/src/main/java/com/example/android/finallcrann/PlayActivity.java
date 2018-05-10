package com.example.android.finallcrann;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        cardImage = findViewById(R.id.cardImage);
        playerCard = findViewById(R.id.playerCard);
        cards = new ArrayList<String>();
        rand = new Random();
        points = findViewById(R.id.points);
        //playerGrid=findViewById(R.id.playerGrid);
        current = findViewById(R.id.current);
        hit = findViewById(R.id.hit);
        stay = findViewById(R.id.stay);
        AssetManager manager = getAssets();
        try{
            for(String filename : manager.list("")){
                cards.add(filename);
            }
        }catch (IOException e){
            Toast.makeText(this, "No Card", Toast.LENGTH_SHORT).show();
        }


    }
    public void hitPressed(View v) throws IOException{
        //Unfortunately the player can hit an infinite amount of times
        //This is kind of non-optimally solved through the stayPressed method
        int r = rand.nextInt(cards.size());
        playerCard.setImageBitmap(BitmapFactory.decodeStream(getAssets().open(cards.get(r))));

        //hit.setText("");
        for(int i = 0; i<cards.size();i++){
            if(i<=3){
                //numbered card
                values+=10;
            }else if(i>=4 && i<=7){
                //jack
                values+=5;
            }else if(i>=8&&i<=11){
                //queen
                values+=6;
            }else if(i>=12&&i<=15){
                //king
                values+=7;
            }else if(i>=16&&i<=19){
                //ace
                values+=1;
            }else if(i>=20&&i<=53){
                //rest of the numbered cards
                values+=10;
            }
            int resID = getResources().getIdentifier(cards.get(r), "drawable", "com.example.android.finallcrann.MainActivity");
            current.setText(resID+"");
        }



    }
    public void stayPressed(View v) throws IOException{
        //pressing stay should prevent player clicks from affecting anything else
        //Disables Hit Button
        //program should calculate points here
        hit.setEnabled(false);
        points.setText("Points: "+current);
        int r = rand.nextInt(cards.size());
        cardImage.setImageBitmap(BitmapFactory.decodeStream(getAssets().open(cards.get(r))));



    }
    /*
    public void cardPressed(View v) throws IOException{
        int r = rand.nextInt(cards.size());
        cardImages.setImageBitmap(BitmapFactory.decodeStream(getAssets().open(cards.get(r))));

    }
    */

}
