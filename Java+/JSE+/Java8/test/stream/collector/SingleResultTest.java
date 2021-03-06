package stream.collector;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Из стрима должен остаться один элемент.
 */
public class SingleResultTest {
    private final Stream<String> stream = Stream.of("a", "b", "c");

    @Test
    public void singleElement() {
        String s = Stream.of("a", "b", "c")
                .filter("b"::equals)
                .collect(new SingleElementCollector<>());
        String single = stream
                .filter("b"::equals)
                .collect(new SingleElementCollector<>());
        assertThat(single, equalTo("b"));
    }

    @Test(expected = NoSuchElementException.class)
    public void noElements() {
        stream
                .filter("d"::equals)
                .collect(new SingleElementCollector<>());
    }

    @Test(expected = IllegalStateException.class)
    public void moreThanOneElement() {
        stream
                .filter(element -> element.equals("b") || element.equals("c"))
                .collect(new SingleElementCollector<>());
    }
}
