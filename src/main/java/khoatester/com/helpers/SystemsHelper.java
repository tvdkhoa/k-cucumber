package khoatester.com.helpers;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class SystemsHelper {

    public static String getCurrentDir() {
        String current = System.getProperty("user.dir") + File.separator;
        return current;
    }
    public static void CleanTargetDirectory()
    {
        String targetPath = getCurrentDir()+"target/allure-results";
        Path directory = Paths.get(targetPath);
        try (Stream<Path> paths = Files.walk(directory)) {
            paths
                    .sorted((a, b) -> b.compareTo(a)) // sort in reverse order to delete files before directories
                    .forEach(path -> {
                        if (!Files.isDirectory(path) || !path.equals(directory)) { // don't delete the target directory itself
                            try {
                                Files.delete(path);
                            } catch (IOException e) {
                                System.err.println("Failed to delete " + path + ": " + e.getMessage());
                            }
                        }
                    });
        } catch (IOException e) {
            System.err.println("Error when trying to clean the directory: " + e.getMessage());
        }
    }
}
