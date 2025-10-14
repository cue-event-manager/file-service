package cue.edu.co.model.file.exceptions;

import cue.edu.co.model.common.BusinessException;

public class InvalidFileTypeException extends BusinessException {
    public InvalidFileTypeException() {
        super("Formato de archivo no valido", "INVALID_FILE_TYPE");
    }
}
