package com.knapptown.gpmdataexplorer.components;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Base class describing how data processing components will function.
 * In general it is a two step process, validate the input path and then
 * process the data in the given path.
 * @param <Output> The output type of the data processor.
 */
public abstract class DataProcessor<Output> {

    /**
     * Process data containing in a given path.
     * @param path The input path containing the data to be processed.
     * @return The output data that has been processed.
     */
    public final Output process(Path path) {
        validateDataPath(path);
        return processData(path);
    }

    /**
     * Process the data in a given path. Internal method to be implemented
     * by the extending data processor.
     * @param path The input path to the data to be processed.
     * @return The data that has been processed.
     */
    abstract Output processData(Path path);

    /**
     * Validate the data path. This the default implementation just verifying the
     * path exists. But it is designed to be over ridden for extending data
     * processors.
     * @param path The input path to validate.
     */
    void validateDataPath(Path path) {
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("File does not exist: " + path);
        }
    }

}
