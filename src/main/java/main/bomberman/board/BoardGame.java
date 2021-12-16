package main.bomberman.board;

import javafx.scene.canvas.GraphicsContext;
import main.bomberman.entities.Entity;
import main.bomberman.entities.Message;
import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enermy.Enemy;
import main.bomberman.entities.tile.Brick;
import main.bomberman.entities.tile.Wall;
import main.bomberman.graphics.Properties;
import main.bomberman.level.LoadLevel;

import java.util.ArrayList;

public class BoardGame {
    private static int width;
    private static int height;
    private static ArrayList<Entity> map = new ArrayList<>();
    private static ArrayList<Enemy> list_enemy = new ArrayList<>();
    private static Bomber player1;
    private static ArrayList<Message> listMessage = new ArrayList<>();
    private static Properties properties;
    private static boolean paused;
    private static boolean gameOver;
    private static int selectCharP1;
    private static int selectCharP2;
    private static int mode = 1;
    private static int numberPlayer;

    public BoardGame(int selectCharacter){
        map.clear();
        list_enemy.clear();

        numberPlayer = 1;
//        mode = md;
        selectCharP1 = selectCharacter;
        player1 = new Bomber(selectCharacter, 1);
        LoadLevel.load(1);
        width = LoadLevel.get_width();
        height = LoadLevel.get_height();
        map = LoadLevel.getMap();
        list_enemy = LoadLevel.getListEnemy();
        properties = new Properties(player1);
        paused = false;
        gameOver = false;
    }

    public void update(double elapsedTime){
        if(paused){
            return;
        }

        player1.update(elapsedTime);
//        if(player2 != null)
//            player2.update(elapsedTime);

        for(int i = 0; i < list_enemy.size(); i++){
            list_enemy.get(i).update(elapsedTime);
            if(list_enemy.get(i).isKilled()){
                list_enemy.remove(i);
                i--;
            }
        }
    }

    public void render(GraphicsContext gc, double time){

        for(Entity e : map) {
            if(e instanceof Brick){
                ((Brick) e).render(gc, time);
            }
            else
                e.render(gc);
        }

        player1.render(gc, time);
//        if(player2 != null)
//            player2.render(gc, time);

        for(Enemy enemy : list_enemy){
            enemy.render(gc, time);
        }

        for(Message m : listMessage){
            m.render(gc);
        }

        properties.render(gc);

        if(gameOver){
            paused = true;
            properties.renderGameOver(gc);
        }
    }

    public static Entity getEntityAt(int x, int y){
        if(x + y*width >= 0 && x + y*width < map.size())
            return map.get(x + y*width);
        return new Wall();
    }

    public static int[][] getMap(){
        int[][] res = new int[height][width];

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                Entity e = getEntityAt(j, i);
                if(e instanceof Brick){
                    if(((Brick) e).isDestroyed())
                        res[i][j] = 1;
                    else res[i][j] = 0;
                }
                else res[i][j] = (LoadLevel.get_map()[i][j] == '#')?(0):(1);
            }
        }

        return res;
    }

    public static ArrayList<Enemy> getListEnemy(){
        return list_enemy;
    }

    public static void addMessage(String content, int x, int y){
        listMessage.add(new Message(content, x, y));
    }

    public static void pause(){
        paused = !paused;
    }

    public static void nextLevel(){
        map.clear();
        list_enemy.clear();

        player1 = new Bomber(selectCharP1, 1);
        properties.reset(player1);
//        if(numberPlayer == 2)
//            player2 = new Bomber(selectCharP2, 2);

        LoadLevel.load(LoadLevel.get_level() + 1);
        map = LoadLevel.getMap();
        list_enemy = LoadLevel.getListEnemy();

        paused = false;
        gameOver = false;
    }

    public static void addScore(int x, int y){
        addMessage(properties.addScore(), x, y);
    }

    public static int getScore(){
        return properties.getScore();
    }

    public static void setGameOver(boolean ok){
        gameOver = ok;
    }

    public static boolean endGame(){
        return gameOver;
    }

    public static int getMode(){
        return mode;
    }

    public static Bomber getPlayer1(){
        return player1;
    }
}
