package cue.edu.co.model.file.commands;

import java.io.InputStream;

public record UploadFileCommand(
        String originalName,
        String contentType,
        Long size,
        InputStream content) {
}
