package com.knapptown.gpmdataexplorer.components;

import java.nio.file.Files;
import java.nio.file.Path;

import static com.knapptown.gpmdataexplorer.ApplicationConstants.CSV_EXTENSION;

/**
 * An extension of the Data Processor class to handle processing CSV metadata
 * files.
 * @param <Output> The output data for a CSV metadata file.
 */
public abstract class DataCsvProcessor<Output> extends DataProcessor<Output> {

    private final DataCsvReader dataCsvReader;

    DataCsvProcessor(DataCsvReader dataCsvReader) {
        this.dataCsvReader = dataCsvReader;
    }

    /**
     * Process a CSV Metadata file path.
     * @param path The input path to the data to be processed.
     * @return Output data as processed from the input path.
     */
    @Override
    final Output processData(Path path) {
        return processCsvFile(path);
    }

    /**
     * Validate a given input path. In addition to the super class checks
     * for existence this implementation checks that the path is a normal file
     * and the name ends with ".csv".
     * @param path The input path to validate.
     */
    @Override
    void validateDataPath(Path path) {
        super.validateDataPath(path);
        if (Files.isDirectory(path)) {
            throw new IllegalArgumentException("Invalid metadata file: " + path + ". Must not be a directory.");
        }
        if (!path.toFile().getName().endsWith(CSV_EXTENSION)) {
            throw new IllegalArgumentException("Invalid  metadata file: " + path + ". Must be a CSV file.");
        }
    }

    /**
     * A method for extending classes to process a given CSV metadata file and return
     * output data.
     * @param path A CSV metadata file path.
     * @return Output data as processed from the input metadata CSV file path.
     */
    abstract Output processCsvFile(Path path);

    /**
     * A method for retrieving a data CSV reader instance.
     * @return A Data CSV Reader instance.
     */
     DataCsvReader getDataCsvReader() {
         return this.dataCsvReader;
     }

}
