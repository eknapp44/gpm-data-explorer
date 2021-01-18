package com.knapptown.gpmdataexplorer.components;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.knapptown.gpmdataexplorer.mappers.SongModelMapper;
import com.knapptown.gpmdataexplorer.models.Playlist;
import com.knapptown.gpmdataexplorer.models.Song;
import com.knapptown.gpmdataexplorer.models.SongCsvObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;

@Component
public class DataCsvReader {

    private final CsvMapper csvMapper;
    private final CsvSchema csvSchema;
    private final SongModelMapper songModelMapper;

    public DataCsvReader(CsvMapper csvMapper,
                         CsvSchema csvSchema,
                         SongModelMapper songModelMapper) {
        this.csvMapper = csvMapper;
        this.csvSchema = csvSchema;
        this.songModelMapper = songModelMapper;
    }

    public Playlist readPlaylistFromCsv(Path path) throws IOException {
        return  csvMapper.readerFor(Playlist.class)
                .with(csvSchema)
                .readValue(path.toFile());
    }

    public Song readSongFromCsv(Path path) throws IOException {
        SongCsvObject object = csvMapper.readerFor(SongCsvObject.class)
                .with(csvSchema)
                .readValue(path.toFile()) ;
        return songModelMapper.mapSongCsvObjectToSong(object);
    }

}
