package com.example.android.projectmarch29;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String[] movies = {"JAWS", "AIRPLANE", "Raiders of the Lost Ark", "Ghostbusters", "Groundhog Day", "Dumb and Dumber"};
    private static String[] moviesID = {"tt0073195", "tt0080339", "tt0082971", "tt0087332", "tt0107048", "tt0109686"};
    //private String idValues;
    private ListView movieList;
    private ArrayList<String> moviesAdded;
    private ArrayList<String> moviesIDAdded;
    private ArrayAdapter<String> adapter;
    private SharedPreferences pref;
    //private Button addMovies
    //Lenny's Code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieList = findViewById(R.id.movieList);
        moviesAdded = new ArrayList<String>();
        moviesIDAdded = new ArrayList<String>();
        for (int i = 0; i < movies.length; i++) {
            moviesAdded.add(movies[i]);
        }
        for (int i = 0; i<moviesID.length; i++){
            moviesIDAdded.add(moviesID[i]);
        }

        adapter = new ArrayAdapter<String>(this, R.layout.list_item_view, moviesAdded);
        movieList.setAdapter(adapter);//Y
        movieList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String url = "https://www.imdb.com/title/";
                        url += moviesIDAdded.get(i);
                        Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));//N
                        startActivity(in);
                    }

                }
        );

        movieList.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(getApplicationContext(), "You removed "+moviesAdded.get(i), Toast.LENGTH_SHORT).show();
                        moviesAdded.remove(i);//N
                        moviesIDAdded.remove(i);
                        adapter.notifyDataSetChanged();
                        movieList.setAdapter(adapter);
                        return true;//E
                    }
                }
        );

    }

    public void buttonPressed(View v){
        Intent i = new Intent(this, MovieActivity.class);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        String movieName = data.getStringExtra("MNAME");
        String movieId = data.getStringExtra("MID");
        ArrayAdapter<String> adapter;//Lenny's Code
        adapter = new ArrayAdapter<String>(this, R.layout.list_item_view, moviesAdded);
        movieList.setAdapter(adapter);
        movieList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String url = "https://www.imdb.com/title/";
                        url += moviesIDAdded.get(i);
                        Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(in);
                    }
                }
        );
        moviesAdded.add(movieName);
        moviesIDAdded.add(movieId);//L

    }
    @Override
    public void onStart(){
        super.onStart();
        SharedPreferences pref;
        pref = this.getApplicationContext().getSharedPreferences("moviesAdded", MODE_PRIVATE);

    }
    @Override
    public void onStop(){
        super.onStop();
        SharedPreferences pref;
        pref = PreferenceManager.getDefaultSharedPreferences()
    }
}
