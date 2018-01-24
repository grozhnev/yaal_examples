package util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResourceUtil {

    private ResourceUtil() {
    }

    public static String resourceToString(Class<?> clazz, String resourceName) {
        try {
            URL resourceUrl = clazz.getResource(resourceName);
            Path path = new File(resourceUrl.getFile()).toPath();
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static File resourceToFile(Class<?> clazz, String resourceName) {
        URL resourceUrl = clazz.getResource(resourceName);
        return new File(resourceUrl.getFile());
    }

    public static String resourceToPath(Class<?> clazz, String resourceName) {
        URL resourceUrl = clazz.getResource(resourceName);
        return resourceUrl.getFile();
    }
}
