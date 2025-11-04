package cue.edu.co.model.file.builders;

import java.util.UUID;

public class FilePathBuilder {
    public static String buildImagePath(String extension) {
        String uuid = UUID.randomUUID().toString();
        String safeExtension = extension == null || extension.isBlank() ? "" : "." + extension;
        return "images/" + uuid + safeExtension;
    }

    public static String buildFilePath(String extension) {
        String uuid = UUID.randomUUID().toString();
        String safeExtension = extension == null || extension.isBlank() ? "" : "." + extension;
        return "files/" + uuid + safeExtension;
    }
}
