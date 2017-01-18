import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by yjw on 2017/1/18.
 */
public class ContextTest {


    Context context;
    public   int[][] initMatrix() {
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
    @Before
    public void before() {
        context = new Context(initMatrix());
        context.init();
    }

    @Test
    public void testCalculateProNumbs() throws Exception {

    }
    @Test
    public void testCalculateRowProNumbs() throws Exception {
        Dot dot = context.dots[0][1];
        context.calculateRowProNumbs(dot, dot.row);
        context.printRow(dot.row.row);
        System.out.println(dot);

    }
    @Test
    public void testCalculateColumnProNumbs() throws Exception {
        Dot dot = context.dots[0][1];
        context.calculateColumnProNumbs(dot, dot.column);
        context.printColumn(dot.column.column);
        System.out.println(dot);
    }
    @Test
    public void testCalculateBoxProNumbs() throws Exception {
        Dot dot = context.dots[0][1];
        context.calculateBoxProNumbs(dot, dot.box);
        context.printBox(dot.box.x, dot.box.y);
        System.out.println(dot);
    }
    @Test
    public void calculateProNumbs() throws Exception {
        context.calculateProNumbs();
        Dot[][] _dots = context.dots;
        for (Dot[] dot : _dots) {
            for (Dot dot1 : dot) {
                System.out.println(dot1.proNumbs);
            }
        }
    }

    @Test
    public void test() {
        context.printMatrix();
        context.calculateProNumbs();
        context.printDot(0, 1);
    }
}