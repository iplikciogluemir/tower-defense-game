package com.towerdefense.ui;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import com.towerdefense.towers.LaserTower;
import com.towerdefense.towers.MissileLauncherTower;
import com.towerdefense.towers.SingleShotTower;
import com.towerdefense.towers.TripleShotTower;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TowerPanel {

    private static Pane mainDraggablePane;

    public static void setMainDraggablePane(Pane pane) {
        mainDraggablePane = pane;
    }

    public static StackPane getTowerPanel() {

        VBox vbx = new VBox(10);
        final double prefWidth = 200;

        String css = "-fx-background-color: #f2d79d;" +
                "-fx-border-color: #eed399;" +
                "-fx-border-width: 2;" +
                "-fx-padding:5;" +
                "-fx-text-fill: #582b0d;" + "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-pref-width:" + prefWidth + ";" +
                "-fx-alignment:center";

        HUDVariables variables = new HUDVariables();
        variables.setLives(5);
        variables.setMoney(100);
        variables.setTime(10);

        Text livesText = new Text("Lives: " + variables.getLives());
        livesText.setFont(Font.font(24));
        livesText.setFill(Color.web("#672b00"));

        Text moneyText = new Text("Money: " + variables.getMoney() + "$");
        moneyText.setFont(Font.font(24));
        moneyText.setFill(Color.web("#672b00"));

        Text countText = new Text("Next Wave: " + variables.counterViewer());
        variables.setCountText(countText);
        countText.setFont(Font.font(24));
        countText.setFill(Color.web("#672b00"));

        vbx.setStyle("-fx-background-color: #faf1da");
        Label singleShotTower = new Label("Single Shot Tower - 50$", SingleShotTower.getSingleShotTower());
        singleShotTower.setContentDisplay(ContentDisplay.TOP);
        singleShotTower.setStyle(css);

        Label laserTower = new Label("Single Shot Tower - 120$", LaserTower.getLaserTower());
        laserTower.setContentDisplay(ContentDisplay.TOP);
        laserTower.setStyle(css);

        Label tripleShotTower = new Label("Triple Shot Tower - 150$", TripleShotTower.getTripleShotTower());
        tripleShotTower.setContentDisplay(ContentDisplay.TOP);
        tripleShotTower.setStyle(css);

        Label missileLauncherTower = new Label("Missile Launcher Tower - 200$",
                MissileLauncherTower.getMissileLauncherTower());
        missileLauncherTower.setContentDisplay(ContentDisplay.TOP);
        missileLauncherTower.setStyle(css);

        vbx.getChildren().addAll(livesText, moneyText, countText, singleShotTower, laserTower, tripleShotTower,
                missileLauncherTower);
        vbx.setAlignment(Pos.CENTER);
        vbx.setPadding(new Insets(0, 20, 0, 20));

        mainDraggablePane.setMouseTransparent(true);
        DragTowers dragMechanism = new DragTowers(mainDraggablePane);

        dragMechanism.draggableTower(singleShotTower, SingleShotTower.getSingleShotTower());
        dragMechanism.draggableTower(laserTower, LaserTower.getLaserTower());
        dragMechanism.draggableTower(tripleShotTower, TripleShotTower.getTripleShotTower());
        dragMechanism.draggableTower(missileLauncherTower, MissileLauncherTower.getMissileLauncherTower());

        StackPane pane = new StackPane(vbx);

        pane.setOnMousePressed(e -> {
            e.consume();
        });

        pane.setOnMouseDragged(e -> {
            e.consume();
        });

        pane.setOnMouseReleased(e -> {
            e.consume();
        });

        return pane;
    }
}