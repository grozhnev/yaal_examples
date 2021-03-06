package databind.deserialize.pointer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import util.JsonUtil;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Convert part of JSON to POJO.
 */
public class JsonPointerTest {

    @Test
    public void pointer() throws IOException {
        String json = JsonUtil.singleQuoteToDouble("{ 'person': {'id': 123, 'name': 'aleks'}}");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);
        JsonNode person = rootNode.at("/person");
        User user = mapper.treeToValue(person, User.class);

        assertThat(user.id, equalTo(123));
        assertThat(user.name, equalTo("aleks"));
    }

    @SuppressWarnings("WeakerAccess")
    private static class User {
        public Integer id;
        public String name;
    }
}
