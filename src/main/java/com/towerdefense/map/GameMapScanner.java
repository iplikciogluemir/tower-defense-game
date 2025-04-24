package com.towerdefense.map;

// After WAVE_DATA is not used yet.

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class GameMapScanner {

    ArrayList<String> levelPath;
    int width, height;
    Scanner input;
    File file;

    public GameMapScanner(File file) throws Exception {
        this.file = file;
        levelPath = new ArrayList<>();
        width = 0;
        height = 0;
        input = new Scanner(file);
    }

    private void resetScanner() throws Exception {
        if (input != null)
            input.close();

        input = new Scanner(file);
    }

    public int getWidth() throws Exception {
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

    public int getHeight() throws Exception {
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

    public ArrayList<String> getPath() throws Exception {
        resetScanner();
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
