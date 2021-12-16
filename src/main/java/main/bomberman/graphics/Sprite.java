package main.bomberman.graphics;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Sprite {
    public Sprite(){
    }

    public static Image removeBackground(Image image, int scale){
        int W = (int) image.getWidth();
        int H = (int) image.getHeight();
        WritableImage outputImage = new WritableImage(W*scale, H*scale);
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = outputImage.getPixelWriter();
        Color oldColor = image.getPixelReader().getColor(W - 1,H - 1);
        int ob=(int) oldColor.getBlue()*255;
        int or=(int) oldColor.getRed()*255;
        int og=(int) oldColor.getGreen()*255;

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                int argb = reader.getArgb(x, y);
                int a = (argb >> 24) & 0xFF;
                int r = (argb >> 16) & 0xFF;
                int g = (argb >>  8) & 0xFF;
                int b =  argb        & 0xFF;
                if (g==og && r==or && b==ob) {
                    a = 0;
                }

                argb = (a << 24) | (r << 16) | (g << 8) | b;
                for(int e = 0; e < scale; e++)
                    for (int f = 0; f < scale; f++)
                        writer.setArgb(x*scale + e, y*scale + f, argb);
            }
        }
        return outputImage;
    }

    public static Image[] getListImage(String name, int number, int scale) {
        Image[] imageArray = new Image[number];
        for (int i = 0; i < number; i++) {
            imageArray[i] = new Image(Objects.requireNonNull(GetImage.get(name + i + ".png")));
            imageArray[i] = removeBackground(imageArray[i], scale);
        }
        return imageArray;
    }

    public static Image[] getListImage(String name, int number, int scale, boolean removeBgr){
        Image[] imageArray = new Image[number];
        if(removeBgr){
            for (int i = 0; i < number; i++) {
                imageArray[i] = new Image(Objects.requireNonNull(GetImage.get(name + i + ".png")));
                imageArray[i] = removeBackground(imageArray[i], scale);
            }
        }
        else {
            for (int i = 0; i < number; i++) {
                imageArray[i] = new Image(Objects.requireNonNull(GetImage.get(name + i + ".png")));
            }
        }
        return imageArray;
    }

    public static Image readImage(Image image, int row, int col, int W, int H, int scale){
        WritableImage outputImage = new WritableImage(W*scale, H*scale);
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = outputImage.getPixelWriter();

        for (int x = 0; x < W; x++) {
            for (int y = 0; y < H; y++) {
                int argb = reader.getArgb(x + col*W, y + row*H);

                for(int e = 0; e < scale; e++)
                    for (int f = 0; f < scale; f++)
                        writer.setArgb(x*scale + e, y*scale + f, argb);
            }
        }
        return outputImage;
    }

    public static Image[][] getListImage(String name, int row, int col, int scale){
        Image[][] imageArray = new Image[row][col];
        Image im = new Image(Objects.requireNonNull(GetImage.get(name)));
        int w = (int)im.getWidth()/col;
        int h = (int)im.getHeight()/row;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                imageArray[i][j] = readImage(im, i, j, w, h, scale);
            }
        }
        return imageArray;
    }
}
