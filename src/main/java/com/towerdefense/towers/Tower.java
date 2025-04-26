package com.towerdefense.towers;

public abstract class Tower {
    protected int x;
    protected int y;
    protected int cost;
    protected int range;
    protected int damage;
    protected int fireRate;
    protected boolean isPlaced;

    public Tower(int x, int y, int cost, int range, int damage, int fireRate) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.range = range;
        this.damage = damage;
        this.fireRate = fireRate;
        this.isPlaced = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCost() {
        return cost;
    }

    public int getRange() {
        return range;
    }

    public int getDamage() {
        return damage;
    }

    public int getFireRate() {
        return fireRate;
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void place() {
        this.isPlaced = true;
    }

    public abstract void attack();
}