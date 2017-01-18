import java.util.Arrays;

/**
 * Created by xjtu_yjw on 2017/1/18.
 */
public class Box {
    Dot[][] dots = new Dot[3][3];
    int x;
    int y;

    @Override
    public String toString() {
        return "Box{" +
                "dots=" + Arrays.toString(dots) +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
