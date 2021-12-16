package main.bomberman.entities.character.enermy.ai;

import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enermy.Enemy;

public class AIMedium extends AI{
    private Bomber bomber;
    private Enemy myseft;

    private final int MAX_RANGE = 250;
    public AIMedium(Enemy e, Bomber b){
        myseft = e;
        bomber = b;
    }

    private int getDistanceX(){
        return  myseft.getPositionX() - bomber.getPositionX();
    }

    private int getDistanceY(){
        return  myseft.getPositionY() - bomber.getPositionY();
    }

    private double getDistance(){
        //return Math.sqrt(Math.pow(getDistanceX(), 2) + Math.pow(getDistanceY(), 2));
        return Math.abs(getDistanceX()) + Math.abs(getDistanceY());
    }

    @Override
    public int calcDirection() {
        int x = getDistanceX();
        int y = getDistanceY();
        if(/*random.nextInt(15)%5 != 0 &&*/ getDistance() <= MAX_RANGE){
            //0: down, 1: left, 2: right, 3: up
            int[] dir = new int[2];

            if(x > 0)
                dir[0] = 1;
            else if(x < 0)
                dir[0] = 2;
            else
                dir[0] = -1;

            if(y > 0)
                dir[1] = 3;
            else if(y < 0)
                dir[1] = 0;
            else
                dir[1] = -1;

            if(dir[0] == -1){
                if(dir[1] == -1){
                    return random.nextInt(4);
                }
                else
                    return dir[1];
            }
            else{
                if(dir[1] == -1)
                    return dir[0];
                else
                    return dir[random.nextInt(2)];
            }
        } else
            return random.nextInt(4);
    }
}
