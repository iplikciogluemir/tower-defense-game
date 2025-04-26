package com.towerdefense.towers;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class MissileLauncherTower extends Tower {

    public MissileLauncherTower(int x, int y, int cost, int range, int damage, int fireRate) {
        super(x, y, cost, range, damage, fireRate);
    }

    @Override
    public void attack() {
        // implement the attack method :)
    }

    public static Group getMissileLauncherTower() {

        Rectangle r1 = new Rectangle(0, 0, 6, 7);
        r1.setStyle("-fx-fill: #cbb296");

        Rectangle r2 = new Rectangle(6, 0, 3, 7);
        r2.setStyle("-fx-fill: #8b633a");

        Rectangle r3 = new Rectangle(12, 0, 6, 7);
        r3.setStyle("-fx-fill: #cbb296");

        Rectangle r4 = new Rectangle(18, 0, 3, 7);
        r4.setStyle("-fx-fill: #8b633a");

        Rectangle r5 = new Rectangle(24, 0, 6, 7);
        r5.setStyle("-fx-fill: #cbb296");

        Rectangle r6 = new Rectangle(30, 0, 3, 7);
        r6.setStyle("-fx-fill: #8b633a");

        Rectangle r7 = new Rectangle(36, 0, 6, 7);
        r7.setStyle("-fx-fill: #8b633a");

        Rectangle r8 = new Rectangle(42, 0, 3, 7);
        r8.setStyle("-fx-fill: #8b633a");

        Rectangle r9 = new Rectangle(12, 22, 7, 15);
        r9.setStyle("-fx-fill: #8b633a");

        Rectangle r10 = new Rectangle(12, 52, 7, 15);
        r10.setStyle("-fx-fill: #8b633a");

        Rectangle r11 = new Rectangle(0, 7, 30, 60);
        r11.setStyle("-fx-fill: #cbb296");

        Rectangle r12 = new Rectangle(30, 7, 15, 60);
        r12.setStyle("-fx-fill: #8b633a");

        Rectangle r13 = new Rectangle(60, 0, 6, 7);
        r13.setStyle("-fx-fill: #cbb296");

        Rectangle r14 = new Rectangle(66, 0, 3, 7);
        r14.setStyle("-fx-fill: #8b633a");

        Rectangle r15 = new Rectangle(72, 0, 6, 7);
        r15.setStyle("-fx-fill: #cbb296");

        Rectangle r16 = new Rectangle(78, 0, 3, 7);
        r16.setStyle("-fx-fill: #8b633a");

        Rectangle r17 = new Rectangle(84, 0, 6, 7);
        r17.setStyle("-fx-fill: #cbb296");

        Rectangle r18 = new Rectangle(90, 0, 3, 7);
        r18.setStyle("-fx-fill: #8b633a");

        Rectangle r19 = new Rectangle(96, 0, 6, 7);
        r19.setStyle("-fx-fill: #8b633a");

        Rectangle r20 = new Rectangle(102, 0, 3, 7);
        r20.setStyle("-fx-fill: #8b633a");

        Rectangle r21 = new Rectangle(72, 22, 7, 15);
        r21.setStyle("-fx-fill: #8b633a");

        Rectangle r22 = new Rectangle(72, 52, 7, 15);
        r22.setStyle("-fx-fill: #8b633a");

        Rectangle r23 = new Rectangle(60, 7, 30, 60);
        r23.setStyle("-fx-fill: #cbb296");

        Rectangle r24 = new Rectangle(90, 7, 15, 60);
        r24.setStyle("-fx-fill: #8b633a");

        Rectangle r25 = new Rectangle(38, 38, 15, 29);
        r25.setStyle("-fx-fill: #8b633a");

        Rectangle r26 = new Rectangle(30, 25, 33, 42);
        r26.setStyle("-fx-fill: #cbb296");

        Group tower = new Group();
        tower.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19,
                r20, r21, r22, r23, r24, r25, r26);

        r11.toBack();
        r23.toBack();
        r25.toFront();

        return tower;

    }

}
