package carlos.demo.com.beacoin;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
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
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

public class MainActivity extends Activity {
    public static float W,H;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(new CanvasView(this));

        //fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    //canvas
    public class CanvasView extends View{
        private Paint pincel;
        private ArrayList<Ad> ads=new ArrayList();
        private boolean executed=false;
        private Canvas canvas;
        private float x,y;

        //constructor
        public CanvasView(Context context){
            super(context);
            Resources res = context.getResources();

            pincel = new Paint();
            pincel.setColor(Color.BLACK);
            pincel.setStrokeWidth(3);
            pincel.setStyle(Paint.Style.STROKE);

            x=getWidth()/2;
            y=getHeight()/2;
            //añadir las imagenes
            ads.add(new Ad(res.getDrawable(R.drawable.image)));
            //ads.add(new Ad(BitmapFactory.decodeResource(context.getResources(), R.drawable.image)));

        }




        @Override //lienzo donde dibujar el canvas
        protected void onDraw(Canvas canvas) {
            this.canvas=canvas;


            if(ads.get(0).isColliding(x,y)) {
                ads.get(0).dimensions((float) (this.x - ((canvas.getWidth()) * 0.9) / 2), (float) (this.y - ((canvas.getHeight()) * 0.9) / 2),
                        (float) (this.x + ((canvas.getWidth()) * 0.9) / 2), (float) (this.y + ((canvas.getHeight()) * 0.9) / 2),
                        (float)  ( (x*100)/(canvas.getWidth()*0.2) ));//rotation
                //set opacity effect
                if(ads.get(0).getX()<0) {
                    ads.get(0).setOpacity(
                      100  -  (int)(  (-1*ads.get(0).getX()*100)/(canvas.getWidth()*0.5) )
                    );
                }
                     else {
                    ads.get(0).setOpacity(110);
                }

            }

            if(!executed)
                once();

            ads.get(0).draw(canvas, pincel);
            //canvas.save();
            //  canvas.rotate( (float)  ((x*100)/canvas.getWidth()*0.2),canvas.getWidth()/2,canvas.getHeight());
            //canvas.restore();

        }

        //solo se ejecutará una vez
        private void once(){
            W=canvas.getWidth();
            H=canvas.getHeight();
            x=canvas.getWidth()/2;
            y=canvas.getHeight()/2;
            ads.get(0).dimensions((float) (this.x - ((canvas.getWidth()) * 0.9) / 2), (float) (this.y - ((canvas.getHeight()) * 0.9) / 2),
                                  (float) (this.x + ((canvas.getWidth()) * 0.9) / 2), (float) (this.y + ((canvas.getHeight()) * 0.9)/2),0);
            executed=true;
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            x=(int)event.getX();
            y=(int)event.getY();
            if(x<0)
                x=0;
            if(x> canvas.getWidth())
                x= canvas.getWidth();
            if(y <0)
                y=0;
            if(y > canvas.getHeight())
                y= canvas.getHeight();
            invalidate();

            return true;
        }



    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
