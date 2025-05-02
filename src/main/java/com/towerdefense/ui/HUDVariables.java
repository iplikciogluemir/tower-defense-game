package com.towerdefense.ui;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HUDVariables {
    private static int lives;
    private static int money;
    private static Timeline timeline;
    private static int seconds;
    private static String countViewer;

    private static Text countText;
    private static Text moneyText;
    private static Text livesText;

    // LIVES METHODS
    public static void setLivesText(Text livesText) {
        HUDVariables.livesText = livesText;
    }

    public static int getLives() {
        return lives;
    }

    public static void setLives(int lives) {
        HUDVariables.lives = lives;
        if (livesText != null)
            livesText.setText("Lives: " + lives);
    }

    // MONEY METHODS
    public static void setMoneyText(Text moneyText) {
        HUDVariables.moneyText = moneyText;
    }

    public static int getMoney() {
        return money;
    }

    public static void setMoney(int money) {
        HUDVariables.money = money;
        if (moneyText != null)
            moneyText.setText("Money: " + money + "$");
    }

    // TIME METHODS
    public static void setTime(int seconds) {
        HUDVariables.seconds = seconds;
    }

    public static void setCountText(Text countText) {
        HUDVariables.countText = countText;
    }

    public static String counterViewer() {
        countViewer = seconds + "s";

        if (timeline != null) {
            timeline.stop();
            timeline = null;
        }

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), e -> {
            if (seconds > 0) {
                seconds--;
                countViewer = seconds + "s";
                countText.setText("Next Wave: " + countViewer);
            } else {
                countViewer = "0s";
                countText.setText("Next Wave: " + countViewer);
                timeline.stop();
                HUD.startAnimation();
            }
        });

        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        return countViewer;
    }
}