import java.util.Arrays;

/**
 * Created by xjtu_yjw on 2017/1/19.
 */
public class Utils {

    public static <T> int matrixSize(T[][] matrix) {
        int r = matrix.length;
        int c = 0;
        if (r != 0) {
            c = matrix[0].length;
        }
        return r * c;
    }

    public static <T> T[] matrix2List(T[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        int s = r * c;
        T[] result;
        result = (T[]) new Object[s];
        for (int i = 0; i < r; i++) {
            System.arraycopy(matrix[i], 0, result, i * c + 0, c);
        }

        return  result;
    }
    public static <T> void display(T t) {
        System.out.println(t.getClass());
    }


    public static void main(String[] args) {
        Integer[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        Object[] _rs = matrix2List(matrix);
        Integer[] res = new Integer[6];
        System.arraycopy(_rs, 0, res, 0, 6);

        System.out.println(Arrays.toString(res));

    }

}
