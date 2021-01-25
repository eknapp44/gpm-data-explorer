package com.knapptown.gpmdataexplorer;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import com.knapptown.gpmdataexplorer.components.DataDirectoryProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

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
    @Value("${gpm.data.explorer.data.directory}")
    private String dataDirectory;

    /**
     * A Jackson CSV Mapper instance. It is configured specially to accept cse insensitive properties.
     * @return A Jackson CSV Mapper instance.
     */
    @Bean
    public CsvMapper csvMapper() {
        return (CsvMapper) new CsvMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    /**
     * A Jackson CSV Schema instance. It is configured to use headers.
     * @return A Jackson CSV Schema instance.
     */
    @Bean
    public CsvSchema csvSchema() {
        return CsvSchema.emptySchema().withHeader();
    }

    /**
     * A Command Line Runner instance that triggers the initial processing of a configured
     * data directory.
     * @param processor A data directory processor instance.
     * @return A Command Line Runner instance.
     */
    @Bean
    public CommandLineRunner loadDatabase(DataDirectoryProcessor processor) {
        return args -> processor.process(Paths.get(dataDirectory));
    }
    
}
