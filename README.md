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

  
