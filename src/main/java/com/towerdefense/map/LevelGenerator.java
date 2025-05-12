package com.towerdefense.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.geometry.Point2D;
import java.util.Random;

public class LevelGenerator {
    static int maxHeight = 15;
    static int minHeight = 10;
    static int maxWidth = 15;
    static int minWidth = 10;

    public static ArrayList<Point2D> mapGenerator() {
        ArrayList<Point2D> path = new ArrayList<>();
        Random rn = new Random();
        int height = rn.nextInt(maxHeight - minHeight + 1) + minHeight;
        int width = rn.nextInt(maxWidth - minWidth + 1) + minWidth;

        path.add(new Point2D(height, width));

        ArrayList<Point2D> startingPos = new ArrayList<>();
        for (int i = height - 1; i >= 0; i--) {
            startingPos.add(new Point2D(i, 0));
        }
        for (int j = 1; j < width; j++) {
            startingPos.add(new Point2D(0, j));
        }

        ArrayList<Point2D> endingPos = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            endingPos.add(new Point2D(i, width - 1));
        }
        for (int j = width - 2; j >= 0; --j) {
            endingPos.add(new Point2D(height - 1, j));
        }
        Collections.shuffle(startingPos, rn);
        Collections.shuffle(endingPos, rn);

        int currX = (int) startingPos.get(0).getX();
        int currY = (int) startingPos.get(0).getY();

        int endX = (int) endingPos.get(0).getX();
        int endY = (int) endingPos.get(0).getY();
        path.add(new Point2D(currX, currY));

        while (currX != endX || currY != endY) {
            List<Point2D> moves = new ArrayList<>();
            if (currX < endX) {
                moves.add(new Point2D(currX + 1, currY));
            } else if (currX > endX) {
                moves.add(new Point2D(currX - 1, currY));
            }

            if (currY < endY) {
                moves.add(new Point2D(currX, currY + 1));
            } else if (currY > endY) {
                moves.add(new Point2D(currX, currY - 1));
            }

            if (moves.isEmpty()) {
                break;
            }

            Point2D nextMove;
            if (moves.size() == 1) {
                nextMove = moves.get(0);
            } else {
                Collections.shuffle(moves, rn);
                nextMove = moves.get(0);
            }

            currX = (int) nextMove.getX();
            currY = (int) nextMove.getY();

            path.add(nextMove);
        }

        return path;
    }

    public static void main(String[] args) {
        ArrayList<Point2D> path = mapGenerator();
        for (Point2D point : path) {
            System.out.println("(" + (int) point.getX() + ", " + (int) point.getY() + ")");
        }
    }
}