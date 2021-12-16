package main.bomberman.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.bomberman.board.BoardGame;
import main.bomberman.entities.tile.Brick;
import main.bomberman.entities.Entity;
import main.bomberman.entities.tile.Wall;

abstract public class AnimatedImage extends Entity {
    public Image[] frames;
    protected double duration = 0.10;
    protected double velocityX;
    protected double velocityY;
    protected double speed;

    public AnimatedImage(){
        super();
        velocityX = 0;
        velocityY = 0;
        speed = 200;
    }

    public void setFrame(Image[] listFrame){
        frames = listFrame;
    }

    public Image getFrame(double time) {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }

    public void update(double time) {
        if(canMove((int) (positionX + velocityX * time), (int)(positionY + velocityY * time))){
            positionX += velocityX * time;
            positionY += velocityY * time;
        }
    }

    public void render(GraphicsContext gc, double time) {
        gc.drawImage(getFrame(time), positionX, positionY);
    }

    public boolean canMove(int x, int y){
        x += width/3;
        y += height/3;

        Entity tren_trai = BoardGame.getEntityAt((x - width/4)/width, y/height);
        Entity tren_phai = BoardGame.getEntityAt((x + width/3)/width, y/height);
        Entity duoi_trai = BoardGame.getEntityAt((x - width/4)/width, (y+ 2*height/3)/height);
        Entity duoi_phai = BoardGame.getEntityAt((x + width/3)/width, (y+ 2*height/3)/height);
        if(tren_trai instanceof Wall || tren_phai instanceof Wall ||
            duoi_trai instanceof Wall || duoi_phai instanceof Wall){
            return false;
        }
        return (!(tren_trai instanceof Brick) || ((Brick) tren_trai).isDestroyed()) &&
                (!(tren_phai instanceof Brick) || ((Brick) tren_phai).isDestroyed()) &&
                (!(duoi_trai instanceof Brick) || ((Brick) duoi_trai).isDestroyed()) &&
                (!(duoi_phai instanceof Brick) || ((Brick) duoi_phai).isDestroyed());
    }
}
