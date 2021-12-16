package main.bomberman.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.bomberman.BombermanGame;
import main.bomberman.sound.Sound;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstViewController implements Initializable {
    public javafx.scene.control.Label btnMsic;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnMsic.setText(Sound.getMode());
    }

    public void start(ActionEvent event) {
        try {
            Sound.playSound(Sound.placeBomb);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(BombermanGame.class.getResource("get-name.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exit(ActionEvent event) {
        Sound.playSound(Sound.placeBomb);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void sound(ActionEvent event) {
        Sound.mute();
        btnMsic.setText(Sound.getMode());
    }

    public void introBtn(ActionEvent event) {
        try {
            Sound.playSound(Sound.placeBomb);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(BombermanGame.class.getResource("intro-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
