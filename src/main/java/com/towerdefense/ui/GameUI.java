package com.towerdefense.ui;

import javax.swing.plaf.basic.BasicTreeUI.TreeToggleAction;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameUI {

    private static Button startButton;
    private static Button winButton;
    private static Button backToMenuLose;
    private static Button backToMenuEnd;
    private static ToggleButton darkModeToggleButton;

    public static StackPane winScreen() {
        StackPane pane = new StackPane();

        pane.setStyle("-fx-background-color:" + GameColors.getBackgroundColor());

        winButton = new Button("Continue to Next Level");
        winButton.setStyle("-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-color: " + GameColors.getButtonBorderColor() + ";" +
                "-fx-pref-width: 260px;" +
                "-fx-pref-height: 60px ;" +
                "-fx-background-color: " + GameColors.getButtonBackgroundColor() + ";" +
                "-fx-text-fill: " + GameColors.getTextColor() + ";" +
                ";-fx-font-weight: bold ;" +
                " -fx-font-size: 16px;");

        Label label = new Label("You Won!", winButton);
        label.setStyle("-fx-text-fill:" + GameColors.getTextColor() + ";" + "-fx-font-size: 26px;");
        pane.getChildren().addAll(label);

        label.setContentDisplay(ContentDisplay.BOTTOM);
        return pane;
    }

    public static Button getWinButton() {
        return winButton;
    }

    public static VBox startScreen() {
        VBox startVbox = new VBox(20);
        startVbox.setAlignment(Pos.CENTER);
        startVbox.setStyle("-fx-background-color:" + GameColors.getBackgroundColor());

        startButton = new Button("Start Game");
        startButton.setStyle("-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-color: " + GameColors.getButtonBorderColor() + ";" +
                "-fx-pref-width: 200px;" +
                "-fx-pref-height: 100px ;" +
                "-fx-background-color: " + GameColors.getButtonBackgroundColor() + ";" +
                "-fx-text-fill: " + GameColors.getTextColor() + ";" +
                "-fx-font-weight: bold;" +
                " -fx-font-size: 16px;");

        darkModeToggleButton = new ToggleButton("Dark Mode");
        darkModeToggleButton.setStyle("-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-color: " + GameColors.getButtonBorderColor() + ";" +
                "-fx-pref-width: 100px;" +
                "-fx-pref-height: 30px ;" +
                "-fx-background-color: " + GameColors.getButtonBackgroundColor() + ";" +
                "-fx-text-fill: " + GameColors.getTextColor() + ";" +
                "-fx-font-weight: bold;" +
                " -fx-font-size: 12px;");

        startVbox.getChildren().addAll(startButton, darkModeToggleButton);
        return startVbox;
    }

    public static Button getStartButton() {
        return startButton;
    }

    public static ToggleButton getDarkModToggleButton() {
        return darkModeToggleButton;
    }

    public static StackPane loseScreen() {
        StackPane pane = new StackPane();

        pane.setStyle("-fx-background-color:" + GameColors.getBackgroundColor());

        backToMenuLose = new Button("Back to Main Menu");
        backToMenuLose.setStyle("-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-color: " + GameColors.getButtonBorderColor() + ";" +
                "-fx-pref-width: 260px;" +
                "-fx-pref-height: 60px ;" +
                "-fx-background-color: " + GameColors.getButtonBackgroundColor() + ";" +
                "-fx-text-fill: " + GameColors.getTextColor() + ";" +
                ";-fx-font-weight: bold ;" +
                " -fx-font-size: 16px;");

        Label label = new Label("Game Over!", backToMenuLose);
        label.setStyle("-fx-text-fill: " + GameColors.getTextColor() + ";" + "-fx-font-size: 26px;");
        pane.getChildren().addAll(label);

        label.setContentDisplay(ContentDisplay.BOTTOM);
        return pane;
    }

    public static Button getBackToMenuButton() {
        return backToMenuLose;
    }

    public static StackPane endScreen() {
        StackPane pane = new StackPane();

        pane.setStyle("-fx-background-color:" + GameColors.getBackgroundColor());

        backToMenuEnd = new Button("Back to Main Menu");
        backToMenuEnd.setStyle("-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-color: " + GameColors.getButtonBorderColor() + ";" +
                "-fx-pref-width: 260px;" +
                "-fx-pref-height: 60px ;" +
                "-fx-background-color: " + GameColors.getButtonBackgroundColor() + ";" +
                "-fx-text-fill: " + GameColors.getTextColor() + ";" +
                ";-fx-font-weight: bold ;" +
                " -fx-font-size: 16px;");

        Label label = new Label("You won!", backToMenuEnd);
        label.setStyle("-fx-text-fill: " + GameColors.getTextColor() + ";" + "-fx-font-size: 26px;");
        pane.getChildren().addAll(label);

        label.setContentDisplay(ContentDisplay.BOTTOM);
        return pane;
    }

    public static Button getBackToMenuEndButton() {
        return backToMenuEnd;
    }
}
