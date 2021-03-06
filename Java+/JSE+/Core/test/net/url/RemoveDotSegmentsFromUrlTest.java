package net.url;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Remove dot segments (/./ and /../) from URL.
 */
public class RemoveDotSegmentsFromUrlTest {
    @Test
    public void removeDotSegments() {
        assertThat(RemoveDotSegmentsFromUrl.removeDotSegments("/a/b/../c"), equalTo("/a/c"));
        assertThat(RemoveDotSegmentsFromUrl.removeDotSegments("../css/common.css"), equalTo("css/common.css"));
    }

    /**
     * Examples from FRC 3986.
     */
    @Test
    public void rfc1() {
        assertThat(RemoveDotSegmentsFromUrl.removeDotSegments("/a/b/c/./../../g"), equalTo("/a/g"));
        assertThat(RemoveDotSegmentsFromUrl.removeDotSegments("mid/content=5/../6"), equalTo("mid/6"));
    }
}