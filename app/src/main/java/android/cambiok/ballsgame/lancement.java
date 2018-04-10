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
    EditText pseudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancement);
        pseudo = (EditText) findViewById(R.id.pseudo);



    }

    public void debutPartie1Joueur(View view) {

       if(pseudo.getText().toString().length() > 2){
        Intent intent = new Intent(this, JeuDeBoules.class);
        startActivity(intent);

         }

    }

    public void debutPartie2Joueur(View view) {

        // if(taille > 0){
        Intent intent = new Intent(this, JeuDeBoules.class);
        startActivity(intent);

        // }
    }

    public void meilleursscores (View view) {

        // if(taille > 0){
        Intent intent = new Intent(this, JeuDeBoules.class);
        startActivity(intent);

        // }
    }


}


