import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by yjw on 2017/1/18.
 */
public class Context {

    int[][] matrix = new int[9][9];
    Dot[][] dots = new Dot[9][9];
    Box[][] boxes = new Box[3][3];
    Row[] rows = new Row[9];
    Column[] columns = new Column[9];
    Square square = new Square();

    public Context(int[][] matrix) {
        this.matrix = matrix;
    }


    public  Dot[][] init() {
        Dot[][] dots = preInitDots(matrix);
        boxes = initBox(dots);
        columns = initColumn(dots);
        rows =initRow(dots);
        square=initSquare(boxes, columns, rows);
        dots = afterInitDot(dots, boxes, columns, rows);
        this.dots = dots;
        return dots;
    }

    public  void printDots() {
        for (Dot[] dot : dots) {
            for (Dot dot1 : dot) {
                System.out.print(" "+dot1.toString());
            }
            System.out.println();
        }

    }


    private Dot[][] preInitDots(int[][] matrix) {
        Dot[][] dots = new Dot[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int value = matrix[i][j];
                Dot dot = new Dot(i, j, value);
                if (value != 0) {
                    dot.isCertain = true;
                }
                for (int k = 0; k < 9; k++) {
                    if (dot.isCertain) {
                        dot.addImpsNumbs(k + 1);
                    }else {
                        dot.addProNumbs(k+1);
                    }
                }
                dots[i][j] = dot;
            }
        }

