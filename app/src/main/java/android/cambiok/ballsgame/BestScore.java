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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


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
        try {
            test();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void test () throws IOException {
        File path = this.getFilesDir();
        File file = new File(path, "my-file-name.txt");


        FileOutputStream stream = new FileOutputStream(file);
        try {
            stream.write("A|B|C".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }

        // Lecture
        int length = (int) file.length();

        byte[] bytes = new byte[length];

        FileInputStream in = new FileInputStream(file);
        try {
            in.read(bytes);
        } finally {
            in.close();
        }

        String contents = new Scanner(file).useDelimiter("|").next();
    }


}
