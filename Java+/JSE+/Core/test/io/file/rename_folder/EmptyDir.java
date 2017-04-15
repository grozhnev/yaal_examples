package io.file.rename_folder;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static java.lang.System.out;

public class EmptyDir {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void file() throws IOException {
        out.println("===== File#renameTo =====");
        File source = folder.newFolder();
        out.printf("SOURCE: %s%n", source);

        File target = new File(source.getParent(), "renamed-file_" + source.getName());
        out.printf("TARGET: %s%n", target);

        boolean result = source.renameTo(target);
        out.printf("RESULT: %s%n%n", result);
    }
}