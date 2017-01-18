import java.util.Arrays;

/**
 * Created by xjtu_yjw on 2017/1/18.
 */
public class Square {
    Box[][] boxes = new Box[3][3];
    Row[] rows = new Row[9];
    Column[] columns = new Column[9];

    @Override
    public String toString() {
        return "Square{" +
                "boxes=" + Arrays.toString(boxes) +
                ", rows=" + Arrays.toString(rows) +
                ", columns=" + Arrays.toString(columns) +
                '}';
    }
}
