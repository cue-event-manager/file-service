package cue.edu.co.jpa.entities;


import cue.edu.co.model.file.FileContentType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SoftDelete;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static cue.edu.co.jpa.constants.FileColumn.*;
import static cue.edu.co.jpa.constants.TableConstant.FILE_TABLE;
import static cue.edu.co.model.file.constants.FileConstant.*;

@Data
@Entity
@SoftDelete
@Table(name = FILE_TABLE)
@EntityListeners(AuditingEntityListener.class)
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = ORIGINAL_NAME, nullable = false, length = MAX_ORIGINAL_NAME_LENGTH)
    private String originalName;

    @Enumerated(EnumType.STRING)
    @Column(name = CONTENT_TYPE, nullable = false, length = MAX_CONTENT_TYPE_LENGTH)
    private FileContentType contentType;

    @Column(name = SIZE, nullable = false)
    private Long size;

    @Column(name = PATH, nullable = false, length = MAX_PATH_LENGTH)
    private String path;

    @Column(name = OWNER_ID, nullable = false)
    @CreatedBy
    private Long ownerId;

    @Column(name = CREATED_AT, nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
}