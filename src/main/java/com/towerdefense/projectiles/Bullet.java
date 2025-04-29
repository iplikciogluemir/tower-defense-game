package com.towerdefense.projectiles;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;

public class Bullet extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        shoot(pane, 50, 50, 100, 100);
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setTitle("test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void shoot(Pane pane, double towerX, double towerY, double enemyX, double enemyY) {
        Circle bullet = new Circle(5, Color.RED);
        bullet.setCenterX(towerX);
        bullet.setCenterY(towerY);

        double dX = enemyX - towerX;
        double dY = enemyY - towerY;
        double distance = Projectile.getDistance(towerX, towerY, enemyX, enemyY);

        TranslateTransition bulletTransition = new TranslateTransition();
        bulletTransition.setNode(bullet);
        bulletTransition.setDuration(Duration.seconds(distance / 200));
        bulletTransition.setByX(dX);
        bulletTransition.setByY(dY);
        bulletTransition.setOnFinished(e -> {
            ((Pane) bullet.getParent()).getChildren().remove(bullet);
        });

        pane.getChildren().add(bullet);

        bulletTransition.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}