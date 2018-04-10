// ff

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
    // autre : erreur
    int GameMode;

    // Game State
    // Si Game mode = 0
    // 0 : tableau non vide + non bloqué
    //  -> on attend un click sur un ensemble de block
    //  -> un click sur un block a pour effet de le sectionner
    // 1 : tableau non vide + bloqué
    //  -> Le joueur est bloqué, gameover, on enregistre son score dans MeilleurScore
    // 2 : tableau vide
    //  -> on regenre un tableau avec augmentation de la difficulté
    // Si Game mode = 1
    // 0 :
    // 1 :
    // 2 :
    int GameState;

    int GameLevel;

    int varover;


    public int CheckGameOver2() {
        return varover;
    }


    public int CheckGameOver() {
        int x, y;

        int gameover = 1;
        int score = 1;

        for (x = 0; x < tailleX; x++) {
            for (y = 0; y < tailleY; y++){
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


    public int updateGameState(int x, int y) {
        int xx, yy, emptyboard;
        int mode = getGameMode();
        int state = getGameState();

        // mode 1 joueur
        if (mode == 0) {
            switch (state) {
                case 0: // tableau non vide et non bloqué
                    if (getSelectedScore() > 0) { // Double click?
                        if (SelectedTableau[x][y] == 1) { // Le joueur click sur un block déjà selectionné.
                            Joueur1.setScore(Joueur1.getScore() + getSelectedScore()*1000);

                            skynet(); // On elimine ce block
                            CleanTabMemoire(); // On nettoie le tableau memoire
                            updateGame(); // On fait tomber les cases

                            varover = CheckGameOver();

                            if (varover == 1) { // game over
                                ; // Partie terminée
                            }

                            //
                        }
                        else { // Le joueur click sur un nouvel ensemble de block.
                            CleanTabMemoire();
                            TrouverVoisin(x, y);

                           if (getSelectedScore() == 1) // Si une case : on retire la selection.
                              CleanTabMemoire();
                        }
                    }

                    else { // Rien de selectionné

                        CleanTabMemoire();
                        TrouverVoisin(x, y);

                        if (getSelectedScore() == 1) // Si une case : on retire la selection.
                             CleanTabMemoire();

                        // Si aucune case, on genere un nouveau niveau
                        emptyboard = 1;
                        for (xx = 0; xx < tailleX; xx++) {
                            for (yy = 0; y < tailleY; yy++) {
                                if (GetCaseColor(xx, yy) != -1) {
                                    emptyboard = 0;
                                }
                            }
                        }

                        if (emptyboard == 1) { // On genere nouvelle board
                            setGameLevel(getGameLevel() + 1);
                            GenerationAleatoireTableau(getGameLevel());
                        }
                    }

                    // Mise à jour du game state
                    //

                    break;
                case 1:
                    ;
                    break;
                default:
                    setGameState(0);
                    break;
            }
        }

        // mode 2 joueurs
        else {
            switch (state) {
                case 0:
                    ;
                    break;
                case 1:
                    ;
                    break;
                default:
                    setGameState(0);
                    break;
            }
        }

        return getGameState();
    }

    public int getTailleX() {return tailleX;}
    public int getTailleY() {return tailleY;}

    public void setTailleX(int taille) {tailleX = taille;}
    public void setTailleY(int taille) {tailleY = taille;}

    public int getGameLevel() {return GameLevel;}
    public void setGameLevel(int GameLevel) {GameLevel = GameLevel;}

    public int getGameMode() {return GameMode;}
    public int getGameState() {return GameState;}

    public void setGameMode(int mode) {

        if (mode == 0 || mode == 1)
            GameMode = mode;
        else
            GameMode = 0;

        // Reset Tableau et elements de jeux
        Joueur1.setScore(0);
        //Joueur2.setScore(0);

        //setGameState(0);

        //GenerationAleatoireTableau(3);
        //CleanTabMemoire();
    }

    public void setGameState(int state) {GameState = state;}

    public int [][] getTableau() {return Tableau;}
    public int [][] getSelectedTableau() {return SelectedTableau;}

    public Game(int tailleX, int tailleY, int gameMode) { // Constructeur
        int x, y;

        setTailleX(tailleX);
        setTailleY(tailleY);

        Joueur1 = new Joueur("Joueur1");
        Joueur2 = new Joueur("Joueur2");

        setGameMode(gameMode);
        setGameState(0);



        selectedx = 0;
        selectedy = 0;

        Tableau = new int[getTailleX()][getTailleY()];
        SelectedTableau = new int[getTailleX()][getTailleY()];
        TableauMemoire = new int[getTailleX()][getTailleY()];


        setGameLevel(3);

        GenerationAleatoireTableau(getGameLevel());

        // Mise à zéro tableau selection
        for (x = 0; x < tailleX; x++) {
            for (y = 0; y < tailleY; y++) {
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
        boolean colonnevide;

        // GetCaseColor(x, y);
        // SetCaseColor(x, y, color);

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
