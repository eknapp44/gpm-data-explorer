package com.knapptown.gpmdataexplorer.components;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * An extension of the base Data Processor type designed to analyze input
 * paths that are directories.
 */
public abstract class DirectoryProcessor<Output> extends DataProcessor<Output> {

    final Output processData(Path path) {
        return processDirectory(path);
    }

    /**
     * An extension of the base validation method that in addition checks
     * that the input path is a directory.
     * @param path The input path to validate.
     */
    @Override
    void validateDataPath(Path path) {
        super.validateDataPath(path);
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Directory does not exist: " + path);
        }
    }

    /**
     * Method to be implemented by extending types to process directories.
     * @param path Path to a Data Directory.
     * @return Processed output data.
     */
    abstract Output processDirectory(Path path);

}
