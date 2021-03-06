import lombok.Builder;
import lombok.Value;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BuilderTest {

    @Test
    public void test() {
        String name = "Moscow";
        int age = 1000;
        City moscow = City.builder().name(name).age(age).build();
        assertThat(moscow.getName(), equalTo(name));
        assertThat(moscow.getAge(), equalTo(age));
    }

    @Builder
    @Value
    private static class City {
        String name;
        int age;
    }
}
