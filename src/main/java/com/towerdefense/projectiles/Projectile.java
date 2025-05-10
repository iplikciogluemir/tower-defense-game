package com.towerdefense.projectiles;

public abstract class Projectile {
    public static double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.abs(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2)));
    }
}