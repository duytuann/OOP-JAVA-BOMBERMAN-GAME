package main.bomberman.entities.character.enermy;

import java.util.Random;

import main.bomberman.entities.character.enermy.ai.AIHigh;

public class Oneal extends Enemy {
	public Oneal() {
		brain = new AIHigh(bomber, this);
		setFrame("sprites\\oneal_left", "sprites\\oneal_left", "sprites\\oneal_right", "sprites\\oneal_right", 3);
		setAnimateDead("sprites\\oneal_dead", 1);
		speed = 3;
	}

//    @Override
//    public void calcMove() {
//        super.calcMove();
//        speed = 80 + random.nextInt(80) ;
//    }

	@Override
	public void update(double time) {
		if (!alive)
			return;

		if (this.intersects(bomber)) {
			this.kill();
			bomber.kill();
		}

		if (!((AIHigh) brain).inTheDes()) {
			if (((AIHigh) brain).checkPos(positionX, positionY)) {
				setStatusMove(((AIHigh) brain).getNextDir());
			} else {
				positionX += velocityX;
				positionY += velocityY;
			}
		} else {
			if (!((AIHigh) brain).isThinking())
				((AIHigh) brain).creatWay();
			else {
				if (!((AIHigh) brain).canSolve()) {
					Random random = new Random();
					setStatusMove(random.nextInt(4));
				}
			}
		}
	}
}
