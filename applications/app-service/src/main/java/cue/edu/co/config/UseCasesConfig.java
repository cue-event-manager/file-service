package cue.edu.co.config;

import cue.edu.co.model.file.gateways.FileRepository;
import cue.edu.co.model.file.gateways.FileStorage;
import cue.edu.co.usecase.file.UploadFileUseCase;
import cue.edu.co.usecase.file.UploadImageUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "cue.edu.co.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {
    @Bean
    public UploadImageUseCase uploadImageUseCase(FileRepository fileRepository,
                                                 FileStorage fileStorage){
        return new UploadImageUseCase(fileRepository, fileStorage);
    }

    @Bean
    public UploadFileUseCase uploadFileUseCase(FileRepository fileRepository,
                                                FileStorage fileStorage){
        return new UploadFileUseCase(fileRepository, fileStorage);
    }
}
