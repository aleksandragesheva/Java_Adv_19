package examsAdv.exam240219;
import java.util.Scanner;

public class Pr2TronRacers {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[][] matrix = new String[n][n];

        fillMatrix(scanner, matrix);
        Player firstPlayer = new Player("f");
        Player secondPlayer = new Player("s");
        getCoordinates(matrix, firstPlayer, firstPlayer.getPersonalSymbol());//f
        getCoordinates(matrix, secondPlayer, secondPlayer.getPersonalSymbol());//s -rabotiat

        while (true) {
            String input = scanner.nextLine();
            String[] commands = input.split("\\s+");
            String firstPlayerCommand = commands[0];
            String secondPlayerCommand = commands[1];

            movingPlayer(firstPlayerCommand, firstPlayer, matrix, secondPlayer.getPersonalSymbol());
            if (firstPlayer.getIsPlayerIsDead()) {
                break;
            }
            movingPlayer(secondPlayerCommand, secondPlayer, matrix, firstPlayer.getPersonalSymbol());
            if (secondPlayer.getIsPlayerIsDead()) {
                break;
            }
        }
        printMatrix(matrix);
    }

    private static void printMatrix(String[][] matrix) {
        for (String[] strings : matrix) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
    }

    private static void movingPlayer(String command, Player player, String[][] matrix, String personalSymbol) {
        switch (command) {
            case "left":
                player.setY(player.getY() - 1);
                if (player.getY() < 0) {
                    player.setY(matrix[0].length - 1);
                }
                checkingPlayer(player, matrix, personalSymbol);
                break;
            case "right":
                player.setY(player.getY() + 1);
                if (player.getY() > matrix[0].length - 1) {
                    player.setY(0);
                }
                checkingPlayer(player, matrix, personalSymbol);
                break;
            case "up":
                player.setX(player.getX() - 1);
                if (player.getX() < 0) {
                    player.setX(matrix.length - 1);
                }
                checkingPlayer(player, matrix, personalSymbol);
                break;
            case "down":
                player.setX(player.getX() + 1);
                if (player.getX() > matrix.length - 1) {
                    player.setX(0);
                }
                checkingPlayer(player, matrix, personalSymbol);
                break;
        }
    }

    private static void checkingPlayer(Player player, String[][] matrix, String enemyPersonalSymbol) {
        if (matrix[player.getX()][player.getY()].equals(enemyPersonalSymbol)) {
            player.setPlayerIsDead();
            matrix[player.getX()][player.getY()] = "x";
        } else {
            matrix[player.getX()][player.getY()] = player.getPersonalSymbol();
        }
    }

    private static void fillMatrix(Scanner scanner, String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            String input = scanner.nextLine();
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = input.charAt(j) + "";
            }
        }
    }

    private static void getCoordinates(String[][] matrix, Player player, String symbol) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals(symbol)) {
                    player.setX(i);
                    player.setY(j);
                }
            }
        }
    }
}

class Player {

    private int x;
    private int y;
    private boolean playerIsDead;
    private String personalSymbol;

    Player(String personalSymbol) {
        this.x = -1;
        this.y = -1;
        this.playerIsDead = false;
        this.personalSymbol = personalSymbol;
    }

    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }

    boolean getIsPlayerIsDead() {
        return playerIsDead;
    }

    void setPlayerIsDead() {
        this.playerIsDead = true;
    }

    String getPersonalSymbol() {
        return personalSymbol;
    }

}