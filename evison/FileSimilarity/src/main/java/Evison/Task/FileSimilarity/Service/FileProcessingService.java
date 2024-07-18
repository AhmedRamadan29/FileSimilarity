package Evison.Task.FileSimilarity.Service;
import Evison.Task.FileSimilarity.InterfacesImp.InterFaceAllFunc;


import Evison.Task.FileSimilarity.FilesConfig.FileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
@Service
public class FileProcessingService implements InterFaceAllFunc.FileProcessing {

    private final InterFaceAllFunc.WordExtractor wordExtractor;
    private final InterFaceAllFunc.SimilarityCalculator similarityCalculator;
    private final FileProperties fileProperties;
    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Autowired
    public FileProcessingService(InterFaceAllFunc.WordExtractor wordExtractor, InterFaceAllFunc.SimilarityCalculator similarityCalculator, FileProperties fileProperties) {
        this.wordExtractor = wordExtractor;
        this.similarityCalculator = similarityCalculator;
        this.fileProperties = fileProperties;
    }
    @Override
    public Map<Path, Integer> processFiles() throws InterruptedException, ExecutionException {
        Path fileA = Paths.get(fileProperties.getPathA());
        Path directory = Paths.get(fileProperties.getDirectory());
        Set<String> fileAWords = wordExtractor.extractWords(fileA);
        List<CompletableFuture<Map.Entry<Path, Integer>>> futures = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path file : stream) {
                futures.add(CompletableFuture.supplyAsync(() -> {
                    Set<String> fileWords = wordExtractor.extractWords(file);
                    int score = similarityCalculator.calculateSimilarityScore(fileAWords, fileWords);
                    return new AbstractMap.SimpleEntry<>(file, score);
                }, executorService));
            }
        } catch (IOException exception) {
            System.err.println("error reading directory: " + exception.getMessage());
        }

        Map<Path, Integer> fileScores = new HashMap<>();
        for (CompletableFuture<Map.Entry<Path, Integer>> future : futures) {
            Map.Entry<Path, Integer> entry = future.get();
            fileScores.put(entry.getKey(), entry.getValue());
        }

        return fileScores;
    }



}