package cue.edu.co.usecase.file;

import cue.edu.co.model.file.File;
import cue.edu.co.model.file.FileContentType;
import cue.edu.co.model.file.builders.FilePathBuilder;
import cue.edu.co.model.file.commands.UploadFileCommand;
import cue.edu.co.model.file.exceptions.FileTooLargeException;
import cue.edu.co.model.file.exceptions.InvalidFileTypeException;
import cue.edu.co.model.file.gateways.FileRepository;
import cue.edu.co.model.file.gateways.FileStorage;
import cue.edu.co.model.file.utils.FileUtil;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import static cue.edu.co.model.file.constants.FileConstant.ALLOWED_IMAGE_TYPES;
import static cue.edu.co.model.file.constants.FileConstant.MAX_IMAGE_SIZE_BYTES;

@RequiredArgsConstructor
public class UploadImageUseCase {
    private final FileRepository fileRepository;
    private final FileStorage fileStorage;


    public File execute(UploadFileCommand command) {
        validateFile(command);

        String extension = FileUtil.extractExtension(command.originalName());
        String path = FilePathBuilder.buildImagePath(extension);

        fileStorage.upload(path, command.content(), command.contentType());

        File file = File.builder()
                .originalName(command.originalName())
                .contentType(FileContentType.fromMimeType(command.contentType()))
                .size(command.size())
                .path(path)
                .createdAt(LocalDateTime.now())
                .build();

        return fileRepository.save(file);
    }


    private void validateFile(UploadFileCommand command) {
        validateType(command.contentType());
        validateSize(command.size());
    }

    private void validateType(String mimeType) {
        FileContentType type = FileContentType.fromMimeType(mimeType);
        if (!ALLOWED_IMAGE_TYPES.contains(type)) {
            throw new InvalidFileTypeException();
        }
    }

    private void validateSize(Long size) {
        if (size == null || size <= 0) {
            throw new FileTooLargeException(MAX_IMAGE_SIZE_BYTES);
        }
        if (size > MAX_IMAGE_SIZE_BYTES) {
            throw new FileTooLargeException(MAX_IMAGE_SIZE_BYTES);
        }
    }
}

