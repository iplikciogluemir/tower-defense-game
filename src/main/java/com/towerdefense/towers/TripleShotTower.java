package com.towerdefense.towers;

import com.towerdefense.ui.GameColors;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class TripleShotTower extends Tower {

    public TripleShotTower(int x, int y, int cost, int range, int damage, int fireRate) {
        super(x, y, cost, range, damage, fireRate);
    }

    @Override
    public void attack() {
        // implement the attack method :)
    }

    public static Group getTripleShotTower() {
        Rectangle r1 = new Rectangle(0, 0, 6, 7);
        r1.setStyle("-fx-fill: " + GameColors.getTripleShotTowerColor1());

        Rectangle r2 = new Rectangle(6, 0, 3, 7);
        r2.setStyle("-fx-fill: " + GameColors.getTripleShotTowerColor2());

        Rectangle r3 = new Rectangle(12, 0, 6, 7);
        r3.setStyle("-fx-fill: " + GameColors.getTripleShotTowerColor1());

        Rectangle r4 = new Rectangle(18, 0, 3, 7);
        r4.setStyle("-fx-fill: " + GameColors.getTripleShotTowerColor2());

        Rectangle r5 = new Rectangle(24, 0, 6, 7);
        r5.setStyle("-fx-fill: " + GameColors.getTripleShotTowerColor1());

        Rectangle r6 = new Rectangle(30, 0, 3, 7);
        r6.setStyle("-fx-fill: " + GameColors.getTripleShotTowerColor2());

        Rectangle r7 = new Rectangle(36, 0, 6, 7);
        r7.setStyle("-fx-fill: " + GameColors.getTripleShotTowerColor2());

        Rectangle r8 = new Rectangle(42, 0, 3, 7);
        r8.setStyle("-fx-fill: " + GameColors.getTripleShotTowerColor2());

        Rectangle r9 = new Rectangle(12, 22, 7, 15);
        r9.setStyle("-fx-fill: " + GameColors.getTripleShotTowerColor2());

        Rectangle r10 = new Rectangle(12, 52, 7, 15);
        r10.setStyle("-fx-fill: " + GameColors.getTripleShotTowerColor2());

        Rectangle r11 = new Rectangle(0, 7, 30, 60);
        r11.setStyle("-fx-fill: " + GameColors.getTripleShotTowerColor1());

        Rectangle r12 = new Rectangle(30, 7, 15, 60);
        r12.setStyle("-fx-fill: " + GameColors.getTripleShotTowerColor2());

        Group tower = new Group();
        tower.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);

        r11.toBack();

        return tower;

    }

}