package Evison.Task.FileSimilarity.Controller;
import Evison.Task.FileSimilarity.Service.FileProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class FileComparisonController {

    private final FileProcessingService fileProcessingService;
     @Autowired
    public FileComparisonController(FileProcessingService fileProcessingService) {
        this.fileProcessingService = fileProcessingService;
    }

    @GetMapping("/compare")
    public ResponseEntity<Map<String, String>> compareFiles() throws ExecutionException, InterruptedException {
        Map<Path, Integer> results = fileProcessingService.processFiles();
        Map<String, String> resultsWithPercentage = new HashMap<>();

        for (Map.Entry<Path, Integer> entry : results.entrySet()) {
            resultsWithPercentage.put(entry.getKey().toString(), entry.getValue() + "%");
        }

        return ResponseEntity.ok(resultsWithPercentage);
    }
}