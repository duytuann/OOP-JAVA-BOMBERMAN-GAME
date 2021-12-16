package main.bomberman.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.bomberman.board.BoardGame;
import main.bomberman.entities.Entity;
import main.bomberman.entities.tile.Brick;
import main.bomberman.entities.tile.Wall;

abstract public class AnimatedCharacter extends Entity {
    protected double duration = 0.10;
    protected double velocityX;
    protected double velocityY;
    protected double speed;
    protected Image[][] framesMove = new Image[4][];
    protected Image[] dead;
    protected boolean isRunning;
    protected boolean alive = true;
    protected int statusMove = 0; //0: down, 1: left, 2: right, 3: up
    protected int lastTime = 0;

    public AnimatedCharacter(){
        super();
        velocityX = 0;
        velocityY = 0;
        speed = 200;
    }

    public void setFrame(String down, String left, String right, String up, int _number) {
        for (int i = 0; i < _number; i++)
            framesMove[0] = Sprite.getListImage(down, _number, scale);
        for (int i = 0; i < _number; i++)
            framesMove[1] = Sprite.getListImage(left, _number, scale);
        for (int i = 0; i < _number; i++)
            framesMove[2] = Sprite.getListImage(right, _number, scale);
        for (int i = 0; i < _number; i++)
            framesMove[3] = Sprite.getListImage(up, _number, scale);
    }

    public void setAnimateDead(String dead, int _number){
        this.dead = Sprite.getListImage(dead, _number, scale);
    }

    public Image getFrame(double time, Image[] frames){
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }

    public void setStatusMove(String statusMove){
        setVelocity(0, 0);
        switch (statusMove) {
            case "DOWN" -> {
                this.statusMove = 0;
                velocityY += speed;
                isRunning = true;
            }
            case "LEFT" -> {
                this.statusMove = 1;
                velocityX -= speed;
                isRunning = true;
            }
            case "RIGHT" -> {
                this.statusMove = 2;
                velocityX += speed;
                isRunning = true;
            }
            case "UP" -> {
                this.statusMove = 3;
                velocityY -= speed;
                isRunning = true;
            }
            default -> isRunning = false;
        }
    }

    public void setStatusMove(int statusMove){
        isRunning = true;
        setVelocity(0, 0);
        switch (statusMove) {
            case 0 -> velocityY += speed;
            case 1 -> velocityX -= speed;
            case 2 -> velocityX += speed;
            case 3 -> velocityY -= speed;
            default -> isRunning = false;
        }
        if(isRunning)
            this.statusMove = statusMove;
    }
    public void update(double time) {
        if(!alive)
            return;
        if(canMove((int) (positionX + velocityX * time), (int)(positionY + velocityY * time))){
            positionX += velocityX * time;
            positionY += velocityY * time;
        }
    }

    public void render(GraphicsContext gc, double time) {
        if (alive) {
            if(isRunning) {
                gc.drawImage(getFrame(time, framesMove[statusMove]), positionX, positionY);
            }
            else gc.drawImage(framesMove[statusMove][0], positionX, positionY);
        } else if (lastTime < dead.length * 10) {
            gc.drawImage(dead[lastTime / 10], positionX, positionY);
            lastTime++;
        }
    }

    public boolean canMove(int x, int y){
        x += width/3;
        y += height/3;

        Entity tren_trai = BoardGame.getEntityAt((x - width/4)/width, y/height);
        Entity tren_phai = BoardGame.getEntityAt((x + width/2)/width, y/height);
        Entity duoi_trai = BoardGame.getEntityAt((x - width/4)/width, (y+ 2*height/3)/height);
        Entity duoi_phai = BoardGame.getEntityAt((x + width/2)/width, (y+ 2*height/3)/height);
        if(tren_trai instanceof Wall || tren_phai instanceof Wall ||
                duoi_trai instanceof Wall || duoi_phai instanceof Wall){
            return false;
        }
        if((tren_trai instanceof Brick && !((Brick) tren_trai).isDestroyed())||
                (tren_phai instanceof Brick && !((Brick) tren_phai).isDestroyed())||
                (duoi_trai instanceof Brick && !((Brick) duoi_trai).isDestroyed())||
                (duoi_phai instanceof Brick && !((Brick) duoi_phai).isDestroyed())){
            return false;
        }

        return true;
    }

    public void kill(){
        alive = false;
    }

    public boolean isKilled(){
        return !alive;
    }

    public double getSpeed(){
        return speed;
    }

    public void setSpeed(double s){
        speed = s;
    }
}
