package com.towerdefense.towers;

public class Tower {
    private int x;
    private int y;
    private int damage;
    private int range;
    private int cost;

    public Tower(int x, int y, int damage, int range, int cost) {
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.range = range;
        this.cost = cost;
    }

    // Getters and setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public int getCost() {
        return cost;
    }

    public boolean isInRange(int targetX, int targetY) {
        double distance = Math.sqrt(
                Math.pow(targetX - x, 2) + Math.pow(targetY - y, 2));
        return distance <= range;
    }
}