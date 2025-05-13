package com.towerdefense.map;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import javafx.geometry.Point2D;

public class LevelGenerator {
    private static int MIN_WIDTH = 10;
    private static final int MIN_HEIGHT = 10;
    private static final int MAX_WIDTH = 15;
    private static final int MAX_HEIGHT = 15;

    private int width;
    private int height;
    private ArrayList<Point2D> path;
    private Random random;

    public LevelGenerator() {
        this.random = new Random();
        this.path = new ArrayList<Point2D>();
    }

    public void generateLevel(String filePath) {
        height = random.nextInt(MAX_HEIGHT - MIN_HEIGHT + 1) + MIN_HEIGHT;
        MIN_WIDTH = height;
        width = random.nextInt(MAX_WIDTH - MIN_WIDTH + 1) + MIN_WIDTH;

        generatePath();

        ArrayList<String> waveData = generateWaveData();

        try {
            PrintWriter output = new PrintWriter(filePath);
            output.println("WIDTH:" + width);
            output.println("HEIGHT:" + height);

            for (Point2D p : path) {
                output.println((int) p.getY() + "," + (int) p.getX());
            }

            output.println("WAVE_DATA:");
            for (String wave : waveData) {
                output.println(wave);
            }
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generatePath() {
        int startY = random.nextInt(height / 2) + height / 4;
        Point2D current = new Point2D(0, startY);
        path.add(current);

        ArrayList<Point2D> visitedarr = new ArrayList<>();
        int currentAttempts = 0;
        int maximumAttemps = 1000;
        while ((current.getX() < width - 1) && currentAttempts < maximumAttemps) {
            Point2D next = getNextPoint(current, visitedarr);
            if (next != null) {
                path.add(next);
                current = next;
            }
            currentAttempts++;
        }
        if (current.getX() < width - 1) {
            while (current.getX() < width - 1) {
                current = new Point2D(current.getX() + 1, current.getY());
                path.add(current);
            }
        }
    }

    private Point2D getNextPoint(Point2D current, ArrayList<Point2D> visited) {
        ArrayList<Point2D> moves = new ArrayList<>();

        if (current.getX() < width - 1) {
            moves.add(new Point2D(current.getX() + 1, current.getY()));
        }

        if (current.getY() > 0) {
            moves.add(new Point2D(current.getX(), current.getY() - 1));
        }

        if (current.getY() < height - 1) {
            moves.add(new Point2D(current.getX(), current.getY() + 1));
        }

        ArrayList<Point2D> possibleMoves = new ArrayList<>();
        for (Point2D move : moves) {
            boolean isVisited = false;
            for (Point2D node : visited) {
                if (move.equals(node)) {
                    isVisited = true;
                    break;
                }
            }
            if (!isVisited) {
                possibleMoves.add(move);
            }
        }

        if (possibleMoves.isEmpty()) {
            return null;
        }

        Point2D nextMove = possibleMoves.get(random.nextInt(possibleMoves.size()));
        // visited.add(nextMove);
        for (Point2D move : moves) {
            visited.add(move);
        }
        return nextMove;
    }

    private ArrayList<String> generateWaveData() {
        ArrayList<String> waveData = new ArrayList<>();
        int numWaves = random.nextInt(3) + 3;

        for (int i = 0; i < numWaves; i++) {
            int enemyCount = (i + 1) * 5 + random.nextInt(5);
            double spawnInterval = 0.3 + (random.nextDouble() * 0.7);
            int waveDelay = 5 + random.nextInt(3);

            waveData.add(String.format(Locale.US, "%d, %.1f, %d", enemyCount, spawnInterval, waveDelay));
        }

        return waveData;
    }
}