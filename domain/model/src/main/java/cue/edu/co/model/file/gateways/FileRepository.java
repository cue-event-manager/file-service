package cue.edu.co.model.file.gateways;

import cue.edu.co.model.file.File;

import java.util.Optional;
import java.util.Set;

public interface FileRepository {
    File save(File file);
    Optional<File> findById(Long id);
    Set<File> findAllByIds(Set<Long> ids);
}