package main.bomberman.entities.tile;

import main.bomberman.entities.Entity;
import main.bomberman.level.LoadLevel;

public class Wall extends Entity {
    public Wall(){
        setImg("sprites\\map" + (LoadLevel.get_level()%3 + 1) +"\\wall.png", 1);
    }
    public Wall(int x, int y) {
        setPosition(x, y);
        setImg("sprites\\map" + (LoadLevel.get_level()%3 + 1) +"\\wall.png", 1);
    }
}
