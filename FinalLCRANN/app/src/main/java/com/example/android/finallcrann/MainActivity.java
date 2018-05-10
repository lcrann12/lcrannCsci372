package com.example.android.finallcrann;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

}
