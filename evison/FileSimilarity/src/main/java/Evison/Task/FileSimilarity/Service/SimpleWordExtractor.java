package Evison.Task.FileSimilarity.Service;

import Evison.Task.FileSimilarity.InterfacesImp.InterFaceAllFunc;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

@Service
public class SimpleWordExtractor implements InterFaceAllFunc.WordExtractor {

    /*
     * This method reads a file line by line, splits each line into tokens based on non-word characters and adds each token that matches the alphabetic pattern to a set of words.

     param: file the path to the file from which, to extract words,
          return a set of words extracted from the file
     */
    @Override
    public Set<String> extractWords(Path file) {
        Set<String> words = new HashSet<>();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\W+");
                for (String token : tokens) {
                    if (token.matches("[a-zA-Z]+")) {
                        words.add(token.toLowerCase());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file " + file + ": " + e.getMessage());
        }
        return words;
    }
}
