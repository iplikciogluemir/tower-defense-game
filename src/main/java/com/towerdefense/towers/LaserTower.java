package com.towerdefense.towers;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class LaserTower extends Tower {

    public LaserTower(int x, int y, int cost, int range, int damage, int fireRate) {
        super(x, y, cost, range, damage, fireRate);
    }

    @Override
    public void attack() {
        // implement the attack method :)
    }

    public static Group getLaserTower() {

        Rectangle r1 = new Rectangle(8, 0, 6, 6);
        r1.setStyle("-fx-fill: #cbb296");

        Rectangle r2 = new Rectangle(14, 0, 3, 6);
        r2.setStyle("-fx-fill: #8b633a");

        Rectangle r3 = new Rectangle(20, 0, 6, 6);
        r3.setStyle("-fx-fill: #cbb296");

        Rectangle r4 = new Rectangle(26, 0, 3, 6);
        r4.setStyle("-fx-fill: #8b633a");

        Rectangle r5 = new Rectangle(32, 0, 6, 6);
        r5.setStyle("-fx-fill: #cbb296");

        Rectangle r6 = new Rectangle(38, 0, 3, 6);
        r6.setStyle("-fx-fill: #8b633a");

        Rectangle r7 = new Rectangle(44, 0, 6, 6);
        r7.setStyle("-fx-fill: #cbb296");

        Rectangle r8 = new Rectangle(50, 0, 3, 6);
        r8.setStyle("-fx-fill: #8b633a");

        Rectangle r9 = new Rectangle(8, 6, 30, 16);
        r9.setStyle("-fx-fill: #cbb296");

        Rectangle r10 = new Rectangle(38, 6, 15, 16);
        r10.setStyle("-fx-fill: #8b633a");

        Rectangle r11 = new Rectangle(7, 22, 4, 8);
        r11.setStyle("-fx-fill: #8b633a");

        Rectangle r12 = new Rectangle(24, 22, 4, 8);
        r12.setStyle("-fx-fill: #8b633a");

        Rectangle r13 = new Rectangle(20, 30, 7, 6);
        r13.setStyle("-fx-fill: #8b633a");

        Rectangle r14 = new Rectangle(38, 30, 3, 6);
        r14.setStyle("-fx-fill: #8b633a");

        Rectangle r15 = new Rectangle(16, 52, 10, 20);
        r15.setStyle("-fx-fill: #8b633a");

        Rectangle r16 = new Rectangle(0, 22, 41, 50);
        r16.setStyle("-fx-fill: #cbb296");

        Rectangle r17 = new Rectangle(41, 22, 20, 50);
        r17.setStyle("-fx-fill: #8b633a");

        Rectangle r18 = new Rectangle(50, 22, 8, 8);
        r18.setStyle("-fx-fill: #cbb296");

        Group tower = new Group();
        tower.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18);
        r16.toBack();
        r17.toBack();

        return tower;

    }

}