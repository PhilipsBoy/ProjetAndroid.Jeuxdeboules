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



public class lancement extends AppCompatActivity { //Activité Launcher qui ouvre sur les autres


    EditText pseudo, pseudo2, difficulte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancement);
        pseudo = (EditText) findViewById(R.id.pseudo); // Association avec les textview
        pseudo2 = (EditText) findViewById(R.id.pseudo2);
        difficulte = (EditText) findViewById(R.id.difficulte);


    }

    public void debutPartie1Joueur(View view) {

       if(pseudo.getText().toString().length() > 2){ //On passe à l'activité suivante seulement si un pseudo est rentré
        Intent intent = new Intent(this, JeuDeBoules.class);
        intent.putExtra("Name", pseudo.getText().toString()); // On passe le pseudo
        intent.putExtra("ModeJeu", 0); //On passe le mode de jeu

       String diff = difficulte.getText().toString();

        if (diff.length() < 1)
            diff = "1";

        intent.putExtra("Difficulte", diff); //On passe la difficultés
        startActivity(intent);

         }

    }

    public void debutPartie2Joueur(View view) {

        if(pseudo.getText().toString().length() > 2 && pseudo2.getText().toString().length() > 2 ) {
            Intent intent = new Intent(this, JeuDeBoules.class);
            intent.putExtra("Name", pseudo.getText().toString());
            intent.putExtra("Name2", pseudo2.getText().toString());
            intent.putExtra("ModeJeu", 1);
            intent.putExtra("Difficulte", difficulte.getText().toString());
            startActivity(intent);
        }

    }

    public void meilleursscores (View view) {


        Intent intent = new Intent(this, BestScore.class);
        startActivity(intent);


    }


}


