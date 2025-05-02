package com.towerdefense.enemies;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Enemy {
    private int health;
    private int speed;
    private Group enemyGroup;

    public Enemy(int health, int speed) {
        this.health = health;
        this.speed = speed;
        this.enemyGroup = getEnemyShape();

    }

    public Enemy() {
        this.health = 100;
        this.speed = 1;
        this.enemyGroup = getEnemyShape();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public Group getEnemy(){
        return enemyGroup;
    }

    private Group getEnemyShape() {
        Polygon body = new Polygon();
        body.getPoints().addAll(
                7.0, 7.0,
                0.0, 30.0,
                14.0, 30.0);
        body.setStyle("-fx-fill: #b32223");

        Circle head = new Circle(7, 7, 7);
        head.setStyle("-fx-fill: #885b21");

        Rectangle healthBar = new Rectangle(24, 5);
        healthBar.setFill(Color.web("#b32223"));
        healthBar.setLayoutX(7 - (healthBar.getWidth() / 2));
        healthBar.setLayoutY(-10);

        Group enemyPawn = new Group();
        enemyPawn.getChildren().addAll(head, body, healthBar);
        head.toFront();

        return enemyPawn;
    }

    public static void getSingleHit(Group group) {
        for (Node node : group.getChildren()) {
            if (node instanceof Rectangle) {
                Rectangle modifiedRectangle = (Rectangle) node;
                modifiedRectangle.setWidth(modifiedRectangle.getWidth() - 8);
                break;
            }
        }
    }

    public static boolean isDead(Group group) {
        Rectangle checkingRectangle = null;
        for (Node node : group.getChildren()) {
            if (node instanceof Rectangle) {
                checkingRectangle = (Rectangle) node;
                break;
            }
        }

        double width = checkingRectangle.getWidth();

        if (width == 0)
            return true;
        else
            return false;
    }
}
