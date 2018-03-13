package com.example.android.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private GridLayout grid;
    private SeekBar seeker;
    private TextView barLabel;
    private Button spinStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.grid);
        spinStop = findViewById(R.id.spinStop);
        barLabel = findViewById(R.id.barLabel);
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
        for(int i=0; i<3; i++) {
            ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.image_layout, grid, false);
            Drawable image = getResources().getDrawable(R.drawable.strawberry);
            imageView.setImageDrawable(image);
            grid.addView(imageView);
        }
    }
    public void buttonPressed(View v){
        spinStop.setText("STOP");

        /*if(spinStop.isPressed() && spinStop.getText().toString().equalsIgnoreCase("STOP")){
            spinStop.setText("SPIN");
        }*/

    }
}
