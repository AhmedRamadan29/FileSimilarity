# File Similarity Service

## Overview
This project provides a service for comparing the similarity between a reference file and multiple files within a directory. The similarity is calculated based on the number of common words between the files. The service is implemented using Spring Boot and is designed to be scalable using Java's concurrency framework.

##  Contents
1. [Project Structure](#project-structure)
2. [Dependencies](#dependencies)
3. [Configuration](#configuration)
4. [Usage](#usage)
5. [Services](#services)
    - [FileProcessingService](#fileprocessingservice)
    - [SimpleSimilarityCalculator](#simplesimilaritycalculator)
    - [SimpleWordExtractor](#simplewordextractor)
6. [Interfaces](#interfaces)
    - [SimilarityCalculator](#similaritycalculator)
    - [WordExtractor](#wordextractor)
    - [FileProcessing](#fileprocessing)
7. [Controller](#controller)
8. [API Endpoints](#api-endpoints)


## Dependencies
The project uses the following dependencies:
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Test

Ensure these dependencies are included in your `pom.xml` file.

## Configuration
The `application.properties` file should be configured with the paths for the reference file and the directory containing files to compare.
  pathA: "path/to/reference/file"
  directory: "path/to/files/directory"

 ## services
FileProcessingService
The FileProcessingService processes files and calculates their similarity score with a reference file.

Dependencies: WordExtractor, SimilarityCalculator, FileProperties
Concurrency: Uses an ExecutorService with a fixed thread pool.
Method: processFiles
Extracts words from the reference file.
Iterates over files in the specified directory.
Submits tasks to the executor service to calculate similarity scores asynchronously.
Collects and returns the similarity scores.
SimpleSimilarityCalculator
The SimpleSimilarityCalculator calculates the similarity score between two sets of words.

Method: calculateSimilarityScore
Takes two sets of words as input.
Calculates the number of common words.
Returns the similarity score as a percentage.
SimpleWordExtractor
The SimpleWordExtractor extracts words from a file.

Method: extractWords
Reads a file line by line.
Splits each line into tokens based on non-word characters.
Adds alphabetic tokens to a set of words.

## Interfaces

SimilarityCalculator
Functional interface for calculating similarity scores

SimilarityCalculator
Functional interface for calculating similarity scores.

##java


@FunctionalInterface
public interface SimilarityCalculator {
    Integer calculateSimilarityScore(Set<String> fileAWords, Set<String> fileWords);
}
WordExtractor
Functional interface for extracting words from a file.

##java

@FunctionalInterface
public interface WordExtractor {
    Set<String> extractWords(Path file);
}
FileProcessing
Functional interface for processing files.

##java

@FunctionalInterface
public interface FileProcessing {
    Map<Path, Integer> processFiles() throws InterruptedException, ExecutionException;
}

## Controller
FileComparisonController
The FileComparisonController exposes the file comparison functionality as a REST API.

Endpoint: /api/compare
Method: compareFiles
Invokes FileProcessingService to process files.
Converts the results to a map with file paths and similarity percentages.
Returns the results as a JSON response.
API Endpoints
Compare Files
GET /api/compare

Description: Compares files in the specified directory with the reference file.
Response: JSON object with file paths as keys and similarity percentages as values.
Example Response:

## json

{
  "path/to/file1.txt": "85%",
  "path/to/file2.txt": "75%"
}



  
