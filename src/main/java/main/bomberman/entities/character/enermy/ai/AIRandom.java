package main.bomberman.entities.character.enermy.ai;

public class AIRandom extends AI{

    @Override
    public int calcDirection() {
        return random.nextInt(4);
    }
}
