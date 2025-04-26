package com.towerdefense.projectiles;

public abstract class Projectile {
    protected double x;
    protected double y;
    protected double speed;
    protected int damage;
    protected double direction;
    protected boolean active;

    public Projectile(double x, double y, double speed, int damage, double direction) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.direction = direction;
        this.active = true;
    }

    public abstract void update();

    public abstract void render();

    // Getters and setters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Movement calculation
    protected void move() {
        x += Math.cos(direction) * speed;
        y += Math.sin(direction) * speed;
    }
}