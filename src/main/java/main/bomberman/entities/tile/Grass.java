package main.bomberman.entities.tile;

import main.bomberman.entities.Entity;
import main.bomberman.level.LoadLevel;

public class Grass extends Entity {
    public Grass(){
        setImg("sprites\\map" + (LoadLevel.get_level()%3+1) +"\\grass.png", 1);
    }
    public Grass(int x, int y) {
        setPosition(x, y);
        setImg("sprites\\map" + (LoadLevel.get_level()%3+1) +"\\grass.png", 1);
    }

}
