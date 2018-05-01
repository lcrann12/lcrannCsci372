package com.example.android.myapplication;

import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private GridLayout grid;//L
    private SeekBar seeker;
    private TextView barLabel;//E
    private Button spinStop;
    private boolean isSpin=true;
    private Handler handler;
    private Update update;//N
    private Random rand;
    private TextView points;//N
    private int pointSystem=0;

    private int imageA[] = {R.drawable.pear, R.drawable.cherry, R.drawable.grape, R.drawable.strawberry};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.grid);
        handler = new Handler();//Y
        update = new Update();
        spinStop = findViewById(R.id.spinStop);
        barLabel = findViewById(R.id.barLabel);
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
        //handler.postDelayed(update, 200);
        for(int i=0; i<grid.getColumnCount();i++){
            ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.image_layout, grid, false);
            Drawable image = getResources().getDrawable(imageA[rand.nextInt(imageA.length)]);
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
    public void buttonPressed(View v) {//L

        int speedUp = seeker.getProgress()*10;//'S
        if (isSpin==true) { //the button's text is set to SPIN, meaning the button hasn't been pressed yet
            spinStop.setText("STOP");//C
            grid.removeAllViews();//O
            for(int i=0; i<grid.getColumnCount();i++){//D
                ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.image_layout, grid, false);//E
                Drawable image = getResources().getDrawable(imageA[rand.nextInt(imageA.length)]);
                imageView.setImageDrawable(image);
                grid.addView(imageView);//E

            }

            handler.postDelayed(update, 200-speedUp);
            isSpin = false;//L
        } else{ //button text was set to STOP, meaning it had been pressed- Lenny's code Watermark
            spinStop.setText("SPIN");//E

            Drawable grid1 = ((ImageView)grid.getChildAt(0)).getDrawable(); //type case and add variables here
            Drawable grid2 = ((ImageView)grid.getChildAt(1)).getDrawable();
            Drawable grid3 = ((ImageView)grid.getChildAt(2)).getDrawable();
            Drawable.ConstantState slot1 = grid1.getConstantState();
            Drawable.ConstantState slot2 = grid2.getConstantState();
            Drawable.ConstantState slot3 = grid3.getConstantState();

            if(slot1.equals(slot2)&&slot2.equals(slot3)){
                pointSystem+=100;
            }else if(slot1.equals(slot2)||slot1.equals(slot3)||slot2.equals(slot3)) {
                pointSystem += 20;
            }else{
                pointSystem+=5;
            }
            points.setText("Points: "+pointSystem+"");
            handler.removeCallbacks(update);
            isSpin = true;

        }
    }
    private class Update implements Runnable{
        @Override
        public void run(){
            int speedUp = seeker.getProgress()*10;
            grid.removeAllViews();
            for(int i=0; i<grid.getColumnCount();i++){
                ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.image_layout, grid, false);
                Drawable image = getResources().getDrawable(imageA[rand.nextInt(imageA.length)]);
                imageView.setImageDrawable(image);
                grid.addView(imageView);

            }
            handler.postDelayed(this, 200-speedUp);
        }

    }


}
