import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by xjtu_yjw on 2017/1/18.
 */
public class Column {
    Dot[] dots = new Dot[9];
    int column;
    HashSet remainNumbs = new HashSet();

    public Column() {
        for (int i = 0; i < 9; i++) {
            remainNumbs.add(i + 1);
        }
    }

    public void updateRemainNumbs(int value) {
        if (value != 0) {
            remainNumbs.remove(value);
        }
    }

    @Override
    public String toString() {
        return "Column{" +
                "dots=" + Arrays.toString(dots) +
                ", column=" + column +
                ", remainNumbs=" + remainNumbs +
                '}';
    }
}
