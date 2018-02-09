package freemarker.bean.wrapper;

import freemarker.BaseFreemarkerTest;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Use static variables in templates.
 */
public class UseStaticVariable extends BaseFreemarkerTest {

    @Test
    public void test() throws IOException, TemplateException {
        BeansWrapperBuilder builder = new BeansWrapperBuilder(Configuration.VERSION_2_3_21);
        BeansWrapper beansWrapper = builder.build();

        Map<String, Object> data = new HashMap<>();
        data.put("statics", beansWrapper.getStaticModels());

        Template template = cfg.getTemplate("bean/wrapper/use_static_variable.ftl");

        StringWriter out = new StringWriter();
        template.process(data, out);

        assertEquals("Separator=" + File.separator, out.toString());
    }
}