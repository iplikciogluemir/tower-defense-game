package com.towerdefense.ui;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

class HUDVariables {
    private int lives;
    private int money;
    private Timeline timeline;
    private int seconds;
    private String countViewer;

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

    public void startTime() {
        countViewer = seconds + "s";

        if (timeline != null) {
            timeline.stop();
            timeline = null;
        }

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), e -> {
            if (seconds > 0) {
                seconds--;
                countViewer = seconds + "s";
            } else {
                countViewer = "0s";
                timeline.stop();
            }
        });

        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(timeline.INDEFINITE);
        timeline.play();
    }

    public String getCountViewer() {
        return countViewer;
    }
}