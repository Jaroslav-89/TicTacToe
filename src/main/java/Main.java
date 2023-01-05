import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        PlayField playField = new PlayField();
        Player player = new Player();
        Comp comp;

        ArrayList<Integer> correctMove;

        playField.createPlayField();

        player.selectPlayerSign();

        if (player.playerSign.equals("X")) {
            comp = new Comp("O");
        } else {
            comp = new Comp("X");
        }

        System.out.println("Вы ставите: " + player.playerSign + "\nКомпьютер ставит: " + comp.compSign);

        //можно выбрать, чтобы первым ходил компьютер
        System.out.println("Если хотите, чтобы компьютер ходил перым, введите Comp.\nЕсли хотите ходить сами, введите что угодно.");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next().trim();
        if (answer.equalsIgnoreCase("Comp")) {
            //получаем координаты в правильном формате(комп)
            correctMove = comp.compMove(playField.field);

            playField.viewChangePlayField(correctMove, comp.compSign);
        }

        while (true) {

            System.out.println("Ваш ход! Для того чтобы поставить -" + player.playerSign +
                    "- введите координаты от 0 до 2 в формате: Y X (через пробел), где 0 0 это верхний левый угол");


            //ход игрока
            do {
                //получаем координаты в правильном формате(игрок)
                correctMove = player.checkInputMove();
                //проверяем не занято ли поле, куда хочет сходить игрок
            } while (!playField.checkCorrectMove(correctMove));

            playField.viewChangePlayField(correctMove, player.playerSign);

            if (playField.checkWinGame(player.playerSign)) {
                System.out.println("Вы победили!");
                break;
            }

            if (!playField.checkFreeSpace()) {
                System.out.println("Ничья!");
                break;
            }

            //ход компьютера
            do {
                //получаем координаты в правильном формате(комп)
                correctMove = comp.compMove(playField.field);
                //проверяем не занято ли поле, куда хочет сходить компьютер
            } while (!playField.checkCorrectMove(correctMove));

            System.out.println("Ход компьютера: " + correctMove.get(0) + " " + correctMove.get(1));

            playField.viewChangePlayField(correctMove, comp.compSign);

            if (playField.checkWinGame(comp.compSign)) {
                System.out.println("Победил компьютер!");
                break;
            }

            if (!playField.checkFreeSpace()) {
                System.out.println("Ничья!");
                break;
            }

        }
        System.out.println("Игра завершена!");
    }
}
