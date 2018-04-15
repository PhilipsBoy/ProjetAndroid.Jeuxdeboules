// BestScore java
// Lit le fichier de score

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

    Scanner s;

    String contents;

    // A remplacer par tableau ........
    String Pseudo1;
    String Score1;

    String Pseudo2;
    String Score2;

    String Pseudo3;
    String Score3;

    String Pseudo4;
    String Score4;

    String Pseudo5;
    String Score5;

    String Pseudo6;
    String Score6;

    String Pseudo7;
    String Score7;

    String Pseudo8;
    String Score8;

    String Pseudo9;
    String Score9;

    String Pseudo10;
    String Score10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_score);

        /*
        try {
            LectureFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // A modifier pour mettre PseudoX / ScoreX j'en sais rien
        //String [] tabScore = {Pseudo1 + " " + Score1, Score1, Pseudo2, Score2, Pseudo3, Score3, Pseudo4, Score4};
        String [] tabScore = {"Classement", "", "", "", "Bat : 22400", "Bat : 1114", "Bat : 1114", "Bat : 1114", "Bat : 1114", "Bat : 50"};

        int i = 0, y;

        // Ajout position
        for (i = 0, y = 1; i < tabScore.length; i++) {
            if (tabScore[i] != "" && tabScore[i] != "Classement") {
                tabScore[i] = y + ". " + tabScore[i];
                y++;
            }
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.scores, tabScore);

        ListView scores = (ListView) findViewById(R.id.tableau);
        scores.setAdapter(adapter);

        */
    }

    public void LectureFile () throws IOException {
        /*
        File path = this.getFilesDir();
        File file = new File(path, "my-file-name.txt");

        // Ecriture
        FileOutputStream stream = new FileOutputStream(file);
        try {
            stream.write("Pseudo1|Score1|Pseudo2|Score2|Pseudo3|Score3|Pseudo4|Score4|Pseudo5|Score5|Pseudo6|Score6|Pseudo7|Score7|Pseudo8|Score8|Pseudo9|Score9|Pseudo10|Score10|".getBytes());

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

        Scanner test = new Scanner(file);

        test.useDelimiter("|");
        //String contents = test.next();

        String tmp = "";

        while (test.hasNext()) {
            tmp = test.next();

            if (tmp == "|")
                break;

            Pseudo1 = Pseudo1 + tmp;
        }

        contents = test.next();

        String[] array = contents.split("|", -1);
        array[0] = array[0] + "  ";
        ;
        ;
/*
        Pseudo1 = s.next();
        Score1 = s.next();

        Pseudo2 = s.next();
        Score2 = s.next();

        Pseudo3 = s.next();
        Score3 = s.next();

        Pseudo4 = s.next();
        Score4 = s.next();

        Pseudo5 = s.next();
        Score5 = s.next();

        Pseudo6 = s.next();
        Score6 = s.next();

        Pseudo7 = s.next();
        Score7 = s.next();

        Pseudo8 = s.next();
        Score8 = s.next();

        Pseudo9 = s.next();
        Score9 = s.next();

        Pseudo10 = s.next();
        Score10 = s.next();*/
    }
}
