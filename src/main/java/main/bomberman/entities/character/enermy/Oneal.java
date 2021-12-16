package main.bomberman.entities.character.enermy;

import main.bomberman.entities.character.enermy.ai.AIMedium;

public class Oneal extends Enemy {
    public Oneal(){
        brain = new AIMedium(this, bomber);
        setFrame("sprites\\oneal_left", "sprites\\oneal_left",
                "sprites\\oneal_right", "sprites\\oneal_right", 3);
        setAnimateDead("sprites\\oneal_dead", 1);
    }

    @Override
    public void calcMove() {
        super.calcMove();
        speed = 80 + random.nextInt(80) ;
    }
}
