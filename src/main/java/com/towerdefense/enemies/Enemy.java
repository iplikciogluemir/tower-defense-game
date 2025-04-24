package com.towerdefense.enemies;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class Enemy {
    private int health;
    private int speed;

    public Enemy(int health, int speed) {
        this.health = health;
        this.speed = speed;
    }

    // getters setters
    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return this.health;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int setSpeed() {
        return this.speed;
    }

    public Group getEnemy() {
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
