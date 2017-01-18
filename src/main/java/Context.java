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


    public  Dot[][] preInitDots(int[][] matrix) {
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
                        dot.impsNumbs.add(k+1);
                    }else {
                        dot.proNumbs.add(k + 1);
                    }
                }
                dots[i][j] = dot;
            }
        }

        return dots;

    }

    public  Box[][] initBox(Dot[][] dots) {
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
            }
        }
        return boxes;
    }

    public  Row[] initRow(Dot[][] dots) {
        Row[] rows = new Row[9];
        for (int i = 0; i < 9; i++) {
            Row row = new Row();
            rows[i] = row;
            row.row = i;
            System.arraycopy(dots[i], 0, row.dots, 0, 9);
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
                }
            }
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
     *  根据给定dots数组计算给定dot中的可能数值
     * @param dot 给定dot
     * @param _dots 给定dots数组
     */
    private void calculateProImpsInDots(Dot dot, Dot[][] _dots) {
        for (Dot[] _dot : _dots) {
            calculateProImpsInDots(dot, _dot);
        }
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
                dot.impsNumbs.add(value);
                dot.proNumbs.remove(value);
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
