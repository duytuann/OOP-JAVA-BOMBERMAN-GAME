package main.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.bomberman.board.BoardGame;
import main.bomberman.entities.Entity;
import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enermy.Enemy;
import main.bomberman.entities.tile.Brick;
import main.bomberman.entities.tile.Wall;
import main.bomberman.graphics.AnimatedImage;
import main.bomberman.graphics.Sprite;
import main.bomberman.sound.Sound;

import java.util.ArrayList;

public class Flame extends AnimatedImage {
    private Image[] center;

    private Image[][] verticalUp; //doc
    private Image[][] verticalDown;
    private Image[][] horizontalLeft;//ngang
    private Image[][] horizontalRight;

    private int numUp;
    private int numDown;
    private int numLeft;
    private int numRight;

    private int showed = 20;//hien trong 20 ms
    private int length = 0;

    private int color = 1; //1: yellow 2:blue 3: green

    public Flame(Bomber bomber, int x, int y, int length){
        duration = 0.2;
        positionX = x;
        positionY = y;
        this.length = length;
        numUp = length + 1;
        numDown = length + 1;
        numLeft = length + 1;
        numRight = length + 1;
        color = bomber.getSelectCharacter();
        setScale(3);
        calcDestroy(bomber);
        loadFrame();
        Sound.playSound(Sound.explore);
    }

    private void calcDestroy(Bomber bomber){
        ArrayList<Enemy> listE = BoardGame.getListEnemy();
        int centreX = positionX/width;
        int centreY = positionY/height;

        for(int i = centreY + 1; i < centreY + 2 + length; i++){
            Entity e = BoardGame.getEntityAt(centreX, i);
            if(e instanceof Wall){
                numDown = i - centreY - 1;
                break;
            }
            if(e instanceof Brick && !((Brick) e).isDestroyed()){
                ((Brick) e).setDestroyed(true);
                numDown = i - centreY - 1;
                break;
            }
            for(Enemy enemy : listE){
                if(enemy.intersects(positionX, i*height, width, height)){
                    enemy.kill();
                }
            }
            if(bomber.intersects(positionX, i*height, width, height)){
                bomber.kill();
            }
        }
        for(int i = centreY - 1; i >= centreY - 1 - length; i--){
            Entity e = BoardGame.getEntityAt(centreX, i);
            if(e instanceof Wall){
                numUp = centreY - i - 1;
                break;
            }
            if(e instanceof Brick && !((Brick) e).isDestroyed()){
                ((Brick) e).setDestroyed(true);
                numUp = centreY - i - 1;
                break;
            }
            for(Enemy enemy : listE){
                if(enemy.intersects(positionX, i*height, width, height)){
                    enemy.kill();
                }
            }
            if(bomber.intersects(positionX, i*height, width, height)){
                bomber.kill();
            }
        }
        for(int i = centreX + 1; i < centreX + 2 + length; i++){
            Entity e = BoardGame.getEntityAt(i, centreY);
            if(e instanceof Wall){
                numRight = i - centreX - 1;
                break;
            }
            if(e instanceof Brick && !((Brick) e).isDestroyed()){
                ((Brick) e).setDestroyed(true);
                numRight = i - centreX - 1;
                break;
            }
            for(Enemy enemy : listE){
                if(enemy.intersects(i*width, positionY, width, height)){
                    enemy.kill();
                }
            }
            if(bomber.intersects(i*width, positionY, width, height)){
                bomber.kill();
            }
        }
        for(int i = centreX - 1; i >= centreX - 1 - length; i--){
            Entity e = BoardGame.getEntityAt(i, centreY);
            if(e instanceof  Wall){
                numLeft = centreX - i - 1;
                break;
            }
            if(e instanceof Brick && !((Brick) e).isDestroyed()){
                ((Brick) e).setDestroyed(true);
                numLeft = centreX - i - 1;
                break;
            }
            for(Enemy enemy : listE){
                if(enemy.intersects(i*width, positionY, width, height)){
                    enemy.kill();
                }
            }
            if(bomber.intersects(i*width, positionY, width, height)){
                bomber.kill();
            }
        }
    }

    public boolean isShowed(){
        return showed == 0;
    }

    public Image getFrame(Image[] im, double time){
        int index = (int)((time % (im.length * duration)) / duration);
        return im[index];
    }

    @Override
    public void render(GraphicsContext gc, double time) {
        if(--showed > 0) {
            gc.drawImage(getFrame(center, time), positionX, positionY);

            for(int i = 0; i < numLeft; i++){
                gc.drawImage(getFrame(horizontalLeft[i], time), positionX - width*(i+1), positionY);
            }
            for(int i = 0; i < numRight; i++){
                gc.drawImage(getFrame(horizontalRight[i], time), positionX + width*(i+1), positionY);
            }
            for(int i = 0; i < numUp; i++){
                gc.drawImage(getFrame(verticalUp[i], time), positionX, positionY - height*(i+1));
            }
            for(int i = 0; i < numDown; i++){
                gc.drawImage(getFrame(verticalDown[i], time), positionX, positionY + height*(i+1));
            }
        }
    }

    private void loadFrame() {
        center = Sprite.getListImage("sprites\\flame" +color + "\\bomb_exploded", 3, scale);

        if(numDown > 0){
            verticalDown = new Image[numDown][];
            for(int i = 0; i < numDown - 1; i++){
                verticalDown[i] = Sprite.getListImage("sprites\\flame" +color + "\\explosion_vertical", 3, scale);
            }
            verticalDown[numDown-1] = Sprite.getListImage("sprites\\flame" +color + "\\explosion_vertical_down_last", 3, scale);
        }

        if(numUp > 0){
            verticalUp = new Image[numUp][];
            for(int i = 0; i < numUp - 1; i++){
                verticalUp[i] = Sprite.getListImage("sprites\\flame" +color + "\\explosion_vertical", 3, scale);
            }
            verticalUp[numUp-1] = Sprite.getListImage("sprites\\flame" +color + "\\explosion_vertical_top_last", 3, scale);
        }

        if(numRight > 0){
            horizontalRight = new Image[numRight][];
            for(int i = 0; i < numRight - 1; i++){
                horizontalRight[i] = Sprite.getListImage("sprites\\flame" +color + "\\explosion_horizontal", 3, scale);
            }
            horizontalRight[numRight-1] = Sprite.getListImage("sprites\\flame" +color + "\\explosion_horizontal_right_last", 3, scale);
        }
        if(numLeft > 0){
            horizontalLeft = new Image[numLeft][];
            for(int i = 0; i < numLeft - 1; i++){
                horizontalLeft[i] = Sprite.getListImage("sprites\\flame" + color + "\\explosion_horizontal", 3, scale);
            }
            horizontalLeft[numLeft-1] = Sprite.getListImage("sprites\\flame" + color + "\\explosion_horizontal_left_last", 3, scale);
        }

    }
}
