package com.knapptown.gpmdataexplorer.components;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.knapptown.gpmdataexplorer.mappers.SongMapper;
import com.knapptown.gpmdataexplorer.models.Playlist;
import com.knapptown.gpmdataexplorer.models.Song;
import com.knapptown.gpmdataexplorer.models.SongCsvObject;
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
    private final SongMapper songMapper;

    /**
     * Create a Data CSV Reader using schema, mapper, and Song Model Mapper instances.
     * @param csvMapper A CSV Mapper instance.
     * @param csvSchema A CSV Schema instance.
     * @param songMapper A Song mapper instance.
     */
    public DataCsvReader(CsvMapper csvMapper,
                         CsvSchema csvSchema,
                         SongMapper songMapper) {
        this.csvMapper = csvMapper;
        this.csvSchema = csvSchema;
        this.songMapper = songMapper;
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
     * Read a CSV File Path containing a Song instance.
     *
     * @Todo Look into fixing the issue causing the need for a in-between dto.
     * @param path A path to a CSV file.
     * @return A Song instance.
     * @throws IOException Thrown when encountering issues reading a CSV file.
     */
    public Song readSongFromCsv(Path path) throws IOException {
        SongCsvObject object = csvMapper.readerFor(SongCsvObject.class)
                .with(csvSchema)
                .readValue(path.toFile()) ;
        return songMapper.mapSongCsvObjectToSong(object);
    }

}
