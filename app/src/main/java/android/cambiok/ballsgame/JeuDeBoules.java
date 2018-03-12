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
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class JeuDeBoules extends AppCompatActivity {

    private Canvas testCanv;
    private Paint testPaint = new Paint();



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

            int taille = 100;
            int espacement = 107;

            int tailleX = 10;
            int tailleY = 10;

            for (j = 0, y = 5; j < tailleX; j++) {


                for (i = 0, x = 5; i < tailleY; i++) {



                    testPaint.setStyle(Paint.Style.FILL);
                    testPaint.setColor(Color.BLUE);
                    testCanv.drawRect(x, y, x+taille, y+taille, testPaint);
                    x = x + espacement;
                }

                y = y + espacement;
            }

            /*

            for(j = 0; j < 4; j++){

                //x = x + (i+200);
                y = y + 105;
                //w = w + (i+200);
                z = z + 105;
/*
                testPaint.setStyle(Paint.Style.FILL);
                testPaint.setColor(Color.BLUE);
                testCanv.drawRect(x,y,w,z, testPaint);

                for(j = 0; j < 4; j++){

                    x = x + 105;
                    //y = y + 105;
                    w = w + 105;
                    //z = z + 105;

                    testPaint.setStyle(Paint.Style.FILL);
                    testPaint.setColor(Color.GREEN);
                    testCanv.drawRect(x,y,w,z, testPaint);

                }

            }

            */


/*
            for(j = 0; j < 15; j++){

                x = x + 105;
                //y = y + 105;
                w = w + 105;
                //z = z + 105;

                testPaint.setStyle(Paint.Style.FILL);
                testPaint.setColor(Color.GREEN);
                testCanv.drawRect(x,y,w,z, testPaint);

            }*/

            }


    }

}
