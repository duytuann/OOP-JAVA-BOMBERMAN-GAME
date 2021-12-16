package main.bomberman.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    public static File placeBomb;
    public static File explore;
    public static File enemyDie;
    public static File itemCollected;
    public static File bomberDie;
    private static Clip soundInGame;
    private static boolean isMutedMusic = false;

    public Sound(){

    }

    public static void load(){
        placeBomb = new File(".\\res\\sound\\newbomb.wav");
        explore = new File(".\\res\\sound\\bomb_bang.wav");
        enemyDie = new File(".\\res\\sound\\monster_die.wav");
        itemCollected = new File(".\\res\\sound\\item.wav");
        bomberDie = new File(".\\res\\sound\\bomber_die.wav");

        try {
            File file = new File(".\\res\\sound\\playgame.mid");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file.toURI().toURL());
            soundInGame = AudioSystem.getClip();
            soundInGame.open(audioIn);
            soundInGame.loop(-1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playSound(File file) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file.toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playMusicInGame(){
        soundInGame.start();
    }

    public static void mute(){
        if(isMutedMusic) {
            soundInGame.loop(-1);
            soundInGame.start();
        }
        else soundInGame.stop();
        isMutedMusic = !isMutedMusic;
    }

    public static String getMode(){
        if(isMutedMusic) return "OFF";
        return "ON";
    }
}
