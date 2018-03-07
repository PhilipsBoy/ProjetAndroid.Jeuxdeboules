package android.cambiok.ballsgame;

import java.util.Random;

/**
 * Created by bat on 07/03/2018.
 */

/*
Variables à faire :
    - Joueur 1 & Joueur 2
    - Tableau
    -

Methodes à faire :
    - GenerationAleatoireTableau
    - TrouverVoisin
    - ResetGame
*/

public class Game {
    int Tableau[][];

    // Joueur Joueur1;
    // Joueur Joueur2;

    public Game() { // Constructeur

    }

    // void GenerationAleatoireTableau(int difficulte) {
    // Tab = -1 -> case vide
    // Tab = [0; difficulte] -> case pleine avec couleur [0; difficulte]
    void GenerationAleatoireTableau(int difficulte) {
        // ..

        int x, y;


        // https://www.compilejava.net/
        //
        int randomNumber = r.ints(1, 0, 11).findFirst().getAsInt();


        for (i = 0; i < ..; i++) {
            for (y = 0; y < ..; y++){
                int randomNumber = r.ints(1, difficulte, 11).findFirst().getAsInt();
                Tableau[x][y] = randomNumber;
            }
        }



    }

    int [] [] TrouverVoisin() {
        // ..

        return Tableau;
    }

    int [][] getTableau()  {
        return Tableau;
    }

    void initTableau()  {
        int x, y;

        for (i = 0; i < ..; i++) {
            for (y = 0; y < ..; y++){
                Tableau[x][y] = -1;
            }
        }
    }

}
