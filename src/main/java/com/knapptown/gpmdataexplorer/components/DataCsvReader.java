package com.knapptown.gpmdataexplorer.components;

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
     * Create a Data CSV Reader using schema, mapper, and Playlist Entry Mapper instances.
     * @param csvMapper A CSV Mapper instance.
     * @param csvSchema A CSV Schema instance.
     * @param playlistEntryMapper A Playlist Entry mapper instance.
     */
    public DataCsvReader(CsvMapper csvMapper,
                         CsvSchema csvSchema,
                         PlaylistEntryMapper playlistEntryMapper) {
        this.csvMapper = csvMapper;
        this.csvSchema = csvSchema;
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
