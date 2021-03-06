package parse;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class LinkTest {
    @Test
    public void href() {
        String html = "<html>" +
                "<body>" +
                "<a href='next_page.html'/>" +
                "</body>" +
                "</html>";

        HtmlCleaner cleaner = new HtmlCleaner();
        TagNode htmlNode = cleaner.clean(html);
        TagNode aTag = htmlNode.getElementListByName("a", true).get(0);
        String hrefAttr = aTag.getAttributeByName("href");
        assertThat(hrefAttr, equalTo("next_page.html"));
    }
}
