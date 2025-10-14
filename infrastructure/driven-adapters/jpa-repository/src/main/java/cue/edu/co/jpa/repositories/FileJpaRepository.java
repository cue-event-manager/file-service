package cue.edu.co.jpa.repositories;

import cue.edu.co.jpa.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FileJpaRepository extends JpaRepository<FileEntity, Long>, JpaSpecificationExecutor<FileEntity> {
}
