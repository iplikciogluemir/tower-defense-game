package com.towerdefense.ui;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;

import java.io.IOException;

import com.towerdefense.towers.LaserTower;
import com.towerdefense.towers.MissileLauncherTower;
import com.towerdefense.towers.SingleShotTower;
import com.towerdefense.towers.TripleShotTower;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TowerPanel {

    public static VBox getTowerPanel(BorderPane uiPane) throws IOException {

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

        HUDVariables.setLives(5);
        HUDVariables.setMoney(1000);
        HUDVariables.setTime(10);

        Text livesText = new Text("Lives: " + HUDVariables.getLives());
        livesText.setFont(Font.font(24));
        livesText.setFill(Color.web("#672b00"));
        HUDVariables.setLivesText(livesText);

        Text moneyText = new Text("Money: " + HUDVariables.getMoney() + "$");
        moneyText.setFont(Font.font(24));
        moneyText.setFill(Color.web("#672b00"));
        HUDVariables.setMoneyText(moneyText);

        Text countText = new Text("Next Wave: " + HUDVariables.counterViewer());
        HUDVariables.setCountText(countText);
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

        DragTowers dragMechanism = new DragTowers(uiPane);

        dragMechanism.draggableTower(singleShotTower, SingleShotTower.getSingleShotTower());
        dragMechanism.draggableTower(laserTower, LaserTower.getLaserTower());
        dragMechanism.draggableTower(tripleShotTower, TripleShotTower.getTripleShotTower());
        dragMechanism.draggableTower(missileLauncherTower, MissileLauncherTower.getMissileLauncherTower());

        return vbx;
    }
}