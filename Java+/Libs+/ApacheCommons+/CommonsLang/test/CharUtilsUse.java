import org.apache.commons.lang3.CharUtils;
import org.junit.Test;

public class CharUtilsUse {
    @Test
    public void testName() {
        //удилить
        char c =CharUtils.toChar('\u0410');
        System.out.println(c);
        System.out.println('\u0410');

    }
}
