import org.junit.Test;
import static java.lang.System.out;

public class ShowTest {
	@Test
    public void test() {
        new Child();
    }
}

class Parent {

    Parent(){
        test();
    }

    void test(){
        out.println("parent::test");
    }

}

class Child extends Parent {

    private String field = "Init";

    Child(){
        field = "Init in constructor";
    }

    void test() {
        out.println("child::test");
        out.println("field=" + field);
    }
}