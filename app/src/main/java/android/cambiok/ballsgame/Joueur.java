package android.cambiok.ballsgame;

/**
 * Created by bat on 07/03/2018.
 */

// Classe joueur
// Permet de décrire un joueur
// Name : pseudo du joueur.
// Score : score du joueur.
// ScoreLevel : score du joueur en entrée de lvl.
public class Joueur {
    // Variables
    int score;
    int scoreLevel;
    String Name;

    // Constructeur
    public Joueur(String name) {
        setScore(0);
        setScoreLevel(0);
        setName(name);
    }

    // get / set
    int getScore() {return score;}
    void setScore(int scoreJoueur) {score = scoreJoueur;}

    int getScoreLevel() {return scoreLevel;}
    void setScoreLevel(int scoreJoueur) {scoreLevel = scoreJoueur;}

    String getName() {return Name;}
    void setName(String JoueurName) {Name = JoueurName;}
}
