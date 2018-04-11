// ff
// gg
package android.cambiok.ballsgame;

import java.util.Random;

/**
 * Created by bat on 07/03/2018.
 */

public class Game {
    int Tableau[][];
    int SelectedTableau[][];
    int TableauMemoire[][];

    int selectedx;
    int selectedy;

    int tailleX;
    int tailleY;

    Joueur Joueur1;
    Joueur Joueur2;

    // Game mode
    // 0 : 1 joueur
    // 1 : 2 joueurs
    int GameMode;

    int GameLevel;

    int varover;

    int Tour; // Determine quel joueur doit jouer 1->Joueur1, 2->Joueur2


    public int CheckGameOver2() {
        return varover;
    }

    public int CheckGameOver() {
        int x, y;

        int gameover = 1;
        int score = 1;

        for (x = 0; x < getTailleX(); x++) {
            for (y = 0; y < getTailleY(); y++){
                if (GetCaseColor(x, y) != -1) {
                    CleanTabMemoire();
                    TrouverVoisin(x, y);
                    score = getSelectedScore();  // compte selected tab

                    if (score != 1)
                        gameover = 0;
                }
            }
        }

        CleanTabMemoire();

        return gameover;
    }


    public void updateGameState(int x, int y) {
        int mode = getGameMode();

        // mode 1 joueur
        if (mode == 0 || mode == 1) {
            if (getSelectedScore() > 0) { // Existe t-il déjà des blocs selectionnés
                if (SelectedTableau[x][y] == 1) { // Le click se trouve t-il sur une case selectionné


                    skynet(); // Destruction des blocs selectionnes
                    CleanTabMemoire(); // On nettoie le tableau memoire

                    // On fait tomber les cases et on les decale
                    updateGame();
                    updateGame();
                    updateGame();
                    updateGame();

                    // On verifie si la partie est terminée
                    varover = CheckGameOver();

                    if (varover == 1) { // game over
                        if (GetCaseColor(getTailleX() - 1, getTailleY() - 1) == -1) { // Tableau completement vide?
                            varover = 0;
                            setGameLevel(getGameLevel() + 1);
                            GenerationAleatoireTableau(getGameLevel());
                        }

                        // Si tableau non terminé : on est en attente.
                    }

                    // Si aucune case, on genere un nouveau niveau
                }
                else { // Le joueur click sur un nouvel ensemble de block.
                    CleanTabMemoire();
                    TrouverVoisin(x, y);

                   if (getSelectedScore() == 1) // Si la taille du groupe est de une case : on ne selectionne pas.
                      CleanTabMemoire();
                }
            }

            else { // Il n'y a pas de blocs selectionnés
                CleanTabMemoire();
                TrouverVoisin(x, y);

                if (getSelectedScore() == 1) // Si une case : on annule la selection.
                     CleanTabMemoire();
            }
        }
    }

    public int getTailleX() {return tailleX;}
    public int getTailleY() {return tailleY;}

    public void setTailleX(int taille) {tailleX = taille;}
    public void setTailleY(int taille) {tailleY = taille;}

    public int getGameLevel() {return GameLevel;}
    public void setGameLevel(int GameLevel2) {GameLevel = GameLevel2;}

    public int getGameMode() {return GameMode;}

    public int getTour () {return Tour;}
    public void setTour (int Tourr) {Tour = Tourr;}

    public void setGameMode(int mode) {

        if (mode == 0 || mode == 1)
            GameMode = mode;
        else
            GameMode = 0;

        // Reset Tableau et elements de jeux
        Joueur1.setScore(0);
        Joueur2.setScore(0);
    }

    public int [][] getTableau() {return Tableau;}
    public int [][] getSelectedTableau() {return SelectedTableau;}

    public Game(int tailleX, int tailleY, int gameMode) { // Constructeur Game
        int x, y;

        //
        setTailleX(tailleX);
        setTailleY(tailleY);

        Joueur1 = new Joueur("Joueur1");
        Joueur2 = new Joueur("Joueur2");

        setGameMode(gameMode);

        Tour = 1; // Le joueur1 commence

        selectedx = 0;
        selectedy = 0;

        Tableau = new int[getTailleX()][getTailleY()];
        SelectedTableau = new int[getTailleX()][getTailleY()];
        TableauMemoire = new int[getTailleX()][getTailleY()];

        if (getGameMode() == 0) // Mode 1 joueur : on commence à partir du level 1
            setGameLevel(1);
        else // Mode 2 joueurs : on commence à partir du level 2
            setGameLevel(2);

        int level = getGameLevel();

        GenerationAleatoireTableau(level);

        // Mise à zéro tableau selection
        for (x = 0; x < getTailleX(); x++) {
            for (y = 0; y < getTailleY(); y++) {
                SelectedTableau[x][y]  = 0;
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

        for (x = 0; x < getTailleX(); x++) {
            for (y = 0; y < getTailleY(); y++){
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

        if (getTour() == 1) {
            Joueur1.setScore(Joueur1.getScore() + getSelectedScore() * 1000); // Ajout score

            if (getGameMode() == 1) // Si mode 2 joueurs, alors on alterne.
                setTour(2);
        }
        else {
            Joueur2.setScore(Joueur2.getScore() + getSelectedScore() * 1000); // Ajout score
            setTour(1);
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
        boolean colonnevide;

        // Decalage vers le bas : ok
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


        // Decallage à droite
        for (y = getTailleY() - 1; y >= 0; y--) {
            // On regarde si la colonne y est "vide" (= remplie de -1).
            colonnevide = true;

            for (x = 0; x < getTailleX(); x++) {
                if (GetCaseColor(x, y) != -1) {
                    colonnevide = false;
                }
            }

            // La colone était-elle vide?
            if (colonnevide == true) {
                // Oui : on decale toutes les colones se trouvant à gauche.
                for (y2 = y; y2 >= 0; y2--) {
                    for (x2 = 0; x2 < getTailleX(); x2++) {
                        if ((y2 - 1) >= 0) {
                            color = GetCaseColor(x2, y2 - 1);
                            setCaseColor(x2, y2, color);
                            setCaseColor(x2, y2 - 1, -1);
                        }
                    }
                }
            }
        }
    }
}
