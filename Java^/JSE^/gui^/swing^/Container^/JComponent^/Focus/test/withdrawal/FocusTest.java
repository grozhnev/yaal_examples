package withdrawal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FocusTest {

    @Test
    public void testLeft() throws Exception {

    }

    @Test
    public void right() {
        Object[][] matrix = {
                {1, 2, null, 3, 4, null, 5},
                {4, 5, null}
        };
        Focus<Object> focus = new Focus<>(matrix);
        assertEquals(1, focus.selected());
        focus.right();
        assertEquals(2, focus.selected());
        focus.right();
        assertEquals(3, focus.selected());
        focus.right();
        assertEquals(4, focus.selected());
        focus.right();
        assertEquals(5, focus.selected());
        focus.right();
        assertEquals(5, focus.selected());
    }

    @Test
    public void down() {
        Object[][] matrix = {
                {1, 2, 3},
                {4, 5, null},
                {null, 8, 9},
                {10},
                {11},
                {null, 12},
                {13, 14}

        };
        Focus<Object> focus = new Focus<>(matrix);
        assertEquals(1, focus.selected());
        focus.down();
        assertEquals(4, focus.selected());
        focus.down();
        assertEquals(10, focus.selected());
        focus.down();
        assertEquals(11, focus.selected());
        focus.down();
        assertEquals(13, focus.selected());
        focus.down();
        assertEquals(13, focus.selected());
    }

    @Test
    public void testUp() throws Exception {

    }
}