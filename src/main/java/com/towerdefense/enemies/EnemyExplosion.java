package com.towerdefense.enemies;

import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class EnemyExplosion {
    private static final int NUM_PARTICLES = 20;
    private static final double MAX_DISTANCE = 50;
    private static final double CIRCLE_RADIUS = 3;
    private static final double DURATION = 0.5;

    public static void createExplosion(Pane root, Enemy enemy) {
        Random random = new Random();
        double enemyX = enemy.getEnemy().getBoundsInParent().getCenterX();
        double enemyY = enemy.getEnemy().getBoundsInParent().getCenterY();

        for (int i = 0; i < NUM_PARTICLES; ++i) {
            Circle particle = new Circle(CIRCLE_RADIUS, Color.RED);
            particle.setCenterX(enemyX);
            particle.setCenterY(enemyY);

            root.getChildren().add(particle);

            double angle = random.nextDouble() * 2 * Math.PI;
            double distance = MAX_DISTANCE * random.nextDouble();
            double dx = Math.cos(angle) * distance;
            double dy = Math.sin(angle) * distance;

            TranslateTransition move = new TranslateTransition(Duration.seconds(DURATION), particle);
            move.setByX(dx);
            move.setByY(dy);
            move.setInterpolator(Interpolator.EASE_OUT);

            FadeTransition fade = new FadeTransition(Duration.seconds(DURATION), particle);
            fade.setFromValue(1.0);
            fade.setToValue(0);

            ParallelTransition explosion = new ParallelTransition(move, fade);
            explosion.setOnFinished(e -> root.getChildren().remove(particle));
            explosion.play();
        }
    }
}
