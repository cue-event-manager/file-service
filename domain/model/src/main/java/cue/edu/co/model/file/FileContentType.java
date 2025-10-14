package cue.edu.co.model.file;

import lombok.Getter;

@Getter
public enum FileContentType {

    IMAGE_JPEG("image/jpeg"),
    IMAGE_PNG("image/png"),
    IMAGE_WEBP("image/webp"),
    PDF("application/pdf"),
    WORD("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    EXCEL("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    POWERPOINT("application/vnd.openxmlformats-officedocument.presentationml.presentation"),
    OTHER("application/octet-stream");

    private final String mimeType;

    FileContentType(String mimeType) {
        this.mimeType = mimeType;
    }

    public static FileContentType fromMimeType(String mimeType) {
        for (FileContentType type : values()) {
            if (type.mimeType.equalsIgnoreCase(mimeType)) {
                return type;
            }
        }
        return OTHER;
    }
}
