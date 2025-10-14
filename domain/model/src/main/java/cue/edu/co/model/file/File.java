package cue.edu.co.model.file;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class File {
    private Long id;
    private String originalName;
    private FileContentType contentType;
    private Long size;
    private String path;
    private Long ownerId;
    private LocalDateTime createdAt;
}
