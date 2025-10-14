package cue.edu.co.model.file.exceptions;

import cue.edu.co.model.common.BusinessException;

public class FileTooLargeException extends BusinessException {
    public FileTooLargeException(Long maxSize) {
        super("El archivo supera el limite: " + maxSize.toString(), "FILE_TOO_LARGE");
    }
}
