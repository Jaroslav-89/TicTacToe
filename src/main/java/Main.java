import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        PlayField playField = new PlayField();
        Player player = new Player();
        Comp comp;

        playField.createPlayField();

        player.selectPlayerSign();

        if (player.playerSign.equals("X")) {
            comp = new Comp("O");
        } else {
            comp = new Comp("X");
        }

        System.out.println("Вы ставите: " + player.playerSign + "\nКомпьютер ставит: " + comp.compSign);

        while (true) {

            System.out.println("Ваш ход! Для того чтобы поставить -" + player.playerSign +
                    "- введите координаты от 0 до 2 в формате: Y X (через пробел), где 0 0 это верхний левый угол");

            ArrayList<Integer> correctMove;

            while (true) {
                //получаем координаты в правильном формате(игрок)
                correctMove = player.checkInputMove();
                //проверяем не занято ли поле, куда хочет сходить игрок
                if (playField.checkCorrectMove(correctMove)) {
                    break;
                }
            }

            playField.viewChangePlayField(correctMove, player.playerSign);

            if (playField.checkWinGame(player.playerSign)){
                System.out.println("Вы победили!");
                break;
            }

            if (!playField.checkFreeSpace()){
                System.out.println("Ничья!");
                break;
            }

            while (true) {
                //получаем координаты в правильном формате(комп)
                correctMove = comp.compMove(playField.field);
                //проверяем не занято ли поле, куда хочет сходить компьютер
                if (playField.checkCorrectMove(correctMove)) {
                    break;
                }
            }

            System.out.println("Ход компьютера: " + correctMove.get(0) + " " + correctMove.get(1));

            playField.viewChangePlayField(correctMove, comp.compSign);

            if (playField.checkWinGame(comp.compSign)){
                System.out.println("Победил компьютер!");
                break;
            }

            if (!playField.checkFreeSpace()){
                System.out.println("Ничья!");
                break;
            }

        }
        System.out.println("Игра завершена!");
    }
}
