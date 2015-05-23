import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Использование JavaBean в модели и шаблоне.
 */
public class JavaBean {
    private static Configuration cfg;

    @BeforeClass
    public static void setUp() throws Exception {
        cfg = new Configuration(Configuration.VERSION_2_3_22);
        File templatesDir = new File(PrimitiveDataTypes.class.getResource("test.ftl").getFile()).getParentFile();
        cfg.setDirectoryForTemplateLoading(templatesDir);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    @Test
    public void test() throws IOException, TemplateException {
        Map<String, Object> root = new HashMap<>();
        root.put("data", new Data());

        Template template = cfg.getTemplate("java_bean.ftl");

        Writer out = new OutputStreamWriter(System.out);
        template.process(root, out);
    }

    /**
     * Getters required.
     */
    public static class Data {
        private int integer = 1;
        private double decimal = 2.5;
        private String string = "Data value";
        private Date date = new Date();
        private boolean bool = true;

        public String getString() {
            return string;
        }

        public int getInteger() {
            return integer;
        }

        public double getDecimal() {
            return decimal;
        }

        public Date getDate() {
            return date;
        }

        public boolean isBoolean() {
            return bool;
        }
    }
}