package android.cambiok.ballsgame;

/**
 * Created by bat on 07/03/2018.
 */

public class Joueur {
    int score;
    int scoreLevel;
    String Name;

    public Joueur(String name) { // Constructeur
        setScore(0);
        setScoreLevel(0);
        setName(name);
    }

    int getScore() {return score;}
    void setScore(int scoreJoueur) {score = scoreJoueur;}

    int getScoreLevel() {return scoreLevel;}
    void setScoreLevel(int scoreJoueur) {scoreLevel = scoreJoueur;}

    String getName() {return Name;}
    void setName(String JoueurName) {Name = JoueurName;}
}
