package main.bomberman.entities.character;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import main.bomberman.Input.Input;
import main.bomberman.board.BoardGame;
import main.bomberman.entities.Entity;
import main.bomberman.entities.bomb.Bomb;
import main.bomberman.entities.tile.Brick;
import main.bomberman.entities.tile.Wall;
import main.bomberman.graphics.AnimatedCharacter;
import main.bomberman.graphics.Sprite;
import main.bomberman.sound.Sound;

import java.util.ArrayList;

public class Bomber extends AnimatedCharacter {
    private ArrayList<Bomb> listBomb = new ArrayList<>();
    private Input input;
    private int timeToPlaceNextBomb = 0;
    private int numberBomb = 2;
    private int powerFlames = 0;
    private int selectCharacter = 1; //0: mac dinh
    private boolean hasSpaceShip = false;
    private int noPlayer = 1;

    public Bomber(int select, int no) {
        setScale(3);
        noPlayer = no;
        input = new Input(no);
        this.selectCharacter = select;
        framesMove = Sprite.getListImage("sprites\\jetter" + selectCharacter +".png", 4, 3, scale);
        setAnimateDead("sprites\\player_dead", 3);
        setPosition(44, 40);
        alive = true;
    }

    public void readInput(){
        setVelocity(0,0);
        isRunning = false;
        if (input.up()){
            setStatusMove("UP");
        }
        else if (input.down()) {
            setStatusMove("DOWN");
        }
        else if (input.left()){
            setStatusMove("LEFT");
        }
        else if (input.right()){
            setStatusMove("RIGHT");
        }
        if(numberBomb > listBomb.size() && input.placeBomb() && timeToPlaceNextBomb < 0){
            listBomb.add(new Bomb(this, powerFlames));
            timeToPlaceNextBomb = 20;
        }
    }

    @Override
    public void update(double time){
        readInput();
        timeToPlaceNextBomb--;

        super.update(time);

        if(listBomb.size() > 0) {
            for(int i = 0; i < listBomb.size(); i++) {
                Bomb bomb = listBomb.get(i);
                bomb.update();
                if (bomb.isDestroyed()) {
                    listBomb.remove(bomb);
                    i--;
                }
            }
        }
    }

    @Override
    public boolean canMove(int x, int y){
        //toa do se den
        x += width/3;
        y += height/3;

        Entity tren_trai = BoardGame.getEntityAt((x - width/4)/width, (y + height/5)/height);
        Entity tren_phai = BoardGame.getEntityAt((x + width/3)/width, (y + height/5)/height);
        Entity duoi_trai = BoardGame.getEntityAt((x - width/4)/width, (y+ 8*height/10)/height);
        Entity duoi_phai = BoardGame.getEntityAt((x + width/3)/width, (y+ 8*height/10)/height);

        if(tren_trai instanceof Wall || tren_phai instanceof Wall ||
                duoi_trai instanceof Wall || duoi_phai instanceof Wall){
            return false;
        }

        Brick brick = null;
        if(tren_trai instanceof Brick){
            brick = (Brick) tren_trai;
            if(!brick.isDestroyed()){
                return false;
            }
            else if(brick.isPortal()){
                if( BoardGame.getListEnemy().size() == 0) {
                    System.out.println("next level");
                    BoardGame.nextLevel();
                    return true;
                }
                else return false;
            }
            else if(brick.hasItem()){
                brick.setCollectedItem(brick.getItem().getProperties(this));
            }
        }
        if(tren_phai instanceof Brick){
            brick = (Brick) tren_phai;
            if(!brick.isDestroyed()){
                return false;
            }
            else if(brick.isPortal()){
                if( BoardGame.getListEnemy().size() == 0) {
                    System.out.println("next level");
                    BoardGame.nextLevel();
                    return true;
                }
                else return false;
            }
            else if(brick.hasItem()){
                brick.setCollectedItem(brick.getItem().getProperties(this));
            }
        }
        if(duoi_trai instanceof Brick){
            brick = (Brick) duoi_trai;
            if(!brick.isDestroyed()){
                return false;
            }
            else if(brick.isPortal()){
                if( BoardGame.getListEnemy().size() == 0) {
                    System.out.println("next level");
                    BoardGame.nextLevel();
                    return true;
                }
                else return false;
            }
            else if(brick.hasItem()){
                brick.setCollectedItem(brick.getItem().getProperties(this));
            }
        }
        if(duoi_phai instanceof Brick){
            brick = (Brick) duoi_phai;
            if(!brick.isDestroyed()){
                return false;
            }
            else if(brick.isPortal()){
                if( BoardGame.getListEnemy().size() == 0) {
                    System.out.println("next level");
                    BoardGame.nextLevel();
                    return true;
                }
                else return false;
            }
            else if(brick.hasItem()){
                brick.setCollectedItem(brick.getItem().getProperties(this));
            }
        }

        return true;
    }

    public void render(GraphicsContext gc, double time){
//        super.render(gc, time);

        if (alive) {
            if(isRunning) {
                gc.drawImage(getFrame(time, framesMove[statusMove]), positionX, positionY, 44, 55);
            }
            else gc.drawImage(framesMove[statusMove][0], positionX, positionY, 44, 55);
        } else if (lastTime < dead.length * 10) {
            gc.drawImage(dead[lastTime / 10], positionX, positionY);
            lastTime++;
        }

        if(listBomb.size() > 0) {
            for (Bomb bomb : listBomb) {
                bomb.render(gc, time);
            }
        }
    }

    public int getNumberBomb(){
        return numberBomb;
    }

    public int getPowerFlames(){
        return powerFlames;
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX + width/5, positionY + height/5, width/2, height/2);
    }

    public Rectangle2D getBoundaryImage(){
        return new Rectangle2D(positionX, positionY, width, height);
    }

    @Override
    public void kill(){
        if(hasSpaceShip){
            framesMove = Sprite.getListImage("sprites\\jetter" + selectCharacter +".png", 4, 3, scale);
            hasSpaceShip = false;
        }
        else {
            Sound.playSound(Sound.bomberDie);
            BoardGame.setGameOver(true);
            alive = false;
        }
    }

    public int getSelectCharacter(){
        return selectCharacter;
    }

    public void upPowerFlames(int n){
        powerFlames += n;
    }

    public void upNumBomb(int n){
        numberBomb += n;
    }

    public void setHasSpaceShip(boolean ok){
        hasSpaceShip = ok;
        if(ok)
            framesMove = Sprite.getListImage("sprites\\specialMode" + selectCharacter + ".png", 4, 2, scale);
    }
}
