package com.example.lenny.cards;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.widget.ImageView;

import android.widget.SeekBar;
import android.widget.TextView;

public class RulesActivity extends AppCompatActivity {

    private SeekBar seekbar;
    private TextView seekLabel;
    private TextView rulesText;
    private ImageView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        rulesText = findViewById(R.id.rulesText);
        seekbar = findViewById(R.id.seekbar);
        seekLabel = findViewById(R.id.seekLabel);
        cardView = findViewById(R.id.cardView);
        seekbar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                        seekLabel.setText(progress+1+"/9");
                        //This just allows the player to scroll through the rules
                        //Doesn't have any meaningful depth, but I think it's pretty cool.
                        if(progress==0){
                            rulesText.setText("The goal of the game is to hit or stay with card values as close as possible to the number 15");
                            cardView.setImageResource(R.drawable.cardback);
                        }else if(progress==1){
                            rulesText.setText("Since I couldn't code an enemy AI, your only enemy is yourself");
                            cardView.setImageResource(R.drawable.cardback);
                        }else if(progress==2){
                            rulesText.setText("If you go over the unknown card's value, you lose points. If you reach below 0 points you lose the game");
                            cardView.setImageResource(R.drawable.cardback);
                        }
                        else if(progress ==3){
                            rulesText.setText("Though it's similar to Blackjack, unlike Blackjack, numbered cards are all the same value: 2");
                            cardView.setImageResource(R.drawable.cardback);
                        }else if(progress ==4){
                            rulesText.setText("Likewise, face cards are as followed: ");
                            cardView.setImageResource(R.drawable.cardback);
                        }else if(progress==5){
                            rulesText.setText("Jacks are equal to 5");
                            cardView.setImageResource(R.drawable.jack);
                        }else if(progress==6){
                            rulesText.setText("Queens are equal to 6");
                            cardView.setImageResource(R.drawable.queen);
                        }else if(progress==7){
                            rulesText.setText("Kings are equal to 7");
                            cardView.setImageResource(R.drawable.king);
                        }else if(progress==8){
                            rulesText.setText("Last and least, Aces are just equal to 1. Click back to go back to the menu and test your luck!");
                            cardView.setImageResource(R.drawable.ace);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );



    }

}
