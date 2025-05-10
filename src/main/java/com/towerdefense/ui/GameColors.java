package com.towerdefense.ui;

public class GameColors {

    private static boolean isDarkMode = false;

    private static String backgroundColor = "#faf1da";
    private static String enemyPathColor = "#f2e0c8";
    private static String gridColor1 = "#fac443";
    private static String gridColor2 = "#fbd058";
    private static String buttonBackgroundColor = "#f2d79d";
    private static String buttonBorderColor = "#eed399";
    private static String textColor = "#582b0d";
    private static String enemyHeadColor = "#885b21";
    private static String enemyBodyColor = "#b32223";
    private static String healthBarColor = "#b32223";
    private static String explosionParticleCircleColor = "#b32223";
    private static String bulletColor = "#FF0000";
    private static String laserColor = "#FF0000";
    private static String missileBulletColor = "#FF0000";
    private static String dragCircleColor = "#FF0000";
    private static String laserTowerColor1 = "#cbb296";
    private static String laserTowerColor2 = "#8b633a";
    private static String missileLauncherTowerColor1 = "#cbb296";
    private static String missileLauncherTowerColor2 = "#8b633a";
    private static String singleShotTowerColor1 = "#cbb296";
    private static String singleShotTowerColor2 = "#8b633a";
    private static String tripleShotTowerColor1 = "#cbb296";
    private static String tripleShotTowerColor2 = "#8b633a";

    public static void setDarkMode(boolean darkMode) {
        GameColors.isDarkMode = darkMode;
        updateColors();
    }

    public static boolean isDarkMode() {
        return isDarkMode;
    }

    public static String getEnemyPathColor() {
        return enemyPathColor;
    }

    public static String getGridColor1() {
        return gridColor1;
    }

    public static String getGridColor2() {
        return gridColor2;
    }

    public static String getBackgroundColor() {
        return backgroundColor;
    }

    public static String getButtonBackgroundColor() {
        return buttonBackgroundColor;
    }

    public static String getButtonBorderColor() {
        return buttonBorderColor;
    }

    public static String getTextColor() {
        return textColor;
    }

    public static String getEnemyHeadColor() {
        return enemyHeadColor;
    }

    public static String getEnemyBodyColor() {
        return enemyBodyColor;
    }

    public static String getHealthBarColor() {
        return healthBarColor;
    }

    public static String getExplosionParticleCircleColor() {
        return explosionParticleCircleColor;
    }

    public static String getBulletColor() {
        return bulletColor;
    }

    public static String getLaserColor() {
        return laserColor;
    }

    public static String getMissileBulletColor() {
        return missileBulletColor;
    }

    public static String getDragCircleColor() {
        return dragCircleColor;
    }

    public static String getLaserTowerColor1() {
        return laserTowerColor1;
    }

    public static String getLaserTowerColor2() {
        return laserTowerColor2;
    }

    public static String getMissileLauncherTowerColor1() {
        return missileLauncherTowerColor1;
    }

    public static String getMissileLauncherTowerColor2() {
        return missileLauncherTowerColor2;
    }

    public static String getSingleShotTowerColor1() {
        return singleShotTowerColor1;
    }

    public static String getSingleShotTowerColor2() {
        return singleShotTowerColor2;
    }

    public static String getTripleShotTowerColor1() {
        return tripleShotTowerColor1;
    }

    public static String getTripleShotTowerColor2() {
        return tripleShotTowerColor2;
    }

    private static void updateColors() {

        backgroundColor = isDarkMode ? "#1b1d2b" : "#faf1da";

        // MapCell
        enemyPathColor = isDarkMode ? "#d19a33" : "#f2e0c8";
        gridColor1 = isDarkMode ? "#3c3f58" : "#fac443";
        gridColor2 = isDarkMode ? "#2e3044" : "#fbd058";

        // TowerPanel - GameUI
        buttonBackgroundColor = isDarkMode ? "#7e57c2" : "#f2d79d";
        buttonBorderColor = isDarkMode ? "#14151f" : "#eed399";
        textColor = isDarkMode ? "#e0e0e0" : "#582b0d";

        // Enemy
        enemyHeadColor = isDarkMode ? "#4a4d6c" : "#885b21";
        enemyBodyColor = isDarkMode ? "#5d00a8" : "#b32223";
        healthBarColor = isDarkMode ? "#5d00a8" : "#b32223";

        // Enemy Explosion
        explosionParticleCircleColor = isDarkMode ? "#5d00a8" : "#b32223";

        // Bullet - Laser - Missile
        bulletColor = isDarkMode ? "#5d00a8" : "#FF0000";
        laserColor = isDarkMode ? "#5d00a8" : "#FF0000";
        missileBulletColor = isDarkMode ? "#5d00a8" : "#FF0000";

        // DragTowers
        dragCircleColor = isDarkMode ? "#b8860b" : "#FF0000";

        // Towers (Laser - Missile SingleShot - TripleShot)
        laserTowerColor1 = isDarkMode ? "#ffcc00" : "#cbb296";
        laserTowerColor2 = isDarkMode ? "#5d00a8" : "#8b633a";

        missileLauncherTowerColor1 = isDarkMode ? "#ffcc00" : "#cbb296";
        missileLauncherTowerColor2 = isDarkMode ? "#5d00a8" : "#8b633a";

        singleShotTowerColor1 = isDarkMode ? "#ffcc00" : "#cbb296";
        singleShotTowerColor2 = isDarkMode ? "#5d00a8" : "#8b633a";

        tripleShotTowerColor1 = isDarkMode ? "#ffcc00" : "#cbb296";
        tripleShotTowerColor2 = isDarkMode ? "#5d00a8" : "#8b633a";

    }
}
