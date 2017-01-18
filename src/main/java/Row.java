import java.util.Arrays;

/**
 * Created by xjtu_yjw on 2017/1/18.
 */
public class Row {
    Dot[] dots = new Dot[9];
    int row;

    @Override
    public String toString() {
        return "Row{" +
                "dots=" + Arrays.toString(dots) +
                ", row=" + row +
                '}';
    }
}
