package com.towerdefense.ui;

class HUDVariables {
    private int lives;
    private int money;

    HUDVariables(int lives, int money, int timer) {
        this.lives = lives;
        this.money = money;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
}