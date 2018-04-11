package android.cambiok.ballsgame;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * Created by bat on 07/04/2018.
 */

public class MeilleurScore {
    String Top1;
    int Score1;



    public MeilleurScore() { // Constructeur

        //writeToFile("ff", this);




        //File path = context.getFilesDir();
    }

    // Ajout score à la liste
    // Retourne la position
    // Retourne -1 si pas dans le top10
    public int AddScore(String Name, int Score) {
        return 0;
    }

    String getName() {return Top1;}
    void setName(String Namee) {Top1 = Namee;}

    int getScore1() {return Score1;}
    void setScore1(int Namee) {Score1 = Namee;}
}
