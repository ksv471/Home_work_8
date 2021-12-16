package Home_work_8;

import java.util.Random;
import java.util.Scanner;

public class Logic {

    static int SIZE = 3;
    static int DOT_WIN = 3;

    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '•';

    static char[][] map;

    static Random random = new Random();

    static boolean gameFinished;

    static void go() {
        gameFinished = true;
        printMap();
        if(checkWin(DOT_X, DOT_WIN)){
            System.out.println("Поздравляем! Вы выйграли!");
            return;
        }
        if(isFull()){
            System.out.println("Ничья");
            return;
        }

        aiTurn();
        printMap();
        if(checkWin(DOT_O, DOT_WIN)) {
            System.out.println("Компьютер победил.");
            return;
        }
        if(isFull()){
            System.out.println("Ничья");
            return;
        }

        gameFinished = false;
    }
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    public static void printMap() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%c ", map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void humanTurn(int x, int y) {
        if(isCellValid(y,x)) {
            map[y][x] = DOT_X;
            go();
        }
    }
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }
    public static void aiTurn() {
        int x;
        int y;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i,j)){
                    map[i][j] = DOT_O;
                    if(checkWin(DOT_O, DOT_WIN)) {
                        return;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i,j)){
                    map[i][j] = DOT_X;
                    if(checkWin(DOT_X, DOT_WIN)) {
                        map[i][j] = DOT_O;
                        return;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i,j)){
                    map[i][j] = DOT_O;
                    if(checkWin(DOT_O, DOT_WIN - 1) && Math.random() < 0.5) {
                        return;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i,j)){
                    map[i][j] = DOT_X;
                    if(checkWin(DOT_X, DOT_WIN - 1) && Math.random() < 0.5) {
                        map[i][j] = DOT_O;
                        return;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[x][y] = DOT_O;
    }

    public static boolean isFull(){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkLine(int a, int b, int c, int d, char dots, int dotWin) {
        if (a + c * (dotWin - 1) > SIZE - 1 || b + c * (dotWin - 1) > SIZE - 1 || b +d * (dotWin - 1) < 0)
        {
            return false;
        }
        for (int i = 0; i < dotWin; i++) {
            if (map[a + i * c][b + i * d] != dots){
                return false;
            }
        }
        return true;
    }

    public static boolean checkWin(char dots, int dotWin) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (checkLine(i, j, 0, 1, dots, dotWin) ||
                        checkLine(i, j, 1, 0, dots, dotWin) ||
                        checkLine(i, j, 1, 1, dots, dotWin) ||
                        checkLine(i, j, -1, 1, dots, dotWin)) {
                    return true;
                }
            }
        }
        return false;
    }
}