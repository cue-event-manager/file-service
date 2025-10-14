package cue.edu.co.api.file;

import cue.edu.co.api.file.constants.FileEndpoint;
import cue.edu.co.api.file.dtos.FileResponseDto;
import cue.edu.co.api.file.mappers.FileDtoMapper;
import cue.edu.co.model.file.File;
import cue.edu.co.model.file.commands.UploadFileCommand;
import cue.edu.co.usecase.file.UploadImageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static cue.edu.co.api.common.constants.RequestParamConstant.REQUEST_PARAM_FILE;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final UploadImageUseCase uploadImageUseCase;
    private final FileDtoMapper fileDtoMapper;

    @PostMapping(FileEndpoint.UPLOAD_IMAGE_FILE_ENDPOINT)
    public ResponseEntity<FileResponseDto> uploadImage(@RequestParam(REQUEST_PARAM_FILE) MultipartFile multipartFile)
            throws IOException {

        UploadFileCommand command = new UploadFileCommand(
                multipartFile.getOriginalFilename(),
                multipartFile.getContentType(),
                multipartFile.getSize(),
                multipartFile.getInputStream()
        );

        File savedFile = uploadImageUseCase.execute(command);

        FileResponseDto response = fileDtoMapper.toDto(savedFile);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
