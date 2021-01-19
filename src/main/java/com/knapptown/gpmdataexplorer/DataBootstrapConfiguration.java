package com.knapptown.gpmdataexplorer;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBootstrapConfiguration {

    @Bean
    public CsvMapper csvMapper() {
        return (CsvMapper) new CsvMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    @Bean
    public CsvSchema csvSchema() {
        return CsvSchema.emptySchema().withHeader();
    }
    
}
