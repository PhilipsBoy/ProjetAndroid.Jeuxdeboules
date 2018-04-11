package android.cambiok.ballsgame;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class BestScore extends AppCompatActivity {

    //ArrayList<String> topScore = new ArrayList<String>();
    //ListView tab;


    String [] tabScore = {"Coucou"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_score);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.scores, tabScore);

        ListView scores = (ListView) findViewById(R.id.tableau);
        scores.setAdapter(adapter);

    }


}
