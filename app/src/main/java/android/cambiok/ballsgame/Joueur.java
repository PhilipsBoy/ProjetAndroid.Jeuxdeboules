package android.cambiok.ballsgame;

/**
 * Created by bat on 07/03/2018.
 */

public class Joueur {
    int score;
    String Name;


    int getScore() {
        return score;
    }

    void setScore(int scoreJoueur) {
        score =  scoreJoueur;
    }

    String getName() {
        return Name;
    }

    void setName(String JoueurName) {
        Name =  JoueurName;
    }
}
