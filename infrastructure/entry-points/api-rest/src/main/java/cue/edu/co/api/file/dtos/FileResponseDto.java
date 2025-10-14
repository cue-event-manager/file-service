package cue.edu.co.api.file.dtos;

import cue.edu.co.model.file.FileContentType;

import java.time.LocalDateTime;

public record FileResponseDto(
        Long id,
        String originalName,
        FileContentType contentType,
        Long size,
        String path,
        Long ownerId,
        LocalDateTime createdAt
) {
}
