package com.towerdefense.ui;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.text.Text;

class HUDVariables {
    private int lives;
    private int money;
    private Timeline timeline;
    private int seconds;
    private String countViewer;
    private Text countText;

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setTime(int seconds) {
        this.seconds = seconds;
    }

    public void setCountText(Text countText) {
        this.countText = countText;
    }

    public String counterViewer() {
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
            }
        });

        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(timeline.INDEFINITE);
        timeline.play();

        return countViewer;
    }
}