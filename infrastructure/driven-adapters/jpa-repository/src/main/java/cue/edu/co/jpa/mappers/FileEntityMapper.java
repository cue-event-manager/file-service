package cue.edu.co.jpa.mappers;

import cue.edu.co.jpa.entities.FileEntity;
import cue.edu.co.model.file.File;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileEntityMapper {
    File toDomain(FileEntity fileEntity);

    FileEntity toEntity(File file);
}
