package com.knapptown.gpmdataexplorer;

import com.knapptown.gpmdataexplorer.components.DataDirectoryProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;
import java.util.Optional;

/**
 * Main configuration for processing data. This configuration sets up components
 * for reading in CSV files and also initiates the processing of the configured
 * data directory, "gpm.data.explorer.data.directory".
 */
@Configuration
public class DataProcessingConfiguration {

    /**
     * The configuration property specifying the base data directory to process.
     */
    @Value("${gpm.data.explorer.data.directory:#{null}}")
    private Optional<String> dataDirectory;

    /**
     * A Command Line Runner instance that triggers the initial processing of a configured
     * data directory.
     * @param processor A data directory processor instance.
     * @return A Command Line Runner instance.
     */
    @Bean
    public CommandLineRunner loadDatabase(DataDirectoryProcessor processor) {
        return dataDirectory.<CommandLineRunner>map(directory ->
                args -> processor.process(Paths.get(directory))).orElse(null);
    }
    
}
