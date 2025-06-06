package com.towerdefense.ui;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;

import com.towerdefense.game.DragTowers;
import com.towerdefense.map.MapCell;
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

        public static VBox getTowerPanel(BorderPane uiPane) {

                VBox vbx = new VBox(10);
                final double prefWidth = 200;

                String css = "-fx-background-color: " + GameColors.getButtonBackgroundColor() + ";" +
                                "-fx-border-color: " + GameColors.getButtonBorderColor() + ";" +
                                "-fx-border-width: 2;" +
                                "-fx-padding:5;" +
                                "-fx-text-fill: " + GameColors.getTextColor() + ";" + "-fx-border-radius: 7px;" +
                                "-fx-background-radius: 7px;" +
                                "-fx-pref-width:" + prefWidth + ";" +
                                "-fx-alignment:center";

                HUDVariables.setLives(5);
                Text livesText = new Text("Lives: " + HUDVariables.getLives());
                livesText.setFont(Font.font(24));
                livesText.setFill(Color.web(GameColors.getTextColor()));
                HUDVariables.setLivesText(livesText);

                Text moneyText = new Text("Money: " + HUDVariables.getMoney() + "$");
                moneyText.setFont(Font.font(24));
                moneyText.setFill(Color.web(GameColors.getTextColor()));
                HUDVariables.setMoneyText(moneyText);

                Text countText = new Text("Next Wave: " + HUDVariables.counterViewer());
                HUDVariables.setCountText(countText);
                countText.setFont(Font.font(24));
                countText.setFill(Color.web(GameColors.getTextColor()));

                Text remainingText = new Text("Remaining Waves: " + MapCell.currMap.getWaveCount());
                remainingText.setFont(Font.font(24));
                remainingText.setFill(Color.web(GameColors.getTextColor()));
                HUDVariables.setRemainingText(remainingText);

                vbx.setStyle("-fx-background-color: " + GameColors.getBackgroundColor());
                Label singleShotTower = new Label("Single Shot Tower - 50$", SingleShotTower.getSingleShotTower());
                singleShotTower.setContentDisplay(ContentDisplay.TOP);
                singleShotTower.setStyle(css);

                Label laserTower = new Label("Laser Tower - 120$", LaserTower.getLaserTower());
                laserTower.setContentDisplay(ContentDisplay.TOP);
                laserTower.setStyle(css);

                Label tripleShotTower = new Label("Triple Shot Tower - 150$", TripleShotTower.getTripleShotTower());
                tripleShotTower.setContentDisplay(ContentDisplay.TOP);
                tripleShotTower.setStyle(css);

                Label missileLauncherTower = new Label("Missile Launcher Tower - 200$",
                                MissileLauncherTower.getMissileLauncherTower());
                missileLauncherTower.setContentDisplay(ContentDisplay.TOP);
                missileLauncherTower.setStyle(css);

                vbx.getChildren().addAll(livesText, moneyText, countText, remainingText, singleShotTower, laserTower,
                                tripleShotTower,
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