package com.knapptown.gpmdataexplorer.components;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.knapptown.gpmdataexplorer.mappers.PlaylistEntryMapper;
import com.knapptown.gpmdataexplorer.models.Playlist;
import com.knapptown.gpmdataexplorer.models.PlaylistEntry;
import com.knapptown.gpmdataexplorer.models.PlaylistEntryCsvObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;

/**
 * A Reader for CSV data files.
 */
@Component
public class DataCsvReader {

    private final CsvMapper csvMapper;
    private final CsvSchema csvSchema;
    private final PlaylistEntryMapper playlistEntryMapper;

    /**
     * Create a Data CSV Reader using a Playlist Entry Mapper instance.
     * @param playlistEntryMapper A Playlist Entry mapper instance.
     */
    public DataCsvReader(PlaylistEntryMapper playlistEntryMapper) {
        // TODO look into how to have CSV Mapper and Schema in the global bean factory and not affect output.
        this.csvMapper = (CsvMapper) new CsvMapper()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        this.csvSchema =  CsvSchema.emptySchema().withHeader();
        this.playlistEntryMapper = playlistEntryMapper;
    }

    /**
     * Read a CSV File Path containing a Playlist instance.
     * @param path A path to a CSV file.
     * @return A Playlist instance.
     * @throws IOException Thrown when encountering issues reading a CSV file.
     */
    public Playlist readPlaylistFromCsv(Path path) throws IOException {
        return  csvMapper.readerFor(Playlist.class)
                .with(csvSchema)
                .readValue(path.toFile());
    }

    /**
     * Read a CSV File Path containing a Playlist Entry instance.
     *
     * @Todo Look into fixing the issue causing the need for a in-between dto.
     * @param path A path to a CSV file.
     * @return A Playlist Entry instance.
     * @throws IOException Thrown when encountering issues reading a CSV file.
     */
    public PlaylistEntry readPlaylistEntryFromCsv(Path path) throws IOException {
        PlaylistEntryCsvObject entry = csvMapper.readerFor(PlaylistEntryCsvObject.class)
                .with(csvSchema)
                .readValue(path.toFile()) ;
        return playlistEntryMapper.mapPlaylistEntryCsvObjectToPlaylistEntry(entry);
    }


}
