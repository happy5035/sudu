import java.util.HashSet;

/**
 * Created by xjtu_yjw on 2017/1/18.
 */
public class Dot {
    int x;
    int y;
    int value;
    boolean isCertain = false;

    HashSet proNumbs = new HashSet();
    HashSet impsNumbs = new HashSet();
    Box box = new Box();
    Row row = new Row();
    Column column = new Column();

    public Dot(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Dot{" +
                "x=" + x +
                ", y=" + y +
                ", value=" + value +
                ", proNumbs=" + proNumbs +
                ", impsNumbs=" + impsNumbs +
                ", isCertain=" + isCertain+
                '}';
    }
}
