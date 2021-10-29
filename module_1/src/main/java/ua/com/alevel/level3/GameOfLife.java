package ua.com.alevel.level3;

import ua.com.alevel.UserInterface;

public class GameOfLife {

    int width;
    int height;
    int[][] board;

    public GameOfLife(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
    }

    public static void start() {
        GameOfLife simulation = new GameOfLife(8, 8);

        simulation.setAlive(1, 1);
        simulation.setAlive(1, 6);
        simulation.setAlive(2, 3);
        simulation.setAlive(2, 4);
        simulation.setAlive(3, 2);
        simulation.setAlive(3, 3);
        simulation.setAlive(3, 4);
        simulation.setAlive(3, 5);
        simulation.setAlive(4, 2);
        simulation.setAlive(4, 3);
        simulation.setAlive(4, 4);
        simulation.setAlive(4, 5);
        simulation.setAlive(5, 3);
        simulation.setAlive(5, 4);
        simulation.setAlive(6, 1);
        simulation.setAlive(6, 6);

        for (int i = 0; i < 100; i++) {

            simulation.printBoard();

            simulation.step();
        }
        UserInterface.SelectThirdLevel();
    }

    public void printBoard() {
        System.out.println("----------");
        for (int y = 0; y < height; y++) {
            String line = "|";
            for (int x = 0; x < width; x++) {
                if (this.board[x][y] == 0) {
                    line += ".";
                } else {
                    line += "*";
                }
            }
            line += "|";
            System.out.println(line);
        }
        System.out.println("----------\n");
    }

    public void setAlive(int x, int y) {
        this.board[x][y] = 1;
    }

    public void setDead(int x, int y) {
        this.board[x][y] = 0;
    }

    public int countAliveNeighbours(int x, int y) {
        int count = 0;

        count += getState(x - 1, y - 1);
        count += getState(x, y - 1);
        count += getState(x + 1, y - 1);

        count += getState(x - 1, y);
        count += getState(x + 1, y);

        count += getState(x - 1, y + 1);
        count += getState(x, y + 1);
        count += getState(x + 1, y + 1);

        return count;
    }

    public int getState(int x, int y) {
        if (x < 0 || x >= width) {
            return 0;
        }

        if (y < 0 || y >= height) {
            return 0;
        }
        return this.board[x][y];
    }

    public void step() {
        int[][] newBoard = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int aliveNeighbours = countAliveNeighbours(x, y);

                if (getState(x, y) == 0) {
                    if (aliveNeighbours < 2) {
                        newBoard[x][y] = 0;
                    } else if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                        newBoard[x][y] = 1;
                    } else if (aliveNeighbours > 3) {
                        newBoard[x][y] = 0;
                    }
                } else {
                    if (aliveNeighbours == 3) {
                        newBoard[x][y] = 1;
                    }
                }

            }
        }
        this.board = newBoard;
    }
}
