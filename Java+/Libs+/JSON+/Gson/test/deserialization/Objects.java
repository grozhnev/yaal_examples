package deserialization;

import static org.junit.Assert.assertEquals;
import java.util.Date;
import org.junit.Test;
import com.google.gson.Gson;
import myobject.MyObject;

/**
 * Парсинг JSON в Java-объект.
 */
public class Objects {
	private static final Gson gson = new Gson();

	@Test
	public void object() {
		String json = "{\"number\":4,\"text\":\"abc\",\"inner\":{\"now\":\"Jul 8, 2015 6:37:19 AM\",\"sum\":18}}";
		MyObject obj = gson.fromJson(json, MyObject.class);
		assertEquals(obj.number, 4);
		assertEquals(obj.text, "abc");
		assertEquals(obj.excluded, false);
		assertEquals(obj.inner.now, new Date(1436326639000L));
		assertEquals((int) obj.inner.sum, 18);
	}

}
