package Evison.Task.FileSimilarity.FilesConfig;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@Data
@ConfigurationProperties(prefix = "file")
public class FileProperties {
    private String pathA;
    private String directory;
}
