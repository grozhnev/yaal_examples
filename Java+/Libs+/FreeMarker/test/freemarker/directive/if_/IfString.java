package freemarker.directive.if_;

import freemarker.BaseFreemarkerTest;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Условные операторы в шаблонах.
 */
public class IfString extends BaseFreemarkerTest {

    @Test
    public void test() throws IOException, TemplateException {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Aleksey");
        data.put("sex", "M");

        Template template = cfg.getTemplate("directive/if_/if_string.ftl");

        StringWriter out = new StringWriter();
        template.process(data, out);

        assertEquals("Hello, Mr. Aleksey!", out.toString());
    }
}