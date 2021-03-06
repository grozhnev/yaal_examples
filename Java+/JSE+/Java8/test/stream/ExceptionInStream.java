package stream;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class ExceptionInStream {

    @Test(expected = RuntimeException.class)
    public void runtimeException() {
        List<String> list = Stream.of("a", "bb", null, "cc", null)
                .filter(s -> s.length() > 1)
                .collect(Collectors.toList());
        System.out.println(list);
    }

    @Test(expected = RuntimeException.class)
    public void checkedException() {
        List<Integer> list = Stream.of(1, 2, null, 3, null)
                .map(s -> {
                    try {
                        return throwChecked();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());
        assertThat(list, contains(1, 2, 3));
    }

    private Integer throwChecked() throws Exception {
        throw new Exception();
    }
}
