import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    String playerSign;

    public void selectPlayerSign() {

        Scanner SignScanner = new Scanner(System.in);
        System.out.println("Выберите чем будете ходить: X или O");

        while (true) {
            String input = SignScanner.next();

            if (input.length() == 1) {
                if (input.equalsIgnoreCase("x") || input.equalsIgnoreCase("х")) {
                    playerSign = "X";
                    break;
                } else {
                    if (input.equalsIgnoreCase("o") || input.equalsIgnoreCase("о") || input.equalsIgnoreCase("0")) {
                        playerSign = "O";
                        break;
                    } else {
                        System.out.println("Введите X или O");
                    }
                }
            } else {
                System.out.println("Введите X или O");
            }
        }
    }


    public ArrayList<Integer> checkInputMove() {

        int xy;
        String xyCoord;
        ArrayList<Integer> coordinatesList = new ArrayList<Integer>();


        while (true) {
            Scanner coordinatesScanner = new Scanner(System.in);
            String coordinates = coordinatesScanner.nextLine();
            //проверяем введенные пользователем координаты
            //на длинну строки(3 элемента) и пробел вторым элементом
            if (coordinates.trim().length() == 3 && coordinates.trim().indexOf(' ') == 1) {
                //если да, то проверяем что первый и последний элмент это цифры
                if (Character.isDigit(coordinates.charAt(0)) && Character.isDigit(coordinates.charAt(2))) {
                    //если да, преобразуем String в int
                    xy = Integer.parseInt(coordinates.replaceAll("\\s", ""));
                    //проверяем на корректность введенные координаты
                    if ((xy >= 0 && xy <= 2) || (xy >= 10 && xy <= 12) || (xy >= 20 && xy <= 22)) {
                        break;
                    } else {
                        System.out.println("Введите координаты: две цифры через пробел в формате Y X. " +
                                "Левый верхний угол имеет координаты 0 0, правый нижний угол имеет координаты 2 2");
                    }
                } else {
                    System.out.println("Введите координаты: две цифры через пробел в формате Y X. Левый верхний угол имеет координаты 0 0");
                }
            } else {
                System.out.println("Введите координаты: две цифры через пробел в формате Y X. Левый верхний угол имеет координаты 0 0");
            }
        }

        //это нужно, тк при координатах 0 0, в ху после преобразований получится просто 0, а при координатах 0 1, получится 1.
        //а нам надо получить и вернуть две координаты
        switch (xy) {
            case 0:
                xyCoord = "00";
                break;
            case 1:
                xyCoord = "01";
                break;
            case 2:
                xyCoord = "02";
                break;
            default:
                xyCoord = String.valueOf(xy);
                break;
        }
        //получаем координаты в int и добавляем их в коллекцию
        for (int i = 0; i < xyCoord.length(); i++) {
            int j = Character.digit(xyCoord.charAt(i), 10);
            coordinatesList.add(j);
        }

        return coordinatesList;
    }
}
