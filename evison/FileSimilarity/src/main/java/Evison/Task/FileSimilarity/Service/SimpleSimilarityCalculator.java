package Evison.Task.FileSimilarity.Service;

import Evison.Task.FileSimilarity.InterfacesImp.InterFaceAllFunc;

import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class SimpleSimilarityCalculator implements InterFaceAllFunc.SimilarityCalculator {

    /*
       param: fileAWords Set of words extracted from file A
       param: fileWords Set of words extracted from another file
       return: Similarity score as a percentage
     */
    @Override
    public Integer calculateSimilarityScore(Set<String> fileAWords, Set<String> fileWords) {
        long commonWords = fileWords.stream().filter(fileAWords::contains).count();
        return (int) Math.round((double) commonWords / fileAWords.size() * 100);
    }
}
