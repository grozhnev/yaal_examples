package cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Создаем объект с заданными датой и временем.
 */
public class CommonsCli {

    @Test
    public void parse() throws ParseException {
        String[] args = new String[]{"-t", "-d", "abc"};

        Options options = new Options();
        options.addOption("t", false, "display current time");
        options.addOption("d", true, "enter string");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        assertTrue(cmd.hasOption("t"));
        assertTrue(cmd.hasOption("d"));
        assertThat(cmd.getOptionValue("d"), equalTo("abc"));
    }

    @Test
    public void printHelp() {
        Options options = new Options();
        options.addOption("t", false, "display current time");
        options.addOption("d", true, "enter string");

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("ant", options);
    }
}
