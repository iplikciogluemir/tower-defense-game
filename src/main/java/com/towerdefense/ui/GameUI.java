package com.towerdefense.ui;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class GameUI {

    public static StackPane winScreen() {
        StackPane pane = new StackPane();

        pane.setStyle("-fx-background-color:#faf1da");

        Button btBack = new Button("Continue to Next Level");
        btBack.setStyle("-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-color: #eed399;" +
                "-fx-pref-width: 260px;" +
                "-fx-pref-height: 60px ;" +
                "-fx-background-color: #f2d79d;" +
                "-fx-text-fill: #582b0d;" +
                ";-fx-font-weight: bold ;" +
                " -fx-font-size: 16px;");

        Label label = new Label("You Won!", btBack);
        label.setStyle("-fx-text-fill: #582b0d; -fx-font-size: 26px;");
        pane.getChildren().addAll(label);

        label.setContentDisplay(ContentDisplay.BOTTOM);
        return pane;
    }

    public static StackPane startScreen() {
        StackPane pane = new StackPane();
        pane.setStyle("-fx-background-color:#faf1da");

        Button bt = new Button("Start Game");
        bt.setStyle("-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-color: #eed399;" +
                "-fx-pref-width: 200px;" +
                "-fx-pref-height: 100px ;" +
                "-fx-background-color: #f2d79d;" +
                "-fx-text-fill: #582b0d;" +
                "-fx-font-weight: bold;" +
                " -fx-font-size: 16px;");
        pane.getChildren().add(bt);
        return pane;
    }

    public static StackPane loseScreen() {
        StackPane pane = new StackPane();

        pane.setStyle("-fx-background-color:#faf1da");

        Button btBack = new Button("Back to Main Menu");
        btBack.setStyle("-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-color: #eed399;" +
                "-fx-pref-width: 260px;" +
                "-fx-pref-height: 60px ;" +
                "-fx-background-color: #f2d79d;" +
                "-fx-text-fill: #582b0d;" +
                ";-fx-font-weight: bold ;" +
                " -fx-font-size: 16px;");

        Label label = new Label("Game Over!", btBack);
        label.setStyle("-fx-text-fill: #582b0d; -fx-font-size: 26px;");
        pane.getChildren().addAll(label);

        label.setContentDisplay(ContentDisplay.BOTTOM);
        return pane;
    }

    public static StackPane endScreen() {
        StackPane pane = new StackPane();

        pane.setStyle("-fx-background-color:#faf1da");

        Button btBack = new Button("Back to Main Menu");
        btBack.setStyle("-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-color: #eed399;" +
                "-fx-pref-width: 260px;" +
                "-fx-pref-height: 60px ;" +
                "-fx-background-color: #f2d79d;" +
                "-fx-text-fill: #582b0d;" +
                ";-fx-font-weight: bold ;" +
                " -fx-font-size: 16px;");

        Label label = new Label("You won!", btBack);
        label.setStyle("-fx-text-fill: #582b0d; -fx-font-size: 26px;");
        pane.getChildren().addAll(label);

        label.setContentDisplay(ContentDisplay.BOTTOM);
        return pane;

    }
}
