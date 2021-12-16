package main.bomberman.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.bomberman.BombermanGame;
import main.bomberman.sound.Sound;

import java.io.IOException;

public class IntroViewController {
    public void exit(ActionEvent event) {
        try {
            Sound.playSound(Sound.placeBomb);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(BombermanGame.class.getResource("first-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
