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
    int SelectedTableau[][];

    int selectedx;
    int selectedy;

    int tailleX;
    int tailleY;

    int TableauMemoire[][];


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
    public int [][] getSelectedTableau() {return SelectedTableau;}

    public Game(int tailleX, int tailleY) { // Constructeur
        int x, y;

        setTailleX(tailleX);
        setTailleY(tailleY);

        selectedx = 0;
        selectedy = 0;

        Tableau = new int[getTailleX()][getTailleY()];
        SelectedTableau = new int[getTailleX()][getTailleY()];
        TableauMemoire = new int[getTailleX()][getTailleY()];

        GenerationAleatoireTableau(3);

        // Mise à zéro tableau selection
        for (x = 0; x < tailleX; x++) {
            for (y = 0; y < tailleY; y++) {
                SelectedTableau[x][y] = 0;
                TableauMemoire = new int[getTailleX()][getTailleY()];
            }
        }


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

    void CleanTabMemoire() {
        int x, y;

        for (x = 0; x < getTailleX(); x++) {
            for (y = 0; y < getTailleY(); y++){
                TableauMemoire[x][y] = 0;
                SelectedTableau[x][y] = 0;
            }
        }
    }

    public void skynet() {
        int x, y;

        for (x = 0; x < getTailleX(); x++) {
            for (y = 0; y < getTailleY(); y++){
                if (SelectedTableau[x][y] == 1) {
                    setCaseColor(x, y, -1);
                }
            }
        }
    }

    public int getSelectedScore() {
        int score = 0;
        int x, y;

        for (x = 0; x < getTailleX(); x++) {
            for (y = 0; y < getTailleY(); y++){
                if (SelectedTableau[x][y] == 1) {
                    score = score + 1;
                }
            }
        }

        return score;
    }

    public void TrouverVoisin(int x, int y) {
        // On me donne un point x et y.
        // 1. J'inscrit ce pt en mémoire

        // 2. Je vérifie le nord
        // 3. Je vérifie l'ouest
        // 4. Je vérifie l'est
        // 5. Je vérifie le sud


        if (GetCaseColor(x, y) != -1) {

            TableauMemoire[x][y] = 1;
            SelectedTableau[x][y] = 1;

            // On vérifie les quatre voisins autour.
            if ((x + 1) < getTailleX() && (GetCaseColor(x, y) == GetCaseColor(x + 1, y)) && TableauMemoire[x + 1][y] == 0) {
                SelectedTableau[x + 1][y] = 1;
                TrouverVoisin(x + 1, y);
            }

            if ((x - 1) >= 0 && (GetCaseColor(x, y) == GetCaseColor(x - 1, y)) && TableauMemoire[x - 1][y] == 0) {
                SelectedTableau[x - 1][y] = 1;
                TrouverVoisin(x - 1, y);
            }

            if ((y + 1) < getTailleY() && (GetCaseColor(x, y) == GetCaseColor(x, y + 1)) && TableauMemoire[x][y + 1] == 0) {
                SelectedTableau[x][y + 1] = 1;
                TrouverVoisin(x, y + 1);
            }

            if ((y - 1) >= 0 && (GetCaseColor(x, y) == GetCaseColor(x, y - 1)) && TableauMemoire[x][y - 1] == 0) {
                SelectedTableau[x][y - 1] = 1;
                TrouverVoisin(x, y - 1);
            }
        }
    }

    int GetCaseColor(int x, int y) {
        if ((x >= 0 && x < getTailleX()) && (y >= 0 && y < getTailleY()))
            return Tableau[x][y];
        else
            return 0;
    }

    void setCaseColor(int x, int y, int color) {
        if ((x >= 0 && x < getTailleX()) && (y >= 0 && y < getTailleY()))
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

    // Mise à jour du tableau de jeu.
    void updateGame() {
        int x, y;
        int x2, y2;

        int color = 0;

        // GetCaseColor(x, y);
        // SetCaseColor(x, y, color);

        // Decalage vers le bas

        for (y = 0; y < getTailleY(); y++) {
            for (x = 0; x < getTailleX(); x++) {
                if (GetCaseColor(x, y) == -1) {
                    x2 = x;
                    y2 = y;

                    for (x2 = x; x2 >= 0; x2--) {
                        if (x2 - 1 >= 0) {
                            color = GetCaseColor(x2 - 1, y2);
                            setCaseColor(x2, y2, color);
                            setCaseColor(x2 - 1, y2, -1);
                        }
                    }
                }
            }
        }

        /*
        for (x = getTailleX() - 1; x >= 0; x--) {
            for (y = getTailleY() - 1; y >= 0; y--) {
                if (GetCaseColor(x, y) == -1) {

                    // On decale tous vers le bas
                    for (x2 = x; x2 >= 0; x2--) {
                        if (x2 - 1 >= 0) {
                            // On recupère la couleur de la case de dessus
                            color = GetCaseColor(x2 - 1, y);

                            // On l'applique à la case actuelle
                            setCaseColor(x, y, color);

                            // On vide la case du dessus
                            setCaseColor(x2 - 1, y, -1);
                        }
                    }
                }
            }
        }
        */


                    /*
                    // On decale tous vers la droite
                    for (x2 = x; x2 >= 0; x2--) {
                        if (x2 - 1 >= 0) {
                            // On recupère la couleur de la case de dessus
                            color = GetCaseColor(x2 - 1, y);

                            // On l'applique à la case actuelle
                            setCaseColor(x, y, color);

                            // On vide la case du dessus
                            setCaseColor(x2 - 1, y, -1);
                        }
                    }

                    */


        // Decalage vers la droite
    }

}
