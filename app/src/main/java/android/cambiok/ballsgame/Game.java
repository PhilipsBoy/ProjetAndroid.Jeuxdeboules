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
    int tailleX;
    int tailleY;

    // Joueur Joueur1;
    // Joueur Joueur2;

    // Liste des méthodes :
    // generateRandomGameTab
    // getTab x
    // InitTab x
    // GetCaseColor x
    // setCaseCpmpr x
    // UpdateTab
    // TrouverVoisin

    public int getTailleX() {return tailleX;}
    public int getTailleY() {return tailleY;}

    public void setTailleX(int taille) {tailleX = taille;}
    public void setTailleY(int taille) {tailleY = taille;}

    public int [][] getTableau() {return Tableau;}

    public Game(int tailleX, int tailleY) { // Constructeur
        setTailleX(tailleX);
        setTailleY(tailleY);

        Tableau = new int[getTailleX()][getTailleY()];
        GenerationAleatoireTableau(3);
    }

    // void GenerationAleatoireTableau(int difficulte) {
    // Tab = -1 -> case vide
    // Tab = [0; difficulte] -> case pleine avec couleur [0; difficulte]
    void GenerationAleatoireTableau(int difficulte) {
        int x, y;
        Random r = new Random();
        int randomNumber;

        for (x = 0; x < tailleX; x++) {
            for (y = 0; y < tailleY; y++){
                randomNumber = r.ints(1, 0, difficulte).findFirst().getAsInt();
                Tableau[x][y] = randomNumber;
            }
        }
    }

    int TrouverVoisin(int x, int y) {
        //int Tableau_tmp[][] = getTableau();

        //return Tableau_tmp;

        return 5;
    }


    int GetCaseColor(int x, int y) {
        if ((x >= 0 && x <= getTailleX()) && (y >= 0 && y <= getTailleY()))
            return Tableau[x][y];
        else
            return 0;
    }

    void setCaseColor(int x, int y, int color) {
        if ((x >= 0 && x <= getTailleX()) && (y >= 0 && y <= getTailleY()))
            Tableau[x][y] = color;
    }

    void initTableau()  {
        int x, y;

        for (x = 0; x < getTailleX(); x++) {
            for (y = 0; y < getTailleY(); y++){
                Tableau[x][y] = -1;
            }
        }
    }

}
