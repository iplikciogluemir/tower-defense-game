package com.towerdefense.enemies;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class Enemy {
    private int health;
    private int X;
    private int Y;
    private int dx;
    private int dy;

    public Enemy(int health, int speed, int X, int Y, int dx, int dy) {
        this.health = health;
        this.X = X;
        this.Y = Y;
        this.dx = dx;
        this.dy = dy;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return this.health;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public static Group getEnemy() {
        Polygon body = new Polygon();
        body.getPoints().addAll(
                7.0, 7.0,
                0.0, 30.0,
                14.0, 30.0);
        body.setStyle("-fx-fill: #b32223");

        Circle head = new Circle(7, 7, 7);
        head.setStyle("-fx-fill: #885b21");

        Group enemyPawn = new Group();
        enemyPawn.getChildren().addAll(head, body);
        head.toFront();

        return enemyPawn;
    }
}
