package main.bomberman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.bomberman.sound.Sound;

import java.io.IOException;

public class BombermanGame extends Application {
    private final int WIDTH = 1344;
    private final int HEIGHT = 720;

    @Override
    public void start(Stage theStage) throws IOException {
        theStage.setTitle("BomberMan");
        Sound.load();
        Sound.playMusicInGame();
        FXMLLoader fxmlLoader = new FXMLLoader(BombermanGame.class.getResource("first-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        theStage.setScene(scene);
        theStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
