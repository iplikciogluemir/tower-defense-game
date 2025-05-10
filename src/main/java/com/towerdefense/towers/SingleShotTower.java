package com.towerdefense.towers;

import com.towerdefense.ui.GameColors;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class SingleShotTower extends Tower {

    public SingleShotTower(int x, int y, int cost, int range, int damage, int fireRate) {
        super(x, y, cost, range, damage, fireRate);
    }

    @Override
    public void attack() {
        // implement the attack method :)
    }

    public static Group getSingleShotTower() {
        Rectangle r1 = new Rectangle(0, 10, 40, 40);
        r1.setStyle("-fx-fill: " + GameColors.getSingleShotTowerColor1());

        Rectangle r4 = new Rectangle(8, 0, 4, 10);
        r4.setStyle("-fx-fill: " + GameColors.getSingleShotTowerColor2());

        Rectangle r13 = new Rectangle(40, 0, 4, 10);
        r13.setStyle("-fx-fill: " + GameColors.getSingleShotTowerColor2());

        Rectangle r14 = new Rectangle(58, 0, 4, 10);
        r14.setStyle("-fx-fill: " + GameColors.getSingleShotTowerColor2());

        Rectangle r10 = new Rectangle(0, 0, 8, 10);
        r10.setStyle("-fx-fill: " + GameColors.getSingleShotTowerColor1());

        Rectangle r11 = new Rectangle(16, 0, 8, 10);
        r11.setStyle("-fx-fill: " + GameColors.getSingleShotTowerColor1());

        Rectangle r12 = new Rectangle(32, 0, 8, 10);
        r12.setStyle("-fx-fill: " + GameColors.getSingleShotTowerColor1());

        Rectangle r2 = new Rectangle(40, 10, 22, 40);
        r2.setStyle("-fx-fill: " + GameColors.getSingleShotTowerColor2());

        Rectangle r3 = new Rectangle(14, 30, 10, 20);
        r3.setStyle("-fx-fill: " + GameColors.getSingleShotTowerColor2());

        Rectangle r6 = new Rectangle(24, 0, 4, 10);
        r6.setStyle("-fx-fill: " + GameColors.getSingleShotTowerColor2());

        Rectangle r9 = new Rectangle(48, 0, 10, 10);
        r9.setStyle("-fx-fill: " + GameColors.getSingleShotTowerColor1());

        Group tower = new Group();
        tower.getChildren().addAll(r1, r2, r3, r4, r6, r9, r10, r11, r12, r13, r14);
        r1.toBack();
        r2.toBack();

        return tower;

    }

}