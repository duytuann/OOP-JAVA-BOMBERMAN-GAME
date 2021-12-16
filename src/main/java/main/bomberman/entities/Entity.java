package main.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.bomberman.graphics.GetImage;
import main.bomberman.graphics.Sprite;


public abstract class Entity {
    protected int positionX;
    protected int positionY;

    protected int width = 48;
    protected int height = 48;

    protected Image img;

    protected int scale = 1;

    public Entity() {
        positionX = 0;
        positionY = 0;
    }

    public void setPosition(int x, int y){
        positionX = x;
        positionY = y;
    }

    public int getPositionX(){
        return positionX;
    }

    public int getPositionY(){
        return positionY;
    }

    public void setSize(int w, int h){
        width = w;
        height = h;
    }

    public void setImg(String name){
        Image im = new Image(GetImage.get(name));
        img = Sprite.readImage(im, 0, 0, (int)im.getWidth(), (int)im.getHeight(), scale);
        width = (int)im.getWidth()*scale;
        height = (int)im.getHeight()*scale;
    }

    public void setImg(String name, int scale){
        Image im = new Image(GetImage.get(name));
        //img = Sprite.readImage(im, 0, 0, (int)im.getWidth(), (int)im.getHeight(), scale);
        img = im;
        width = (int)im.getWidth()*scale;
        height = (int)im.getHeight()*scale;
    }

    public void setScale(int scale){
        this.scale = scale;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Entity s) {
        return s.getBoundary().intersects( this.getBoundary() );
    }

    public boolean intersects(int x, int y, int w, int h){
        Rectangle2D rectangle2D = new Rectangle2D(x, y, w, h);
        return this.getBoundary().intersects(rectangle2D);
    }

    public boolean intersects(Rectangle2D r){
        return r.intersects(this.getBoundary());
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, positionX, positionY);
    }
}
