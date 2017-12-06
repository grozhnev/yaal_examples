package databind.serialize.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Source: https://dzone.com/articles/jackson-mixin-to-the-rescue
 */
public class IgnoreFieldTest {

    @Test
    public void serialize() throws IOException {
        Address address = new Address();
        address.city = "SPb";
        address.state = "Leningrad";

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(address);

        assertThat(json, containsString("state"));

        mapper.addMixIn(Address.class, AddressMixin.class);

        Address deserializedUser = mapper.readValue(json, Address.class);

        assertThat(deserializedUser.state, nullValue());
    }

    @SuppressWarnings("WeakerAccess")
    private static final class Address {
        public String city;
        public String state;
    }

    @SuppressWarnings("unused")
    private static abstract class AddressMixin {
        public String city;
        @JsonIgnore
        public String state;

    }
}
