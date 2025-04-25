package com.towerdefense.towers;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class TripleShotTower {

    public static Group getTripleShotTower() {

        Rectangle r1 = new Rectangle(0, 0, 6, 7);

        Rectangle r2 = new Rectangle(6, 0, 3, 7);

        Rectangle r3 = new Rectangle(12, 0, 6, 7);

        Rectangle r4 = new Rectangle(18, 0, 3, 7);

        Rectangle r5 = new Rectangle(24, 0, 6, 7);

        Rectangle r6 = new Rectangle(30, 0, 3, 7);

        Rectangle r7 = new Rectangle(36, 0, 6, 7);

        Rectangle r8 = new Rectangle(42, 0, 3, 7);

        Rectangle r9 = new Rectangle(12, 22, 7, 15);

        Rectangle r10 = new Rectangle(12, 52, 7, 15);

        Rectangle r11 = new Rectangle(0, 7, 30, 60);

        Rectangle r12 = new Rectangle(30, 7, 15, 60);

        Group tower = new Group();
        tower.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);

        r11.toBack();

        return tower;

    }

}