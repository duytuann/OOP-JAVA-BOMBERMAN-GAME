package main.bomberman.entities.character.enermy;

import main.bomberman.entities.character.enermy.ai.AIRandom;

public class Doll extends Enemy{
    public Doll(){
    	brain = new AIRandom();

        setFrame("sprites\\doll_left", "sprites\\doll_left",
                "sprites\\doll_right", "sprites\\doll_right", 3);
        setAnimateDead("sprites\\doll_dead", 1);
    }
}
