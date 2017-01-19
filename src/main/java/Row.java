import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by xjtu_yjw on 2017/1/18.
 */
public class Row {
    Dot[] dots = new Dot[9];
    int row;
    HashSet remainNumbs = new HashSet();

    public Row() {
        for (int i = 0; i < 9; i++) {
            remainNumbs.add(i + 1);
        }
    }

    @Override
    public String toString() {
        return "Row{" +
                "dots=" + Arrays.toString(dots) +
                ", row=" + row +
                ", remainNumbs=" + remainNumbs +
                '}';
    }

    public void updateRemainNumbs(int value) {
        if (value != 0) {
            remainNumbs.remove(value);
        }
    }

    public int calculateNumOfValue(Integer remainNumb) {
        int num = 0;
        for (Dot dot : dots) {
            if (dot.proNumbs.contains(num)) {
                num++;
            }
        }
        return num;
    }
}
