package pointcut.expression;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Using ExpressionPointcut for declaring pointcuts.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AopConfiguration.class, StringService.class})
public class ExpressionPointcutTest {

    @Autowired
    private StringService stringService;

    @Test
    public void test() {
        assertFalse(MyAdvice.invoked);
        String upper = stringService.toUpperCase("lower");
        assertTrue(MyAdvice.invoked);
        assertThat(upper, equalTo("LOWER"));
    }
}
