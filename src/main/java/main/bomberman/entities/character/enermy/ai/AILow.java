package main.bomberman.entities.character.enermy.ai;

public class AILow extends AI{

    @Override
    public int calcDirection() {
        return random.nextInt(4);
    }
}
