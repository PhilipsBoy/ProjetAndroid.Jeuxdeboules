package android.cambiok.ballsgame;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.jar.Attributes;



public class lancement extends AppCompatActivity {


    String nom;
    EditText pseudo, pseudo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancement);
        pseudo = (EditText) findViewById(R.id.pseudo);
        pseudo2 = (EditText) findViewById(R.id.pseudo2);


    }

    public void debutPartie1Joueur(View view) {

       if(pseudo.getText().toString().length() > 2){
        Intent intent = new Intent(this, JeuDeBoules.class);
        intent.putExtra("Name", pseudo.getText().toString());
        intent.putExtra("ModeJeu", 0);
        startActivity(intent);

         }

    }

    public void debutPartie2Joueur(View view) {

        if(pseudo.getText().toString().length() > 2 && pseudo2.getText().toString().length() > 2 ) {
            Intent intent = new Intent(this, JeuDeBoules.class);
            intent.putExtra("Name", pseudo.getText().toString());
            intent.putExtra("Name2", pseudo2.getText().toString());
            intent.putExtra("ModeJeu", 1);
            startActivity(intent);
        }

    }

    public void meilleursscores (View view) {


        Intent intent = new Intent(this, MeilleurScore.class);
        startActivity(intent);

        // }
    }


}


