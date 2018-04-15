// test
// jjh
// ff
package android.cambiok.ballsgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.jar.Attributes;


public class JeuDeBoules extends AppCompatActivity {

    private Canvas testCanv;
    private Paint testPaint = new Paint(); //Création des différents Paint utilisés dans l'acitivité
    private Paint painttext = new Paint();
    private Paint painttext1 = new Paint();
    private Paint painttext2 = new Paint();
    private Paint paintform = new Paint();
    float initialX, initialY;


    int xx, yy;
    int xx_tmp, yy_tmp;

    int taille = 100;

    Game GameEngine;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this)); // On affiche l'image view où l'on dessinera avec le Canvas


        Bundle extras2 = getIntent().getExtras();
        int mode;
        mode = extras2.getInt("ModeJeu"); // Récupération du mode de jeu

        Bundle extras = getIntent().getExtras();
        Bundle extras3 = getIntent().getExtras();
        Bundle extras4 = getIntent().getExtras();

        String nomJoueur1, nomJoueur2;
        nomJoueur1 = extras.getString("Name"); //Récupération des pseudos donnés dans l'activité lancement
        nomJoueur2 = extras3.getString("Name2");

        String difficulte;
        difficulte = extras4.getString("Difficulte"); // Récupération de la donnée difficulté

        GameEngine = new Game(13, 10, mode, Integer.valueOf(difficulte)); // ajouter difficulte

        GameEngine.Joueur1.setName(nomJoueur1); // Attribution du nom des joueurs
        GameEngine.Joueur2.setName(nomJoueur2);


    }

    public class MyView extends View {
        Paint paint = null;


        public MyView(Context context) { //Création de la view
            super(context);
            paint = new Paint();
        }

        public boolean onTouchEvent (MotionEvent event) { // fonction qui gère les actions sur l'écran

            int action = event.getAction();

            //MediaPlayer mp;
            //mp = MediaPlayer.create(JeuDeBoules.this, R.raw.wow); // mp lié au fichier mp3 wow

            switch (action) {

             case(MotionEvent.ACTION_DOWN): //Quand touché sur l'écran tactile


                    initialX = event.getX(); // Récupération des coordonées du click
                    initialY = event.getY();

                    xx_tmp = (int) initialX / 107; // Choix du carré le plus proche correspondant au click
                    yy_tmp = (int) initialY / 107;

                    if (xx_tmp >= 0 && xx_tmp < 10 && yy_tmp >= 0 && yy_tmp < 13) {

                        GameEngine.updateGameState(yy_tmp, xx_tmp);
                        //mp.start();
                    }

                    break;

            }
            invalidate();
            return true;
        }



        protected void onDraw(Canvas testCanv) { // Méthode onDraw pour dessiner sur testCanvas
            super.onDraw(testCanv);
            int x = 0;
            int y = 0;
            int w = 95;
            int z = 95;
            int i, j;
            paint.setStyle(Paint.Style.FILL); //Paramétrage des paint
            paint.setColor(Color.WHITE);
            testCanv.drawPaint(paint);

            paintform.setStyle(Paint.Style.FILL);
            paintform.setColor(Color.BLACK);

            painttext1.setStyle(Paint.Style.FILL);
            painttext1.setColor(Color.BLACK);

            painttext2.setStyle(Paint.Style.FILL);
            painttext2.setColor(Color.BLACK);

            painttext1.setTextSize(50);
            painttext2.setTextSize(50);

            painttext.setTextSize(50);
            //MediaPlayer mp2;
            //mp2 = MediaPlayer.create(JeuDeBoules.this, R.raw.game_over); // mp2 lié au fichier mp3 game over
            String fin;
            if (GameEngine.CheckGameOver2() == 1) { //Vérification de l'état de la partie
                     fin = "Game Over";

                } else {
                    fin = "En jeu";
                }

            if(GameEngine.getTour()== 1 && GameEngine.getGameMode() == 1){ //Si mode 2 joueurs, attribution d'une couleur à celui qui à la main
                painttext1.setColor(Color.RED);
            }else painttext2.setColor(Color.RED);

            testCanv.drawText(GameEngine.Joueur1.getName() + " : " +  String.valueOf(GameEngine.Joueur1.getScore())+" points", 85, 1550, painttext1); // Pseudo et score du joueur 1
            testCanv.drawText("Niveau : " + String.valueOf(GameEngine.getGameLevel()),715, 1550, painttext);
            testCanv.drawText(fin,715, 1650, painttext);
            testCanv.drawRect(1068, 1455, 5, 1470, paintform); // barre de séparation

            if(GameEngine.getGameMode() == 1){
                testCanv.drawText(GameEngine.Joueur2.getName() + " : " +  String.valueOf(GameEngine.Joueur2.getScore())+ " points", 85, 1650, painttext2 ); // Pseudo et score du joueur 2
            }


            int tab [][]= GameEngine.getTableau();
            int Selectedtab [][]= GameEngine.getSelectedTableau();

            //if (GameEngine.CheckGameOver2() == 1)
               // mp2.start();


            int espacement = 107;



            for (j = 0, y = 5; j < GameEngine.getTailleX(); j++) { // Position du dessin en Y


                for (i = 0, x = 5; i < GameEngine.getTailleY(); i++) { // Position du dessin en X



                    testPaint.setStyle(Paint.Style.FILL);

                    if (tab[j][i] == 0) {
                        testPaint.setColor(Color.GREEN); // Application de la couleur aux rectangles en fonction de la difficulté
                    }
                    else if (tab[j][i] == 1) {
                        testPaint.setColor(Color.BLUE);
                    }
                    else if (tab[j][i] == 2) {
                        testPaint.setColor(Color.RED);
                    }
                    else if (tab[j][i] == 3) {
                        testPaint.setColor(Color.YELLOW);
                    }
                    else if (tab[j][i] == 4) {
                        testPaint.setColor(Color.MAGENTA);
                    }
                    else if (tab[j][i] == 5) {
                        testPaint.setColor(Color.CYAN);
                    }
                    else if (tab[j][i] == 6) {
                        testPaint.setColor(Color.BLACK);
                    }
                    else {

                    }

                    if (tab[j][i] != -1) {
                        if (Selectedtab[j][i] == 1) {
                            // selectionné
                            testPaint.setColor(Color.BLACK);
                            testCanv.drawRect(x, y, x + taille, y + taille, testPaint); // Dessin d'un rectangle noir lors de la sélection
                        } else {
                            // non selectionné
                            testCanv.drawRect(x, y, x + taille, y + taille, testPaint); // Dessin des rectangles du plateau de jeu
                        }
                    }


                    x = x + espacement;
                }

                y = y + espacement;
            }



        }


    }

}