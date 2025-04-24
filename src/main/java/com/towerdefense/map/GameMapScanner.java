package com.towerdefense.map;

// After WAVE_DATA is not used yet.

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameMapScanner {

    private ArrayList<String> levelPath;
    private int width, height;
    private Scanner input;
    private File file;

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

    public ArrayList<String> getPath() throws IOException {
        resetScanner();
        levelPath.clear();

        while (input.hasNext()) {
            String line = input.nextLine();

            for (int rowChecker = 0; rowChecker < line.length(); rowChecker++) {
                if (!line.contains("WIDTH") && !line.contains("HEIGHT") && Character.isDigit(line.charAt(rowChecker)))
                    levelPath.add(line.charAt(rowChecker) + "");
            }

            if (line.equals("WAVE_DATA:"))
                break;
        }
        return levelPath;
    }
}