        return dots;

    }

    private Box[][] initBox(Dot[][] dots) {
        Box[][] boxes = new Box[3][3];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int _i = i / 3;
                int _j = j / 3;
                Box box = boxes[_i][_j];
                if (box == null) {
                    box = new Box();
                    boxes[_i][_j] = box;
                    box.x = _i;
                    box.y = _j;
                }

                box.dots[i % 3][j % 3] = dots[i][j];
                box.dotsList.add(dots[i][j]);
                box.updateRemainNumbs(dots[i][j].value);
            }
        }
        return boxes;
    }

    private Row[] initRow(Dot[][] dots) {
        Row[] rows = new Row[9];
        for (int i = 0; i < 9; i++) {
            Row row = new Row();
            rows[i] = row;
            row.row = i;
            System.arraycopy(dots[i], 0, row.dots, 0, 9);
            for (int j = 0; j < 9; j++) {
                row.updateRemainNumbs(dots[i][j].value);
            }
        }
        return rows;
    }

    private Column[] initColumn(Dot[][] dots) {
        Column[] columns = new Column[9];
        for (int i = 0; i < 9; i++) {
            Column column = new Column();
            columns[i] = column;
            column.column = i;
            for (int j = 0; j < 9; j++) {
                column.dots[j] = dots[j][i];
                column.updateRemainNumbs(dots[i][j].value);
            }
        }
        return columns;
    }

    private Square initSquare(Box[][] box, Column[] column, Row[] row) {
        Square square = new Square();
        square.boxes = box;
        square.columns = column;
        square.rows = row;
        return square;
    }

    private Dot[][] afterInitDot(Dot[][] dots, Box[][] boxes, Column[] columns, Row[] rows) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Dot dot = dots[i][j];
                dot.box = boxes[i / 3][j / 3];
                dot.column = columns[j];
                dot.row = rows[i];
            }
        }
        return dots;
    }

    public void calculateProNumbs() {
        Dot[][] _dots = dots;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Dot _dot = _dots[i][j];
                if (!_dot.isCertain) {
                    calculateBoxProNumbs(_dot, _dot.box);
                    calculateRowProNumbs(_dot, _dot.row);
                    calculateColumnProNumbs(_dot, _dot.column);
                    fill(_dot);
                }
            }
        }
    }

    private void fill( Dot _dot) {
        if (decideCanFill(_dot)) {
            int value = (Integer) _dot.proNumbs.iterator().next();
            System.out.println("(" + _dot.x + "," + _dot.y + ")," + value);
            update(_dot,value);
        }
    }

    /**
     * 更新 box，column，row中剩余数字集合
     * @param dot
     * @param value
     */
    private void update(Dot dot, int value) {
        matrix[dot.x][dot.y] = value;
        updateDot(dot, value);
        updateBox(dot.box, value);
        updateRow(dot.row, value);
        updateColumn(dot.column, value);

    }

    private void updateDot(Dot dot, int value) {
        dot.isCertain = true;
        dot.impsNumbs.add(value);
        dot.proNumbs.remove(value);
        dot.value = value;
    }

    private void updateColumn(Column column, int value) {
        column.updateRemainNumbs(value);
    }

    private void updateRow(Row row, int value) {
        row.updateRemainNumbs(value);
    }

    private void updateBox(Box box, int value) {
        box.updateRemainNumbs(value);
    }

    private boolean decideCanFill(Dot dot) {
        boolean result = decideCanFillBySize(dot) || decideCanFillByOnly(dot);
        
        return result;

    }

    /**
     * 如果该Dot所在行、列或者Box中存在剩余数字只能在一个Dot中，就可以确认该剩余数字
     * @param dot
     * @return
     */
    private boolean decideCanFillByOnly(Dot dot) {
        boolean result ;
        result = decideCanFillByOnlyInBox(dot,dot.box)
                || decideCanFillByOnlyInRow(dot,dot.row)
                || decideCanFillByOnlyInColumn(dot,dot.column);

        return result;
    }

    private boolean decideCanFillByOnlyInColumn(Dot dot, Column column) {
        HashSet remainNumbs = dot.proNumbs;
        for (Object remainNumb : remainNumbs) {
            int num = column.calculateNumOfValue((Integer) remainNumb);
            if (num == 1)
                return true;
        }
        return false;
    }

    private boolean decideCanFillByOnlyInRow(Dot dot, Row row) {
        HashSet remainNumbs = dot.proNumbs;
        for (Object remainNumb : remainNumbs) {
            int num = row.calculateNumOfValue((Integer) remainNumb);
            if (num == 1)
                return true;
        }
        return false;
    }


    private boolean decideCanFillByOnlyInBox(Dot dot, Box box) {
        HashSet remainNumbs = dot.proNumbs;
        for (Object remainNumb : remainNumbs) {
            int num = box.calculateNumOfValue((Integer) remainNumb);
            if (num == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 如果肯能数字只剩下一个就可以确定该位置的值
     * @param dot
     * @return
     */
    private boolean decideCanFillBySize(Dot dot) {
        if (dot.proNumbs.size() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void calculateRowProNumbs(Dot dot, Row row) {
        Dot[] _dots = row.dots;
        calculateProImpsInDots(dot, _dots);
    }

    public void calculateColumnProNumbs(Dot dot, Column column) {
        Dot[] _dots = column.dots;
        calculateProImpsInDots(dot, _dots);
    }

    public void calculateBoxProNumbs(Dot dot, Box box) {
        Dot[][] _dots = box.dots;
        calculateProImpsInDots(dot, _dots);
    }

    /**
     * 根据给定dots数组计算给定dot中的可能数值
     *
     * @param dot   给定dot
     * @param _dots 给定dots数组
     */
    private void calculateProImpsInDots(Dot dot, Dot[][] _dots) {
        int length = Utils.matrixSize(_dots);
        Dot[] dots = new Dot[length];
        Object[] o_dots = Utils.matrix2List(_dots);
        System.arraycopy(o_dots, 0, dots, 0, length);
        calculateProImpsInDots(dot,  dots);
    }

    /**
     *  根据给定dots数组计算给定dot中的可能数值
     * @param dot 给定dot
     * @param _dots 给定dots数组
     */
    private void calculateProImpsInDots(Dot dot, Dot[] _dots) {
        for (Dot _dot : _dots) {
            int value = _dot.value;
            if (value != 0) {
                dot.addImpsNumbs(value);
                dot.removeProNumbs(value);
            }
        }
    }


    public  void printMatrix() {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(" " + anInt);
            }
            System.out.println();
        }
    }

    public void printRow(int num) {
        for (int i : matrix[num]) {
            System.out.print(" " + i);
        }
        System.out.println();
    }

    public void printColumn(int num) {
        for (int i = 0; i < 9; i++) {
            System.out.print(" "+ matrix[i][num]);
        }
        System.out.println();
    }

    public void printBox(int x, int y) {
        Box box = boxes[x][y];
        Dot[][] dots = box.dots;
        for (Dot[] dot : dots) {
            for (Dot dot1 : dot) {
                System.out.print(" "+ dot1.value);
            }
            System.out.println();
        }
    }

    public void printDot(int i, int j) {
        System.out.println(dots[i][j]);
    }
}
