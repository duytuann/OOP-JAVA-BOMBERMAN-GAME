package main.bomberman.entities.tile;

import main.bomberman.entities.character.Bomber;
import main.bomberman.graphics.AnimatedImage;
import main.bomberman.graphics.Sprite;
import main.bomberman.sound.Sound;

public class Item extends AnimatedImage {
    private String name = "";

    public Item(String name){
        this.name = name;
        switch (name) {
            case "speed" -> setImg("sprites\\powerup_speed.png");
            case "flames" -> setImg("sprites\\powerup_flames.png");
            case "bomb" -> setImg("sprites\\powerup_bombs.png");
            case "portal" -> setFrame(Sprite.getListImage("sprites\\portal", 4, 1));
            case "spaceShip" -> setImg("sprites\\spaceShip.png");
        }
    }

    public boolean getProperties(Bomber bomber){
        Sound.playSound(Sound.itemCollected);
        switch (name) {
            case "speed":
                bomber.setSpeed(bomber.getSpeed() * 1.2);
                break;
            case "flames":
                bomber.upPowerFlames(1);
                break;
            case "bomb":
                bomber.upNumBomb(1);
                break;
            case "spaceShip":
                bomber.setHasSpaceShip(true);
                break;
        }
        return true;
    }

}
