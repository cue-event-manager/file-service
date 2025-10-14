CREATE TABLE file (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      original_name VARCHAR(255) NOT NULL,
                      content_type VARCHAR(50) NOT NULL,
                      size BIGINT NOT NULL,
                      path VARCHAR(500) NOT NULL,
                      owner_id BIGINT NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      deleted BOOLEAN NOT NULL DEFAULT FALSE
);
