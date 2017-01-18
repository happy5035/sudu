import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xjtu_yjw on 2017/1/18.
 */
public class Run {

    public static void main(String[] args) {
        int[][] matrix = initMatrix();
        Context context = new Context(matrix);
        context.init();
        context.printDots();

    }

    public  static int[][] initMatrix() {
        int[][] _margin = {
                {0, 0, 1, 3, 0, 5, 0, 9, 0},
                {0, 2, 4, 0, 0, 6, 0, 0, 5},
                {7, 0, 0, 0, 0, 0, 0, 0, 0},
                {4, 0, 0, 8, 0, 0, 0, 0, 2},
                {0, 6, 8, 0, 0, 0, 0, 1, 4},
                {0, 0, 7, 4, 0, 0, 6, 0, 0},
                {0, 4, 0, 6, 5, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 5, 0, 0},
                {0, 0, 5, 0, 3, 0, 0, 6, 9}
        };
        return _margin;
    }

}
