package databind.serialize.custom.field;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.IOException;
import java.io.StringWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Custom serialization a Throwable field to JSON.
 */
public class ThrowableSerializerTest {

    @Test
    public void test() throws IOException, JSONException {
        RuntimeException cause = new RuntimeException("cause message");
        cause.setStackTrace(new StackTraceElement[0]);

        StackTraceElement[] stackTrace = {
                new StackTraceElement("my.Class", "getName", "file", 1),
                new StackTraceElement("my.Class2", "getAge", "file2", 3)
        };

        Throwable throwable = new Throwable("my message", cause);
        throwable.setStackTrace(stackTrace);

        Data data = new Data();
        data.throwable = throwable;


        SimpleModule module = new SimpleModule();
        module.addSerializer(Throwable.class, new ThrowableSerializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);

        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, data);

        String exp = "{throwable: {" +
                "message: 'my message', " +
                "localizedMessage: 'my message', " +
                "cause: {message: 'cause message', localizedMessage: 'cause message', cause: null, stackTrace: '', suppressed: []}," +
                "stackTrace: 'my.Class.getName(file:1); my.Class2.getAge(file2:3)'," +
                "suppressed: []}" +
                "}";

        String actJson = writer.toString();
        System.out.println(actJson);
        JSONAssert.assertEquals(exp, actJson, JSONCompareMode.STRICT);
    }


    private static class Data {
        @JsonSerialize(using = ThrowableSerializer.class)
        Throwable throwable;
    }

    private static class ThrowableSerializer extends StdSerializer<Throwable> {

        ThrowableSerializer() {
            super(Throwable.class);
        }

        @Override
        public void serialize(Throwable value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeStartObject();

            gen.writeStringField("message", value.getMessage());
            gen.writeStringField("localizedMessage", value.getLocalizedMessage());

            serializeCause(value, gen, provider);
            serializeStackTrace(value, gen, provider);
            serializeSuppresed(value, gen, provider);

            gen.writeEndObject();
        }

        private void serializeCause(Throwable throwable, JsonGenerator gen, SerializerProvider provider) throws IOException {
            Throwable cause = throwable.getCause();
            if (cause != null) {
                gen.writeFieldName("cause");
                JsonSerializer<Object> serializer = provider.findValueSerializer(cause.getClass());
                serializer.serialize(cause, gen, provider);
            } else {
                gen.writeNullField("cause");
            }
        }

        private void serializeStackTrace(Throwable throwable, JsonGenerator gen, SerializerProvider provider) throws IOException {
            StackTraceElement[] cause = throwable.getStackTrace();
            if (cause != null) {
                gen.writeFieldName("stackTrace");
                String value = Stream.of(cause).map(StackTraceElement::toString).collect(Collectors.joining("; "));
                JsonSerializer<Object> serializer = provider.findValueSerializer(String.class);
                serializer.serialize(value, gen, provider);
            } else {
                gen.writeNullField("stackTrace");
            }
        }

        private void serializeSuppresed(Throwable throwable, JsonGenerator gen, SerializerProvider provider) throws IOException {
            Throwable[] suppressed = throwable.getSuppressed();
            if (suppressed != null) {
                gen.writeFieldName("suppressed");
                JsonSerializer<Object> serializer = provider.findValueSerializer(suppressed.getClass());
                serializer.serialize(suppressed, gen, provider);
            } else {
                gen.writeNullField("suppressed");
            }
        }
    }
}
