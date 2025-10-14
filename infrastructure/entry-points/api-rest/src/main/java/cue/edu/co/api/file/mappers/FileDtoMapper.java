package cue.edu.co.api.file.mappers;

import cue.edu.co.api.file.dtos.FileResponseDto;
import cue.edu.co.model.file.File;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileDtoMapper {
    FileResponseDto toDto(File file);
}
