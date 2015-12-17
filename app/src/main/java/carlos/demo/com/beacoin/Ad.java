package carlos.demo.com.beacoin;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Created by Carlos on 16/12/2015.
 */
public class Ad{
    private Drawable img=null;
    private Bitmap image;
    private float angle;
    private float x,y, width,height;


    public Ad(Drawable img,float x,float y, float width,float height){
        this.img= img;
        this.x=x;
        this.y=y;
        this.angle=0;
        this.width=width;
        this.height=height;
    }

    public Ad(Drawable img){
        this.img= img;this.angle=0;
    }

    public Ad(Bitmap img){
        this.image= img;this.angle=0;
    }

    public boolean isColliding(float otherX,float otherY){
        if( (otherX > x   && otherX <width) && (otherY > y   && otherY <height))
            return true;
        return false;
    }

    public void dimensions(float x,float y, float width,float height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    public void dimensions(float x,float y, float width,float height,float angle){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.angle=angle;
    }

    //set opacity to the image
    public void setOpacity(int opacity){
        img.setAlpha(opacity);
    }

    //rotación
    public void rotate(Canvas canvas){
        //canvas.rotate(angle, width / 2, height);
        //canvas.rotate(angle, 0, 0);
        canvas.rotate(angle, MainActivity.W/2, MainActivity.H);
    }

    //draw the ad on a canvas
    public void draw(Canvas canvas){
        if(img!=null){
            img.setBounds((int) x, (int) y, (int) width, (int) height);
            img.draw(canvas);
        }else
            canvas.drawBitmap(image,x,y,null);

    }


    public void draw(Canvas canvas,Paint pincel){
        canvas.save();
            rotate(canvas);
            this.draw(canvas);
            canvas.drawRect(x, y, width, height, pincel);
        canvas.restore();
    }

    //setters an getters
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width-x;
    }

    public void setWidth(float width) {
        this.width = x + width;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public float getHeight() {
        return height-y;
    }

    public void setHeight(float height) {
        this.height = y+height;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }
}
