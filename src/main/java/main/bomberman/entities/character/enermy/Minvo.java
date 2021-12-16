package main.bomberman.entities.character.enermy;

import main.bomberman.entities.character.enermy.ai.AIHigh;

import java.util.Random;

public class Minvo extends Enemy{
    public Minvo(){
        brain = new AIHigh(bomber, this);

        setFrame("sprites\\minvo_left", "sprites\\minvo_left",
                "sprites\\minvo_right", "sprites\\minvo_right", 3);
        setAnimateDead("sprites\\minvo_dead", 1);
        setPosition(864, 48);
        speed = 4;
    }

    @Override
    public void update(double time){
        if(!alive)
            return;

        if(this.intersects(bomber)){
            this.kill();
            bomber.kill();
        }

        if(!((AIHigh)brain).inTheDes()) {
            if (((AIHigh) brain).checkPos(positionX, positionY)) {
                setStatusMove(((AIHigh) brain).getNextDir());
            } else {
                positionX += velocityX;
                positionY += velocityY;
            }
        }
        else {
            if(!((AIHigh)brain).isThinking())
                ((AIHigh)brain).creatWay();
            else {
                if(!((AIHigh)brain).canSolve()){
                    Random random = new Random();
                    setStatusMove(random.nextInt(4));
                }
            }
        }
    }
}
