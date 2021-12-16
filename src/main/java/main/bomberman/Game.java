package main.bomberman;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.bomberman.Input.Input;
import main.bomberman.board.BoardGame;
import main.bomberman.graphics.Properties;

import java.io.IOException;

public class Game {
    private final int WIDTH = 1344;
    private final int HEIGHT = 720;
    private Stage stage;
    private int mode = 1;

    public Game(Stage stage, String namePlayer, int selectCharacter){
        this.stage = stage;
        Group root = new Group();
        Scene theScene = new Scene(root, WIDTH, HEIGHT, Color.GRAY);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        Input.setScene(theScene, 1);
        Properties.setName(namePlayer);

        BoardGame boardGame = new BoardGame(selectCharacter);

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );
        final long timeStart = System.currentTimeMillis();
        final long[] lastNanoTime = {System.nanoTime()};

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),                // 60 FPS
                new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                        double t = (System.currentTimeMillis() - timeStart) / 1000.0;

                        double elapsedTime = (System.nanoTime() - lastNanoTime[0]) / 1000000000.0;
                        lastNanoTime[0] = System.nanoTime();

                        boardGame.update(elapsedTime);

                        // render
                        gc.clearRect(0, 0, WIDTH,HEIGHT);
                        boardGame.render(gc, t);

                        if(BoardGame.endGame() && Input.quit()){
                            gameLoop.stop();
                            switchToMenu();
                        }
                    }
                });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
        stage.setScene(theScene);
    }


    private void switchToMenu(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(BombermanGame.class.getResource("first-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
