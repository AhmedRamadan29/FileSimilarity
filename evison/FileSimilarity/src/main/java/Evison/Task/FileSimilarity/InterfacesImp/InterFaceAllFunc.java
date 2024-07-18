package Evison.Task.FileSimilarity.InterfacesImp;

import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public interface InterFaceAllFunc {
    @FunctionalInterface
    interface SimilarityCalculator { Integer calculateSimilarityScore(Set<String> fileAWords, Set<String> fileWords);}
    @FunctionalInterface
    interface WordExtractor  { Set<String> extractWords(Path file);}


    @FunctionalInterface
    interface FileProcessing  {
        Map<Path, Integer> processFiles() throws InterruptedException, ExecutionException;}

}
