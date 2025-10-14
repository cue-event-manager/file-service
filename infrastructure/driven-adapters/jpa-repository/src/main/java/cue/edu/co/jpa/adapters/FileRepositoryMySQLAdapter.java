package cue.edu.co.jpa.adapters;

import cue.edu.co.jpa.entities.FileEntity;
import cue.edu.co.jpa.mappers.FileEntityMapper;
import cue.edu.co.jpa.repositories.FileJpaRepository;
import cue.edu.co.model.file.File;
import cue.edu.co.model.file.gateways.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FileRepositoryMySQLAdapter implements FileRepository {

    private final FileJpaRepository fileJpaRepository;
    private final FileEntityMapper fileEntityMapper;

    @Override
    public File save(File file) {
        FileEntity fileToSave = fileEntityMapper.toEntity(file);
        return fileEntityMapper.toDomain(fileJpaRepository.save(fileToSave));
    }

    @Override
    public Optional<File> findById(Long id) {
        return fileJpaRepository
                .findById(id)
                .map(fileEntityMapper::toDomain);
    }

    @Override
    public Set<File> findAllByIds(Set<Long> ids) {
        return fileJpaRepository
                .findAllById(ids)
                .stream()
                .map(fileEntityMapper::toDomain)
                .collect(Collectors.toSet());
    }
}
