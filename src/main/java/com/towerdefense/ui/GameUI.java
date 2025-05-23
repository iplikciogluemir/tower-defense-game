package com.towerdefense.ui;

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
    private static Button backToMenuButton;
    private static ToggleButton darkModeToggleButton;
    private static ToggleButton muteToggleButton;
    private static ToggleButton infiniteToggleButton;

    public static VBox winScreen() {
        VBox winBox = new VBox(20);
        winBox.setAlignment(Pos.CENTER);

        winBox.setStyle("-fx-background-color:" + GameColors.getBackgroundColor());

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

        backToMenuInitializer();
        winBox.getChildren().addAll(label, backToMenuButton);

        label.setContentDisplay(ContentDisplay.BOTTOM);
        return winBox;
    }

    public static Button getWinButton() {
        return winButton;
    }

    public static VBox startScreen() {
        VBox startVbox = new VBox(20);
        startVbox.setAlignment(Pos.CENTER);
        startVbox.setStyle("-fx-background-color:" + GameColors.getBackgroundColor());

        startButton = new Button("Start Game");
        darkModeToggleButton = new ToggleButton("Dark Mode");
        muteToggleButton = new ToggleButton("Mute");
        infiniteToggleButton = new ToggleButton("Endless Mode : OFF");

        setStartButtonsStyle();

        startVbox.getChildren().addAll(startButton, darkModeToggleButton, muteToggleButton, infiniteToggleButton);
        return startVbox;
    }

    public static void setStartButtonsStyle() {

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

        muteToggleButton.setStyle("-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-color: " + GameColors.getButtonBorderColor() + ";" +
                "-fx-pref-width: 100px;" +
                "-fx-pref-height: 30px ;" +
                "-fx-background-color: " + GameColors.getButtonBackgroundColor() + ";" +
                "-fx-text-fill: " + GameColors.getTextColor() + ";" +
                "-fx-font-weight: bold;" +
                " -fx-font-size: 12px;");

        infiniteToggleButton.setStyle("-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-color: " + GameColors.getButtonBorderColor() + ";" +
                "-fx-pref-width: 150px;" +
                "-fx-pref-height: 30px ;" +
                "-fx-background-color: " + GameColors.getButtonBackgroundColor() + ";" +
                "-fx-text-fill: " + GameColors.getTextColor() + ";" +
                "-fx-font-weight: bold;" +
                " -fx-font-size: 12px;");

    }

    public static Button getStartButton() {
        return startButton;
    }

    public static ToggleButton getDarkModToggleButton() {
        return darkModeToggleButton;
    }

    public static ToggleButton getMuteToggleButton() {
        return muteToggleButton;
    }

    public static ToggleButton getInfiniToggleButton() {
        return infiniteToggleButton;
    }

    public static StackPane loseScreen() {
        StackPane pane = new StackPane();

        pane.setStyle("-fx-background-color:" + GameColors.getBackgroundColor());
        backToMenuInitializer();

        Label label = new Label("Game Over!", backToMenuButton);
        label.setStyle("-fx-text-fill: " + GameColors.getTextColor() + ";" + "-fx-font-size: 26px;");
        pane.getChildren().addAll(label);

        label.setContentDisplay(ContentDisplay.BOTTOM);
        return pane;
    }

    public static StackPane endScreen() {
        StackPane pane = new StackPane();

        pane.setStyle("-fx-background-color:" + GameColors.getBackgroundColor());

        backToMenuInitializer();

        Label label = new Label("You won!", backToMenuButton);
        label.setStyle("-fx-text-fill: " + GameColors.getTextColor() + ";" + "-fx-font-size: 26px;");
        pane.getChildren().addAll(label);

        label.setContentDisplay(ContentDisplay.BOTTOM);
        return pane;
    }

    public static Button getBackToMenuButton() {
        return backToMenuButton;
    }

    public static void backToMenuInitializer() {

        backToMenuButton = new Button("Back to Main Menu");
        backToMenuButton.setStyle("-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-color: " + GameColors.getButtonBorderColor() + ";" +
                "-fx-pref-width: 260px;" +
                "-fx-pref-height: 60px ;" +
                "-fx-background-color: " + GameColors.getButtonBackgroundColor() + ";" +
                "-fx-text-fill: " + GameColors.getTextColor() + ";" +
                ";-fx-font-weight: bold ;" +
                " -fx-font-size: 16px;");
    }
}
