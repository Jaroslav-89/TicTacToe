import java.util.ArrayList;
import java.util.Random;

public class Comp {

    String compSign;

    Comp(String sign) {
        compSign = sign;
    }

    public ArrayList<Integer> compMove(String[][] field) {
        Random random = new Random();
        ArrayList<Integer> coordinatesList = new ArrayList<Integer>();

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (field[x][y] == "*") {
                    coordinatesList.add(x);
                    coordinatesList.add(y);
                }
            }
        }
        return coordinatesList;
    }
}
