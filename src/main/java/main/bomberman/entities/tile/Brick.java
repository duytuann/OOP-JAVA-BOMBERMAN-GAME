package main.bomberman.entities.tile;

import javafx.scene.canvas.GraphicsContext;
import main.bomberman.graphics.AnimatedImage;
import main.bomberman.graphics.Sprite;
import main.bomberman.level.LoadLevel;

public class Brick extends AnimatedImage {
    private boolean isDestroyed;
    private boolean hasItem = false;
    private boolean collectedItem = false;
    private boolean isPortal = false;
    private boolean showPortal = false;
    private Item item;
    private int timeOff = 0;

    public Brick(){
        isDestroyed = false;
        setImg("sprites\\map" + (LoadLevel.get_level()%3+1) +"\\brick.png", 1);
        setFrame(Sprite.getListImage("sprites\\brick_exploded", 3, 1, false));
    }

    public Brick(String item){
        isDestroyed = false;
        hasItem = true;
        if(item.equals("portal")){
            isPortal = true;
        }
        setImg("sprites\\map" + (LoadLevel.get_level()%3+1) +"\\brick.png", 1);
        setFrame(Sprite.getListImage("sprites\\brick_exploded", 3, 1, false));
        this.item = new Item(item);
    }

    public Brick(int x, int y){
        isDestroyed = false;
        setImg("sprites\\map" + (LoadLevel.get_level()%3+1) +"\\brick.png", 1);
        setPosition(x, y);
        setFrame(Sprite.getListImage("sprites\\brick_exploded", 3, 1, false));
    }

    @Override
    public void setPosition(int x, int y){
        positionX = x;
        positionY = y;
        if(hasItem){
            item.setPosition(x, y);
        }
    }

    public boolean isDestroyed(){
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed){
        isDestroyed = destroyed;
        setImg("sprites\\map" + (LoadLevel.get_level()%3+1) + "\\grass.png", 1);
    }

    public boolean hasItem(){
        return hasItem && !collectedItem && !isPortal;
    }

    public void setCollectedItem(boolean collectedItem){
        if(isPortal)
            return;
        this.collectedItem = collectedItem;
    }

    public Item getItem(){
        return item;
    }

    @Override
    public void render(GraphicsContext gc, double time){
        if(isDestroyed && timeOff++ < frames.length * 5){
            gc.drawImage(getFrame(timeOff/5), positionX, positionY);
        }
        else if(isDestroyed && (hasItem && !collectedItem || isPortal)){
            super.render(gc);
            if(isPortal) {
                item.render(gc, time);
            }
            else
                item.render(gc);
        }
        else {
            super.render(gc);
        }
    }

    public boolean isPortal(){
        return isPortal;
    }
}
