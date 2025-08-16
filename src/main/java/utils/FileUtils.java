package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileUtils {

    public void ensureFileExists(Path filePath) {
        try {
            if (filePath.getParent() != null) {
                Files.createDirectories(filePath.getParent());
            }

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (SecurityException e) {
            System.out.println("Don't have permission to create file");
        } catch (IOException e) {
            System.out.println("Error creating file");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot create file in a directory that is not a directory");
        }
    }

    public List<String> readAllLines(Path filePath) {
        try {
            ensureFileExists(filePath);
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println("Failed to read file: " + filePath);
            return List.of();
        }
    }

    public void writeAllLines(Path filePath, List<String> lines) {
        try {
            ensureFileExists(filePath);
            Files.write(filePath, lines);
        } catch (IOException e) {
            System.out.println("Failed to write file: " + filePath);
        }
    }
}
