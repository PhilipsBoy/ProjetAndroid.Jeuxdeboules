// test
// jjh

package android.cambiok.ballsgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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




public class JeuDeBoules extends AppCompatActivity {

    private Canvas testCanv;
    private Paint testPaint = new Paint();
    private Paint paintcoord = new Paint();
    float initialX, initialY;

    int xx, yy;
    int xx_tmp, yy_tmp;

    int taille = 100;

    Game GameEngine = new Game(13, 10, 0);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));

    }

    public class MyView extends View {
        Paint paint = null;




        public MyView(Context context) {
            super(context);
            paint = new Paint();
        }

        public boolean onTouchEvent (MotionEvent event) {

            int action = event.getAction();



            switch (action) {

             case(MotionEvent.ACTION_DOWN):

                    initialX = event.getX();
                    initialY = event.getY();

                    xx_tmp = (int) initialX / 107;
                    yy_tmp = (int) initialY / 107;

                    if (xx_tmp >= 0 && xx_tmp < 10 && yy_tmp >= 0 && yy_tmp < 13) {

                        if (GameEngine.getSelectedScore() > 0) { // Si déjà selectionné
                            if (GameEngine.SelectedTableau[yy_tmp][xx_tmp] == 1) {
                                xx = xx_tmp;
                                yy = yy_tmp;

                                GameEngine.skynet();
                                GameEngine.CleanTabMemoire();
                                GameEngine.updateGame();

                            }
                            else {
                                xx = xx_tmp;
                                yy = yy_tmp;

                                GameEngine.CleanTabMemoire();
                                GameEngine.TrouverVoisin(yy, xx);
                            }
                        }

                        else { // Sinon, rien de selectionné
                            xx = xx_tmp;
                            yy = yy_tmp;

                            GameEngine.CleanTabMemoire();
                            GameEngine.TrouverVoisin(yy, xx);
                            //GameEngine.skynet();
                        }




                    }






                 Log.d("MYINT", "value: " + xx);
                 Log.d("MYINT2", "value: " + yy);

                    break;

            }
            invalidate();
            return true;
        }



        protected void onDraw(Canvas testCanv) {
            super.onDraw(testCanv);
            int x = 0;
            int y = 0;
            int w = 95;
            int z = 95;
            int i, j;
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            testCanv.drawPaint(paint);
            // Use Color.parseColor to define HTML colors
            //paint.setStrokeWidth(10);
            //paint.setStyle(Paint.Style.STROKE);
            //paint.setColor(Color.BLACK);


            // x = left
            // y = top
            // w = right
            // z = bot

            int tab [][]= GameEngine.getTableau();
            int Selectedtab [][]= GameEngine.getSelectedTableau();


            int espacement = 107;



            for (j = 0, y = 5; j < GameEngine.getTailleX(); j++) {


                for (i = 0, x = 5; i < GameEngine.getTailleY(); i++) {



                    testPaint.setStyle(Paint.Style.FILL);

                    if (tab[j][i] == 0) {
                        testPaint.setColor(Color.GREEN);
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
                        //testPaint.setColor(Color.GREEN);
                    }

                    if (tab[j][i] != -1) {
                        if (Selectedtab[j][i] == 1) {
                            // selectionné
                            testPaint.setColor(Color.BLACK);
                            testCanv.drawRect(x, y, x + taille, y + taille, testPaint);
                        } else {
                            // non selectionné
                            testCanv.drawRect(x, y, x + taille, y + taille, testPaint);
                        }
                    }


                    x = x + espacement;
                }

                y = y + espacement;
            }

            /*

            paintcoord.setStyle(Paint.Style.FILL);
            paintcoord.setColor(Color.BLACK);
            testCanv.drawRect(initialX, initialY, initialX+taille, initialY+taille, paintcoord);
            //testCanv.drawRect(x, y, x+taille, y+taille, testPaint);
            */


        }


    }

}
