import java.util.Arrays;

/**
 * Created by xjtu_yjw on 2017/1/18.
 */
public class Column {
    Dot[] dots = new Dot[9];
    int column;

    @Override
    public String toString() {
        return "Column{" +
                "dots=" + Arrays.toString(dots) +
                ", column=" + column +
                '}';
    }
}
