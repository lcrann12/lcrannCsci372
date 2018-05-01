package com.example.android.projectmarch29;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by android on 3/29/18.
 */

public class MovieActivity extends AppCompatActivity {
    private EditText enterName;
    private EditText enterID;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_activity);
        enterName = findViewById(R.id.enterName);
        enterID = findViewById(R.id.enterID);

    }

    @Override
    public void onBackPressed(){
        Intent data = new Intent();
        data.putExtra("MNAME", enterName.getText().toString());
        data.putExtra("MID", enterID.getText().toString());
        setResult(RESULT_OK, data);
        finish();
    }
}
