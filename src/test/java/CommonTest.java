import org.junit.Test;

import java.util.HashSet;

/**
 * Created by xjtu_yjw on 2017/1/19.
 */
public class CommonTest {
    @Test
    public void test() {
        HashSet set = new HashSet();
        set.add(1);
        set.add(2);
        set.add(3);
        Integer i = new Integer(2);
        System.out.println(set.contains(i));
    }
}
