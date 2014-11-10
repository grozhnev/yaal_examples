import javax.swing.JLabel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class KeyboardEventsListener implements KeyListener {
    private JLabel label;

    KeyboardEventsListener(JLabel label) {
        this.label = label;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        String key;
        switch (e.getKeyChar()) {
            case KeyEvent.VK_ESCAPE: {
                key = "ESC";
                break;
            }
            case KeyEvent.VK_ENTER: {
                key = "Enter";
                break;
            }
            case KeyEvent.VK_KP_UP: {
                key = "Up";
                break;
            }
            default: {
                key = String.valueOf(e.getKeyChar());
            }
        }

        int modifiers = e.getModifiers();
        String modifiersText = KeyEvent.getKeyModifiersText(modifiers);

        String oldText = label.getText();
        label.setText(oldText + modifiersText + key + " ");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String key = null;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: {
                key = "Up";
                break;
            }
            case KeyEvent.VK_DOWN: {
                key = "Down";
                break;
            }
            case KeyEvent.VK_RIGHT: {
                key = "Right";
                break;
            }
            case KeyEvent.VK_LEFT: {
                key = "Left";
                break;
            }
        }

        if (key != null) {
            int modifiers = e.getModifiers();
            String modifiersText = KeyEvent.getKeyModifiersText(modifiers);

            String oldText = label.getText();
            label.setText(oldText + modifiersText + key + " ");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}