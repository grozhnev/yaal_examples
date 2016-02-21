package pointcut.execution.interface_method_call;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pointcut.execution.interface_method_call.ConverterImpl;
import pointcut.execution.interface_method_call.ConverterInterface;
import pointcut.execution.interface_method_call.MyAspect;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@ContextConfiguration(classes = {
        MyAspect.class,
        ConverterImpl.class
})
@RunWith(SpringJUnit4ClassRunner.class)
public class CallInterfaceMethodTest {

    @Autowired
    ConverterInterface reverse;

    @Test
    public void test() {
        assertThat(reverse.reverse("abc"), equalTo(MyAspect.PREFIX + "cba"));
    }
}