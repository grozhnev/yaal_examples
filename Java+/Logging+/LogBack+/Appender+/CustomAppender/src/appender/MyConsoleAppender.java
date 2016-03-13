package appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

/**
 * @author Yablokov Aleksey
 */
public class MyConsoleAppender extends AppenderBase<ILoggingEvent> {
    private String prefix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    protected void append(ILoggingEvent event) {
        System.out.println(getPrefix() + "MyConsoleAppender:" + event.getFormattedMessage());
    }
}
