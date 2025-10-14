package cue.edu.co.model.file.gateways;

import java.io.InputStream;
import java.util.Set;

public interface FileStorage {
    String upload(String path, InputStream content, String contentType);
    String generatePresignedUrl(String path, int expirationMinutes);
    byte[] download(String path);
    void delete(String path);
    Set<String> generatePresignedUrls(Set<String> paths, int expirationMinutes);
}
