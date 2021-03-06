package hystrix.command.fallback;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class FallbackTest {

    @Test
    public void fallback() {
        final FallbackCommand command = new FallbackCommand();
        String s = command.execute();
        assertThat(s, equalTo(FallbackCommand.FALLBACK_RESULT));
        assertTrue(command.isResponseFromFallback());
    }
}
