import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by xjtu_yjw on 2017/1/18.
 */
public class Box {
    Dot[][] dots = new Dot[3][3];
    int x;
    int y;
    int size = 9;
    HashSet remainNumbs = new HashSet();

    public Box() {
        for (int i = 0; i < 9; i++) {
            remainNumbs.add(i + 1);
        }
    }

    @Override
    public String toString() {
        return "Box{" +
                "dots=" + Arrays.toString(dots) +
                ", x=" + x +
                ", y=" + y +
                ", size=" + size +
                ", remainNumbs=" + remainNumbs +
                '}';
    }

    public void updateRemainNumbs(int value) {
        if (value != 0) {
            remainNumbs.remove(value);
        }
    }
}
