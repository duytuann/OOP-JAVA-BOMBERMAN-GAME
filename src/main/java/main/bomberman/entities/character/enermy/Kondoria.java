package main.bomberman.entities.character.enermy;

import main.bomberman.entities.character.enermy.ai.AIPro;

import java.util.Random;

public class Kondoria extends Enemy {
	private boolean isTheFirstStep = true;

	public Kondoria() {
		brain = new AIPro(bomber, this);

		setFrame("sprites\\kondoria_left", "sprites\\kondoria_left", "sprites\\kondoria_right",
				"sprites\\kondoria_right", 3);
		setAnimateDead("sprites\\kondoria_dead", 1);
		setPosition(864, 48);
		speed = 3;
	}

	/*
	 * Update if (inTheDes -> getNextDir) else postX + Vx postY + Vy
	 * 
	 * else if isThinking -> createWay() else !canSolve -> random
	 * 
	 */

	@Override
	public void update(double time) {
		if (!alive)
			return;

		if (this.intersects(bomber)) {
			this.kill();
			bomber.kill();
		}

		if (!((AIPro) brain).inTheDes()) {
			if (((AIPro) brain).checkPos(positionX, positionY)) {
				setStatusMove(((AIPro) brain).getNextDir());
			} else {
				positionX += velocityX;
				positionY += velocityY;
			}
		} else {
			if (!((AIPro) brain).isThinking())
				((AIPro) brain).creatWay();
			else {
				if (!((AIPro) brain).canSolve()) {
					Random random = new Random();
					setStatusMove(random.nextInt(4));
				}
			}
		}
	}
}
