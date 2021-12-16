package main.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Message {
    private String content;
    private int posX;
    private int posY;
    private int time = 50;
    private Font font = new Font("Arial", 20);

    public Message(String s, int x, int y){
        content = s;
        posX = x;
        posY = y;
    }

    public void render(GraphicsContext gc){
        if(time-- > 0) {
            gc.setFont(font);
            gc.setFill(Color.RED);
            gc.fillText(content, posX, posY);
        }
    }
}