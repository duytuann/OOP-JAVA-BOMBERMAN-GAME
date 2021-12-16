package main.bomberman.entities.character.enermy;

import main.bomberman.entities.character.enermy.ai.AIHigh;

import java.util.Random;

public class Kondoria extends Enemy{
    private boolean isTheFirstStep = true;

    public Kondoria(){
        brain = new AIHigh(bomber, this);

        setFrame("sprites\\kondoria_left", "sprites\\kondoria_left",
                "sprites\\kondoria_right", "sprites\\kondoria_right", 3);
        setAnimateDead("sprites\\kondoria_dead", 1);
        setPosition(864, 48);
        speed = 3;
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
