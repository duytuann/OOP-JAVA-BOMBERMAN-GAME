package main.bomberman.entities.character.enermy;

import main.bomberman.entities.character.enermy.ai.AIRandom;

public class Balloon extends Enemy {
    public Balloon(){
        brain = new AIRandom();

        setFrame("sprites\\balloom_left", "sprites\\balloom_left",
                "sprites\\balloom_right", "sprites\\balloom_right", 3);
        setAnimateDead("sprites\\balloom_dead", 1);
    }

}
