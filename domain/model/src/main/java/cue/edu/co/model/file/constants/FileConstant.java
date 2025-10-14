package cue.edu.co.model.file.constants;

import cue.edu.co.model.file.FileContentType;

import java.util.Set;

public class FileConstant {

    private FileConstant(){}
    public static final int MAX_ORIGINAL_NAME_LENGTH = 255;
    public static final int MAX_CONTENT_TYPE_LENGTH = 50;
    public static final int MAX_PATH_LENGTH = 500;

    public static final long MAX_IMAGE_SIZE_BYTES = 5 * 1024 * 1024; // 5 MB

    public static final Set<FileContentType> ALLOWED_IMAGE_TYPES = Set.of(
            FileContentType.IMAGE_JPEG, FileContentType.IMAGE_PNG, FileContentType.IMAGE_WEBP
    );

}
