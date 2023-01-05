import java.util.ArrayList;

public class Comp {

    String compSign;
    String playerSign;

    Comp(String sign) {
        compSign = sign;
        if (compSign.equals("X")) {
            playerSign = "O";
        } else {
            playerSign = "X";
        }
    }


    public ArrayList<Integer> compMove(String[][] field) {
        ArrayList<Integer> coordinatesList = new ArrayList<Integer>();

        int count = 0; //счетчик для того, чтобы определить в первый раз проходится цикл или нет. В первое прохождение
        //бесконечный цикл ищет два одинаковых знака в ряд, чтобы поставить любой третий в свободное место
        //Любой знак подходит, тк этот ход будет подходить и для атаки и для защиты

        do {

            //первым делом занимаем центр, если он не занят
            if (field[1][1].equals("*")) {
                System.out.println("нашел свободное место в центре, записываю координаты");
                coordinatesList.add(1);
                coordinatesList.add(1);
                return coordinatesList;
            } else {
                //если центр занят своим знаком, ищем на игровом поле еще свои ходы(если свой ход только один,
                //то можно определить наилучший второй ход, в зависимости от хода игрока)
                if (field[1][1].equals(compSign)) {
                    int signCount = 0;//счетчик своих ходов на игровом поле
                    for (int x = 0; x < 3; x++) {//
                        System.out.println("ищу в строке " + x);
                        for (int y = 0; y < 3; y++) {
                            System.out.println("проверяю место " + y);
                            if (field[x][y].equals(compSign)) {
                                signCount++;
                            }
                        }
                    }

                    //определяем ход компьютера, если игрок поставил по центру слевa/справа/снизу/сверху
                    if ((field[0][1].equals(playerSign) || field[2][1].equals(playerSign)) && signCount == 1) {
                        //если игрок сходил сверху или снизу по центру и это второй ход, тогда ставим по центру слева
                        coordinatesList.add(1);
                        coordinatesList.add(0);
                        return coordinatesList;
                    } else {
                        if ((field[1][0].equals(playerSign) || field[1][2].equals(playerSign)) && signCount == 1) {
                            //если игрок сходил слева или справа по центру и это второй ход, тогда ставим по центру сверху
                            coordinatesList.add(0);
                            coordinatesList.add(1);
                            return coordinatesList;
                        }
                    }

                    //определяем ход компьютера, если игрок поставил по диагонали в любом углу
                    if ((field[0][0].equals(playerSign) || field[2][2].equals(playerSign)) && signCount == 1) {
                        //если игрок сходил в один из углов и это второй ход, тогда ставим в противоположный угол, если он свободен
                        if (field[2][2].equals("*")) {
                            coordinatesList.add(2);
                            coordinatesList.add(2);
                            return coordinatesList;
                        } else {
                            coordinatesList.add(0);
                            coordinatesList.add(0);
                            return coordinatesList;
                        }
                    } else {
                        if ((field[2][0].equals(playerSign) || field[0][2].equals(playerSign)) && signCount == 1) {
                            //если игрок сходил в один из углов и это второй ход, тогда ставим в противоположный угол, если он свободен
                            if (field[2][0].equals("*")) {
                                coordinatesList.add(2);
                                coordinatesList.add(0);
                                return coordinatesList;
                            } else {
                                coordinatesList.add(0);
                                coordinatesList.add(2);
                                return coordinatesList;
                            }
                        }
                    }
                }
            }


            for (int x = 0; x < 3; x++) {//сначала ищем на игровом поле два одинаковых знака по строкам
                System.out.println("ищу в строке " + x);
                int sumAi = 0; //переменная в которой хранится сумма для оценки важности хода в определенное место
                for (int y = 0; y < 3; y++) {
                    System.out.println("проверяю место " + y);
                    if (field[x][y].equals("X")) {
                        sumAi = sumAi + 1;//если в одной строке будет стоять два Х, то общая сумма строки будет 2
                    } else {
                        if (field[x][y].equals("O")) {
                            sumAi = sumAi + 10;//если в одной строке будет стоять два O, то общая сумма строки будет 20
                            //разные цифры (1 и 10) использую для того, чтобы понять, что в строке
                            //есть именно два одинаковых знака
                        }
                    }
                }

                if (sumAi == 2 || sumAi == 20) {//если нужная строка нашлась, находим в ней пустой элемент и записываем его координаты, чтобы туда сходить
                    System.out.println("нашел строку с двумя одинаковыми символами");
                    for (int y = 0; y < 3; y++) {
                        System.out.println("ищу свободное место в этой строке");
                        if (field[x][y].equals("*")) {
                            System.out.println("нашел свободное место, записываю координаты");
                            coordinatesList.add(x);
                            coordinatesList.add(y);
                            return coordinatesList;
                        }
                    }
                } else {
                    if ((sumAi >= 1) && count == 1) {
                        System.out.println("нашел строку только с одним символом");
                        for (int y = 0; y < 3; y++) {
                            System.out.println("ищу свободное место в этой строке");
                            if (field[x][y].equals("*")) {
                                System.out.println("нашел свободное место, записываю координаты");
                                coordinatesList.add(x);
                                coordinatesList.add(y);
                                return coordinatesList;
                            }
                        }
                    }
                }
            }

            for (int y = 0; y < 3; y++) {//ищем на игровом поле два одинаковых знака по столбцам
                System.out.println("ищу в столбце " + y);
                int sumAi = 0; //переменная в которой хранится сумма для оценки важности хода в определенное место
                for (int x = 0; x < 3; x++) {
                    System.out.println("проверяю место " + x);
                    if (field[x][y].equals("X")) {
                        sumAi = sumAi + 1;//если в одной строке будет стоять два Х, то общая сумма строки будет 2
                    } else {
                        if (field[x][y].equals("O")) {
                            sumAi = sumAi + 10;//если в одной строке будет стоять два O, то общая сумма строки будет 20
                        }
                    }
                }
                if (sumAi == 2 || sumAi == 20) {
                    System.out.println("нашел столбец с двумя одинаковыми символами");
                    for (int x = 0; x < 3; x++) {
                        System.out.println("ищу свободное место в этом столбце");
                        if (field[x][y].equals("*")) {
                            System.out.println("нашел свободное место, записываю координаты");
                            coordinatesList.add(x);
                            coordinatesList.add(y);
                            return coordinatesList;
                        }
                    }
                }
            }

            System.out.println("ищу два символа по диагонали от 0 0 к 2 2");
            if ((field[0][0].equals("X") && field[1][1].equals("X")) ||
                    (field[0][0].equals("O") && field[1][1].equals("O"))) {//по диагонали от левого верха до правого низа
                System.out.println("нашел два символа по диагонали");
                if (field[2][2].equals("*")) {
                    System.out.println("нашел свободное место, записываю координаты");
                    coordinatesList.add(2);
                    coordinatesList.add(2);
                    return coordinatesList;
                }
            } else {
                if ((field[0][0].equals("X") && field[2][2].equals("X")) ||
                        (field[0][0].equals("O") && field[2][2].equals("O"))) {
                    System.out.println("нашел два символа по диагонали");
                    if (field[0][0].equals("*")) {
                        System.out.println("нашел свободное место, записываю координаты");
                        coordinatesList.add(1);
                        coordinatesList.add(1);
                        return coordinatesList;
                    }
                } else {
                    if ((field[1][1].equals("X") && field[2][2].equals("X")) ||
                            (field[1][1].equals("O") && field[2][2].equals("O"))) {
                        System.out.println("нашел два символа по диагонали");
                        if (field[0][0].equals("*")) {
                            System.out.println("нашел свободное место, записываю координаты");
                            coordinatesList.add(0);
                            coordinatesList.add(0);
                            return coordinatesList;
                        }
                    }
                }
            }

            System.out.println("ищу два символа по диагонали от 2 0 к 0 2");
            if ((field[2][0].equals("X") && field[1][1].equals("X")) ||
                    (field[2][0].equals("O") && field[1][1].equals("O"))) {//по диагонали от правого верха до левого низа
                System.out.println("нашел два символа по диагонали");
                if (field[0][2].equals("*")) {
                    System.out.println("нашел свободное место, записываю координаты");
                    coordinatesList.add(0);
                    coordinatesList.add(2);
                    return coordinatesList;
                }
            } else {
                if ((field[2][0].equals("X") && field[0][2].equals("X")) ||
                        (field[2][0].equals("O") && field[0][2].equals("O"))) {
                    System.out.println("нашел два символа по диагонали");
                    if (field[1][1].equals("*")) {
                        System.out.println("нашел свободное место, записываю координаты");
                        coordinatesList.add(1);
                        coordinatesList.add(1);
                        return coordinatesList;
                    }
                } else {
                    if ((field[1][1].equals("X") && field[0][2].equals("X")) ||
                            (field[1][1].equals("O") && field[0][2].equals("O"))) {
                        System.out.println("нашел два символа по диагонали");
                        if (field[2][0].equals("*")) {
                            System.out.println("нашел свободное место, записываю координаты");
                            coordinatesList.add(2);
                            coordinatesList.add(0);
                            return coordinatesList;
                        }
                    }
                }
            }
            System.out.println("не нашел два символа, иду на второй круг");
            count = count + 1;

        } while (true);
    }
}