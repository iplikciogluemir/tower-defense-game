package com.towerdefense;

import java.io.File;

import com.towerdefense.game.DragTowers;
import com.towerdefense.game.LevelManager;
import com.towerdefense.ui.GameColors;
import com.towerdefense.ui.GameUI;
import com.towerdefense.ui.HUDVariables;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;

public class Main extends Application {

    private static int levelIndex = 1;

    private Scene scene = new Scene(new Pane());
    private Timeline timeline;
    private boolean isInfinite;

    public static Media menuTheme;
    public static MediaPlayer menuThemeSound;
    public static Media mainTheme;
    public static MediaPlayer mainThemeSound;
    public static Media gameOver;
    public static MediaPlayer gameOverSound;

    public static int getLevelIndex() {
        return levelIndex;
    }

    @Override
    public void start(Stage primaryStage) {

        menuTheme = new Media(new File("src/main/resources/sounds/MenuTheme.wav").toURI().toString());
        menuThemeSound = new MediaPlayer(menuTheme);
        menuThemeSound.setVolume(0.07);
        menuThemeSound.setCycleCount(MediaPlayer.INDEFINITE);

        mainTheme = new Media(new File("src/main/resources/sounds/MainTheme.wav").toURI().toString());
        mainThemeSound = new MediaPlayer(mainTheme);
        mainThemeSound.setVolume(0.07);
        mainThemeSound.setCycleCount(MediaPlayer.INDEFINITE);

        gameOver = new Media(new File("src/main/resources/sounds/GameOver.mp3").toURI().toString());
        gameOverSound = new MediaPlayer(gameOver);
        gameOverSound.setVolume(0.07);

        startGame();

        timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
            if (HUDVariables.getLives() == 0) {
                lose();
                timeline.pause();
            } else if (LevelManager.isLevelOver()) {
                win();
                timeline.pause();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Tower Defense Game");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("Press F11 to switch full-screen mode.");

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.F11)
                primaryStage.setFullScreen(!primaryStage.isFullScreen());

        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void startGame() {
        VBox vbox = GameUI.startScreen();
        scene.setRoot(vbox);
        menuThemeSound.play();

        if (GameColors.isDarkMode()) {
            GameUI.getDarkModToggleButton().setSelected(true);
            GameUI.getDarkModToggleButton().setText("Light Mode");
        }

        GameUI.getDarkModToggleButton().setOnAction(e -> {
            if (GameUI.getDarkModToggleButton().isSelected()) {
                GameUI.getDarkModToggleButton().setText("Light Mode");
                GameColors.setDarkMode(true);
                vbox.setStyle("-fx-background-color: " + GameColors.getBackgroundColor());
                GameUI.setStartButtonsStyle();
            } else {
                GameUI.getDarkModToggleButton().setText("Dark Mode");
                GameColors.setDarkMode(false);
                vbox.setStyle("-fx-background-color: " + GameColors.getBackgroundColor());
                GameUI.setStartButtonsStyle();
            }
        });

        if (menuThemeSound.isMute()) {
            GameUI.getMuteToggleButton().setSelected(true);
            GameUI.getMuteToggleButton().setText("Unmute");
        }
        GameUI.getMuteToggleButton().setOnAction(e -> {
            if (GameUI.getMuteToggleButton().isSelected()) {
                muteMusic();
                GameUI.getMuteToggleButton().setText("Unmute");
            } else {
                unMuteMusic();
                GameUI.getMuteToggleButton().setText("Mute");
            }
        });

        if (isInfinite) {
            GameUI.getInfiniToggleButton().setSelected(true);
            GameUI.getInfiniToggleButton().setText("Endless Mode : ON");
        }

        GameUI.getInfiniToggleButton().setOnAction(e -> {
            if (GameUI.getInfiniToggleButton().isSelected()) {
                isInfinite = true;
                GameUI.getInfiniToggleButton().setText("Endless Mode : ON");
            } else {
                isInfinite = false;
                GameUI.getInfiniToggleButton().setText("Endless Mode : OFF");
            }
        });

        GameUI.getStartButton().setOnMouseClicked(e -> {
            menuThemeSound.stop();
            mainThemeSound.play();

            levelIndex = 1;
            scene.setRoot(LevelManager.getLevelPane(levelIndex));

            if (GameUI.getMuteToggleButton().isSelected())
                LevelManager.muteEffects();
            else
                LevelManager.unMuteEffects();

            HUDVariables.setMoney(100);
            LevelManager.resetLevelCondition();
            timeline.play();
        });
    }

    public void win() {
        LevelManager.clearAll();
        DragTowers.getTowerMap().clear();

        if (levelIndex == 5 && !isInfinite) {
            mainThemeSound.stop();
            Pane pane = GameUI.endScreen();
            scene.setRoot(pane);

            GameUI.getBackToMenuButton().setOnMouseClicked(e -> {
                startGame();
                mainThemeSound.stop();
            });
        } else {
            VBox vbox = GameUI.winScreen();
            levelIndex++;
            scene.setRoot(vbox);

            GameUI.getWinButton().setOnMouseClicked(e -> {
                mainThemeSound.play();
                if (levelIndex > 5)
                    scene.setRoot(LevelManager.getRandomLevel());
                else
                    scene.setRoot(LevelManager.getLevelPane(levelIndex));

                if (GameUI.getMuteToggleButton().isSelected())
                    LevelManager.muteEffects();
                else
                    LevelManager.unMuteEffects();

                LevelManager.resetLevelCondition();
                timeline.play();
            });

            GameUI.getBackToMenuButton().setOnAction(e -> {
                mainThemeSound.stop();
                startGame();
            });
        }

    }

    public void lose() {
        mainThemeSound.stop();
        gameOverSound.play();
        timeline.pause();
        LevelManager.clearAll();
        DragTowers.getTowerMap().clear();

        Pane pane = GameUI.loseScreen();
        scene.setRoot(pane);

        GameUI.getBackToMenuButton().setOnMouseClicked(e -> {
            gameOverSound.stop();
            startGame();
        });
    }

    public static void muteMusic() {
        menuThemeSound.setMute(true);
        mainThemeSound.setMute(true);
        gameOverSound.setMute(true);
    }

    public static void unMuteMusic() {
        menuThemeSound.setMute(false);
        mainThemeSound.setMute(false);
        gameOverSound.setMute(false);
    }
}