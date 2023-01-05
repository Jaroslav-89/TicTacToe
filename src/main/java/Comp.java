import java.util.ArrayList;

public class Comp {

    String compSign;
    String playerSign;

    //конструктор, который принимает и сохраняет символ компьютера и символ игрока
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

        int moveCount = 0;//счетчик ходов на игровом поле

        //определяем сколько ходов уже сделано на поле
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (!field[x][y].equals("*")) {
                    moveCount++;
                }
            }
        }

        //первым делом занимаем центр, если он не занят
        if (field[1][1].equals("*")) {
            System.out.println("нашел свободное место в центре, записываю координаты");
            coordinatesList.add(1);
            coordinatesList.add(1);
            return coordinatesList;
        }

        //если центр занят и сделан только 1 ход, то занимаем любой угол
        if (moveCount == 1) {
            System.out.println("нашел свободное место в углу, записываю координаты");
            coordinatesList.add(0);
            coordinatesList.add(0);
            return coordinatesList;
        }

        //если центр занят и в игре сделаны два хода, то определяем куда сходил игрок и делаем ход
        //в зависимости от эого
        if (moveCount == 2) {
            //если игрок сходил вторым ходом в угол, проверяем все углы и ходим в противоположный
            if (field[0][0].equals(playerSign)) {
                coordinatesList.add(2);
                coordinatesList.add(2);
            } else {
                if (field[0][2].equals(playerSign)) {
                    coordinatesList.add(2);
                    coordinatesList.add(0);
                } else {
                    if (field[2][0].equals(playerSign)) {
                        coordinatesList.add(0);
                        coordinatesList.add(2);
                    } else {
                        if (field[2][2].equals(playerSign)) {
                            coordinatesList.add(2);
                            coordinatesList.add(0);
                        }
                    }
                }
            }

            //если игрок сходил вторым ходом не в угол, занимаем противоположный угол от его хода
            if (field[0][1].equals(playerSign) || field[1][0].equals(playerSign)) {
                coordinatesList.add(2);
                coordinatesList.add(2);
            } else {
                coordinatesList.add(0);
                coordinatesList.add(0);
            }
            return coordinatesList;
        }


        do {
            //это нужно, чтобы сначало искать свои символы в ряд и выигрывать, если таких нет, то на втором круге цикла искать
            //символы соперника в ряд, чтобы не дать ему выиграть, если таких тоже нет, то искать один свой символ и ставить рядом
            String searchSign;
            if ((count == 0) || (count == 2)) {
                searchSign = compSign;
            } else {
                searchSign = playerSign;
            }

            //если это 5й ход, значит компьютер ходил первым в центр, после этого были ходы, которые проверялись на два символа
            //в ряд сначала у компьютера, затем у игрока и если таких совпадений не было, то будет выполнено условие ниже
            //которое приведет к победе компьютера
            if (moveCount == 4 && count == 2) {
                if (field[0][0].equals(compSign) || field[0][2].equals(compSign)) {
                    if (field[0][1].equals("*")) {
                        coordinatesList.add(0);
                        coordinatesList.add(1);
                    }
                }
                if (field[0][2].equals(compSign) || field[2][2].equals(compSign)) {
                    if (field[1][2].equals("*")) {
                        coordinatesList.add(1);
                        coordinatesList.add(2);
                    }
                }
                if (field[2][2].equals(compSign) || field[2][0].equals(compSign)) {
                    if (field[2][1].equals("*")) {
                        coordinatesList.add(2);
                        coordinatesList.add(1);
                    }
                }
                if (field[2][0].equals(compSign) || field[0][0].equals(compSign)) {
                    if (field[1][0].equals("*")) {
                        coordinatesList.add(1);
                        coordinatesList.add(0);
                    }
                }
                return coordinatesList;
            }


            //ищем два символа по диагонали, чтобы поставить свой, если место пустое
            //(диагонали в приоритете, поэтому проверяем их первыми)
            System.out.println("ищу два символа по диагонали от 0 0 к 2 2");
            if (field[0][0].equals(searchSign) && field[1][1].equals(searchSign)) {//по диагонали от левого верха до правого низа
                System.out.println("нашел два символа по диагонали");
                if (field[2][2].equals("*")) {
                    System.out.println("нашел свободное место, записываю координаты");
                    coordinatesList.add(2);
                    coordinatesList.add(2);
                    return coordinatesList;
                }
            } else {
                if (field[0][0].equals(searchSign) && field[2][2].equals(searchSign)) {
                    System.out.println("нашел два символа по диагонали");
                    if (field[0][0].equals("*")) {
                        System.out.println("нашел свободное место, записываю координаты");
                        coordinatesList.add(1);
                        coordinatesList.add(1);
                        return coordinatesList;
                    }
                } else {
                    if (field[1][1].equals(searchSign) && field[2][2].equals(searchSign)) {
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
            if (field[2][0].equals(searchSign) && field[1][1].equals(searchSign)) {//по диагонали от правого верха до левого низа
                System.out.println("нашел два символа по диагонали");
                if (field[0][2].equals("*")) {
                    System.out.println("нашел свободное место, записываю координаты");
                    coordinatesList.add(0);
                    coordinatesList.add(2);
                    return coordinatesList;
                }
            } else {
                if (field[2][0].equals(searchSign) && field[0][2].equals(searchSign)) {
                    System.out.println("нашел два символа по диагонали");
                    if (field[1][1].equals("*")) {
                        System.out.println("нашел свободное место, записываю координаты");
                        coordinatesList.add(1);
                        coordinatesList.add(1);
                        return coordinatesList;
                    }
                } else {
                    if (field[1][1].equals(searchSign) && field[0][2].equals(searchSign)) {
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

            for (int x = 0; x < 3; x++) {//ищем на игровом поле два одинаковых знака по строкам
                System.out.println("ищу в строке " + x);
                int sumAi = 0; //переменная в которой хранится сумма для оценки важности хода в определенное место
                for (int y = 0; y < 3; y++) {
                    System.out.println("проверяю место x " + y);
                    if (field[x][y].equals(searchSign)) {
                        sumAi = sumAi + 1;//если в одной строке будет стоять два одинаковых симола, то общая сумма строки будет 2
                    }
                }

                if (sumAi == 2) {//если нужная строка нашлась, находим в ней пустой элемент и записываем его координаты, чтобы туда сходить
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
                    if ((sumAi >= 1) && count > 1) {
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
                    System.out.println("проверяю место y " + x);
                    if (field[x][y].equals(searchSign)) {
                        sumAi = sumAi + 1;//если в одной строке будет стоять два одинаковых символа, то общая сумма строки будет 2
                    }
                }
                if (sumAi == 2) {
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

            //счетчик пройденных цикло, нужен для того, чтобы на первом проходе искать два своих символа в ряд и ставить туда третий
            //на втором проходе искать два символа соперника в ряд и ставить туда свой
            //на третем проходе искать один свой символ и ставить туда символ в свободное место
            if (count == 0) {
                System.out.println("не нашел два своих символа в ряд, ищу два символа соперника в ряд");
            } else {
                System.out.println("не нашел два символа соперника в ряд, ищу один символ чтобы поставить рядом");
            }

            count = count + 1;

        } while (true);
    }
}
