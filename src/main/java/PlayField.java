import java.util.ArrayList;

public class PlayField {

    String[][] field;

    public void createPlayField() {
        field = new String[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                field[x][y] = "*";
            }
        }

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                System.out.print(" " + field[x][y] + " ");
            }
            System.out.println();
        }
    }

    public Boolean checkCorrectMove(ArrayList<Integer> move) {
        int xCoord = move.get(0);
        int yCoord = move.get(1);
        if (field[xCoord][yCoord] == "*") {
            return true;
        } else {
            System.out.println("Это место занято, введите другие координаты.");
        }
        return false;
    }

    public void viewChangePlayField(ArrayList<Integer> move, String sign) {
        int xCoord = move.get(0);
        int yCoord = move.get(1);
        field[xCoord][yCoord] = sign;

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                System.out.print(" " + field[x][y] + " ");
            }
            System.out.println();
        }
    }

    public Boolean checkFreeSpace() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (field[x][y] == "*") {
                    return true;
                }
            }

        }
        return false;
    }

    //Топорная проверка на победу методом перебора всех выигрышных комбинаций
    public Boolean checkWinGame(String sign) {
        if ((field[0][0].equals(sign) && field[0][1].equals(sign) && field[0][2].equals(sign)) ||
                (field[1][0].equals(sign) && field[1][1].equals(sign) && field[1][2].equals(sign)) ||
                (field[2][0].equals(sign) && field[2][1].equals(sign) && field[2][2].equals(sign)) ||
                (field[0][0].equals(sign) && field[1][0].equals(sign) && field[2][0].equals(sign)) ||
                (field[0][1].equals(sign) && field[1][1].equals(sign) && field[2][1].equals(sign)) ||
                (field[0][2].equals(sign) && field[1][2].equals(sign) && field[2][2].equals(sign)) ||
                (field[0][0].equals(sign) && field[1][1].equals(sign) && field[2][2].equals(sign)) ||
                (field[2][0].equals(sign) && field[1][1].equals(sign) && field[0][2].equals(sign))) {
            return true;
        }
        return false;
    }
}
