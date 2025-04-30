package com.towerdefense.map;

// After WAVE_DATA is not used yet.

import java.util.Scanner;

import javafx.geometry.Point3D;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameMapScanner {

    private ArrayList<String> levelPath;
    private int width, height;
    private Scanner input;
    private File file;
    private ArrayList<Point3D> waveData = new ArrayList<>();

    public GameMapScanner(File file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }
        if (!file.exists() || !file.isFile()) {
            throw new IOException("Invalid file");
        }
        this.file = file;
        levelPath = new ArrayList<>();
        width = 0;
        height = 0;
        input = new Scanner(file);
    }

    public GameMapScanner() {

    }

    private void resetScanner() throws IOException {
        if (input != null)
            input.close();

        input = new Scanner(file);
    }

    public int getWidth() throws IOException {
        resetScanner();
        while (input.hasNext()) {
            String line = input.nextLine();
            if (line.contains("WIDTH:")) {
                width = Integer.parseInt(line.split(":")[1].trim());
                return width;
            }
        }
        return 0;
    }

    public int getHeight() throws IOException {
        resetScanner();
        while (input.hasNext()) {
            String line = input.nextLine();
            if (line.contains("HEIGHT:")) {
                height = Integer.parseInt(line.split(":")[1].trim());
                return height;
            }
        }
        return 0;
    }

    public ArrayList<Point3D> getWaveData() {
        return waveData;
    }

    public void setWaveData(ArrayList<Point3D> waveData) {
        if (waveData == null) {
            throw new IllegalArgumentException("Wave data cannot be null");
        }
        this.waveData = waveData;
    }

    public ArrayList<String> getPath() throws IOException {
        resetScanner();
        levelPath.clear();

        while (input.hasNext()) {
            String line = input.nextLine();

            if (line.equals("WAVE_DATA:")) {
                while (input.hasNext()) {
                    String waveLine = input.nextLine();
                    String[] wavePoints = waveLine.split(",");
                    double x = Double.parseDouble(wavePoints[0].trim());
                    double y = Double.parseDouble(wavePoints[1].trim());
                    double z = Double.parseDouble(wavePoints[2].trim());
                    waveData.add(new Point3D(x, y, z));
                }
            }

            if (!line.contains("WIDTH") && !line.contains("HEIGHT")) {
                String[] coordinate = line.split(",");

                for (String singleCoordinate : coordinate)
                    levelPath.add(singleCoordinate);
            }
        }
        return levelPath;
    }
  



    public double getEnemyCount(int waveIndex){
        return waveData.get(waveIndex-1).getX();
    }
    public double getEnemyInterval(int waveIndex){
        return waveData.get(waveIndex-1).getY();
    }
    public double getWaveDelay(int waveIndex){
        return waveData.get(waveIndex-1).getZ();
    }
    public int getWaveCount (){
        return waveData.size();
    }

}