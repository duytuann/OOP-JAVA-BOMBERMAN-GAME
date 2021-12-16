package main.bomberman.Input;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import main.bomberman.board.BoardGame;

import java.util.Stack;

public class Input {
    private static Scene theScene;
    private static Stack<String> listInput = new Stack<>();
    private int no = 1;
    private static int mode = 1;
    public Input(int no){
        this.no = no;
    }


    public static void setScene(Scene scene, int md){
        theScene = scene;
        mode = md;
        listInput.clear();
        theScene.setOnKeyPressed(
                new EventHandler<javafx.scene.input.KeyEvent>() {
                    @Override
                    public void handle(javafx.scene.input.KeyEvent e) {
                        String code = e.getCode().toString();
                        if(code.equalsIgnoreCase("P")){
                            BoardGame.pause();
                        }
                        else if(!listInput.contains(code)){
                            listInput.push(code);
                        }
                    }
                }
        );

        theScene.setOnKeyReleased(
                new EventHandler<javafx.scene.input.KeyEvent>() {
                    @Override
                    public void handle(javafx.scene.input.KeyEvent e) {
                        String code = e.getCode().toString();
                        //input.remove(code);
                        if(listInput.size() > 0)
                            listInput.remove(code);
                    }
                });
    }

    public boolean right(){
        if (listInput.size() < 1)
            return false;
        if(mode == 1){
            return listInput.peek().equals("RIGHT");
        }
        else {
            if (no == 1) {
                return listInput.contains("D");
            } else {
                //return listInput.peek().equals("RIGHT");
                return listInput.contains("RIGHT");
            }
        }
    }
    public boolean left(){
        if(listInput.size() < 1)
            return false;
        if(mode == 1) {
            return listInput.peek().equals("LEFT");
        }
        else{
            if (no == 1) {
                return listInput.contains("A");
            } else
                return listInput.contains("LEFT");
        }
    }
    public boolean up(){
        if(listInput.size() < 1)
            return false;
        if(mode == 1){
            return listInput.peek().equals("UP");
        }
        else {
            if (no == 1) {
                return listInput.contains("W");
            } else
                return listInput.contains("UP");
        }
    }
    public boolean down(){
        if(listInput.size() < 1)
            return false;
        if(mode == 1){
            return listInput.peek().equals("DOWN");
        }
        else {
            if (no == 1) {
                return listInput.contains("S");
            } else
                return listInput.contains("DOWN");
        }
    }
    public boolean placeBomb(){
        if(listInput.size() < 1)
            return false;
        if(no == 1)
            return listInput.peek().equals("SPACE"); //input.contains("SPACE") || input.contains("ENTER");
        else
            return listInput.peek().equals("ENTER");
    }

    public static boolean quit(){
        if(listInput.size() < 1)
            return false;
        return listInput.peek().equals("Q");
    }
}
