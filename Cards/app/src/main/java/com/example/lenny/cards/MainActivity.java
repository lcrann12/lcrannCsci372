package com.example.lenny.cards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    private TextView highscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        highscore = findViewById(R.id.highscore);
    }
    public void rulesPressed(View v){
        //starts Rules Activity page
        Intent i = new Intent(this, RulesActivity.class);
        startActivityForResult(i, 1);
    }
    public void playPressed(View v){
        //starts Play Activity page
        Intent i = new Intent(this, PlayActivity.class);
        startActivityForResult(i, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        highscore.setText("Highscore: "+ data.getIntExtra("SCORE", 0));
    }
}
