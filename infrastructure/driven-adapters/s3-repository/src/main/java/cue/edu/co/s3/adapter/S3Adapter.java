package cue.edu.co.s3.adapter;

import cue.edu.co.model.file.gateways.FileStorage;
import org.springframework.stereotype.Repository;
import cue.edu.co.s3.config.model.S3ConnectionProperties;
import cue.edu.co.s3.operations.S3Operations;
import lombok.AllArgsConstructor;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.Set;
import java.util.stream.Collectors;


@Repository
@AllArgsConstructor
public class S3Adapter implements FileStorage {

    private final S3Operations s3Operations;
    private final S3ConnectionProperties properties;
    private final S3Presigner s3Presigner;

    @Override
    public String upload(String path, InputStream content, String contentType) {
        try {
            byte[] bytes = content.readAllBytes();
            boolean success = s3Operations.uploadObject(
                    properties.bucketName(),
                    path,
                    bytes
            );

            if (!success) {
                throw new RuntimeException("Failed to upload file to S3");
            }

            return path;
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file to S3: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] download(String path) {
        try (InputStream input = s3Operations.getObject(properties.bucketName(), path);
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

            input.transferTo(buffer);
            return buffer.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error downloading file from S3: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(String path) {
        boolean success = s3Operations.deleteObject(properties.bucketName(), path);
        if (!success) {
            throw new RuntimeException("Failed to delete file from S3: " + path);
        }
    }

    @Override
    public String generatePresignedUrl(String path, int expirationMinutes) {
        try {
            var getObjectRequest = GetObjectRequest.builder()
                    .bucket(properties.bucketName())
                    .key(path)
                    .build();

            var presignRequest = GetObjectPresignRequest.builder()
                    .getObjectRequest(getObjectRequest)
                    .signatureDuration(Duration.ofMinutes(expirationMinutes))
                    .build();

            return s3Presigner.presignGetObject(presignRequest)
                    .url()
                    .toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generating presigned URL: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<String> generatePresignedUrls(Set<String> paths, int expirationMinutes) {
        return paths.stream()
                .map(path -> generatePresignedUrl(path, expirationMinutes))
                .collect(Collectors.toSet());
    }
}

