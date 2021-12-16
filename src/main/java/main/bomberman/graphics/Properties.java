package main.bomberman.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.bomberman.board.BoardGame;
import main.bomberman.entities.character.Bomber;
import main.bomberman.level.LoadLevel;

public class Properties{
    //W: 240
    private final int positionX;
    private final Image image;
    private Image avatar;
    private final Image gameOver;
    private Bomber bomber;
    private static String name = "xxx";
    private int score;
    private int count = 0;

    public Properties(Bomber bomber){
        positionX = LoadLevel.get_width()*48;
        image = new Image(GetImage.get("sprites\\properties2.png"));
        avatar = new Image(GetImage.get("sprites\\jetter" + bomber.getSelectCharacter() + ".png"));
        avatar = getAvatar(avatar, 8);
        gameOver = new Image(GetImage.get("sprites\\gameover.png"));
        this.bomber = bomber;
        score = 0;
    }

    public static void setName(String name){
        Properties.name = name;
    }

    private Image getAvatar(Image image, int scale){
        int W = (int) image.getWidth()/3;
        int H = (int) image.getHeight()/7;
        WritableImage outputImage = new WritableImage(W*scale, H*scale);
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = outputImage.getPixelWriter();

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                int argb = reader.getArgb(x, y);
                for(int e = 0; e < scale; e++)
                    for (int f = 0; f < scale; f++)
                        writer.setArgb(x*scale + e, y*scale + f, argb);
            }
        }
        return outputImage;
    }

    public void render(GraphicsContext gc){
        gc.drawImage(image, positionX, 0);
        gc.drawImage(avatar, positionX + 70, 180);

        gc.setFill(Color.BLACK);
        gc.setFont(new Font("Algerian", 40));
        gc.fillText("LEVEL " + LoadLevel.get_level(), positionX + 45, 60);
        gc.setFont(new Font("Algerian", 28));
        gc.fillText(name, positionX + (240 - name.length() * 18) / 2, 333);
        gc.setFont(new Font("Algerian", 30));
        gc.fillText("SCORE: " + score, positionX + 50, 420);
        gc.fillText(":  " + bomber.getNumberBomb(), positionX + 110, 493);
        gc.fillText(":  " + (bomber.getPowerFlames()*2 + 3), positionX + 110, 550);
        gc.fillText(":  " + (int)bomber.getSpeed(), positionX + 110, 607);
        if(!BoardGame.endGame() && count++ > 30) {
            gc.setFont(new Font("Algerian", 20));
            gc.fillText("PRESS P TO PAUSE", positionX + 33, 675);
            if(count > 80)
                count = 0;
        }
    }

    public String addScore(){
        this.score += 100;
        return String.valueOf(100);
    }

    public void renderGameOver(GraphicsContext gc){
        gc.drawImage(gameOver, 327, 170);
        if(count++ > 30) {
            gc.setFont(new Font("Algerian", 40));
            gc.setFill(Color.CYAN);
            gc.fillText("PRESS Q TO QUIT", 420, 520);
            if (count > 80)
                count = 0;
        }
    }

    public static String getName(){
        return name;
    }

    public int getScore(){
        return score;
    }

    public void reset(Bomber bomber) {
        this.bomber = bomber;
    }
}
