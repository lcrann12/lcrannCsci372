package com.example.android.myapplication;

import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private GridLayout grid;
    private SeekBar seeker;
    private TextView barLabel;
    private Button spinStop;
    private boolean isSpin;
    private Handler handler;
    private Update update;
    private Random rand;
    private TextView points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.grid);
        handler = new Handler();
        update = new Update();
        spinStop = findViewById(R.id.spinStop);
        spinStop.setText("SPIN");
        isSpin = true;
        barLabel = findViewById(R.id.barLabel);
        int imageA[] = {R.drawable.pear, R.drawable.cherry, R.drawable.grape, R.drawable.strawberry};
        points = findViewById(R.id.points);
        rand = new Random();
        seeker = findViewById(R.id.seeker);
        seeker.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                        barLabel.setText("SPEED UP BY: "+progress*10+"%");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
        for(int i=0; i<3;i++){
            ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.image_layout, grid, false);
            Drawable image = getResources().getDrawable(imageA[i]);
            imageView.setImageDrawable(image);
            grid.addView(imageView);
        }
        //handler.postDelayed(update, 500);
        /*for(int i=0; i<3; i++) {
            ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.image_layout, grid, false);
            Drawable image = getResources().getDrawable(R.drawable.strawberry);
            imageView.setImageDrawable(image);
            grid.addView(imageView);


        }*/
    }
    public void buttonPressed(View v) {
        int time = 500;///(1+seeker.getProgress());
        int i = 0;
        int pointSystem=0;
        Random rand = new Random();
        int imageA[] = {R.drawable.cherry, R.drawable.strawberry, R.drawable.grape, R.drawable.pear};
        if (isSpin) {
            spinStop.setText("STOP");
            isSpin = false;
            while (isSpin) {

                ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.image_layout, grid, false);
                Drawable image = getResources().getDrawable(imageA[i]);
                imageView.setImageDrawable(image);
                grid.removeViewAt(i);
                grid.addView(imageView, i);
                if(i<3) {
                    i++;
                }else{
                    i=0;
                }
            }
            handler.postDelayed(update, 500);



        } else {
            if(grid.getChildAt(0).equals(imageA[0])||grid.getChildAt(1).equals(imageA[0])||grid.getChildAt(2).equals(imageA[0])){
                pointSystem+=20;
                points.setText("Points: " + pointSystem);
            }else if(grid.getChildAt(0).equals(imageA[1])&&grid.getChildAt(1).equals(imageA[1])&&grid.getChildAt(2).equals(imageA[1])){
                pointSystem+=50;
                points.setText("Points: " + pointSystem);
            }else if(grid.getChildAt(i).equals(imageA[2])){
                pointSystem+=5;
                points.setText("Points: " + pointSystem);
            }else{
                pointSystem+=1;
                points.setText("Points: " + pointSystem);
            }

            spinStop.setText("SPIN");
            isSpin = true;

            handler.removeCallbacks(update);

        }
    }
    private class Update implements Runnable{
        int imageA[] = {R.drawable.cherry, R.drawable.strawberry, R.drawable.grape, R.drawable.pear};
        int i = 0;
        public void run(){
            if(i<3){
                handler.postDelayed(this, 500);
                i++;
            }else{
                i=0;
            }

        }

    }
}
