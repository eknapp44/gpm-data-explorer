package com.knapptown.gpmdataexplorer.components;

import com.knapptown.gpmdataexplorer.exceptions.DataProcessingException;
import com.knapptown.gpmdataexplorer.models.Song;
import com.knapptown.gpmdataexplorer.services.SongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Process a Song's metadata CSV file.
 */
@Component
public class SongDataProcessor extends DataCsvProcessor<Song> {

    private static final Logger logger = LoggerFactory.getLogger(SongDataProcessor.class);

    private final SongService songService;
    private final DataStringDecoder dataStringDecoder;

    /**
     * Create a Song Metadata Processor given a Data CSV Reader.
     * @param dataCsvReader A data CSV Reader instance.
     * @param songService A Song Service Instance..
     */
    public SongDataProcessor(DataCsvReader dataCsvReader,
                             DataStringDecoder dataStringDecoder,
                             SongService songService) {
        super(dataCsvReader);
        this.dataStringDecoder = dataStringDecoder;
        this.songService = songService;
    }

    /**
     * Process a Song Metadata CSV File path.
     * @param path A CSV metadata file path.
     * @return A Song instance.
     */
    Song processCsvFile(Path path) {
        logger.info("Processing song metadata file: " + path);
        Song song;
        try {
            song = getDataCsvReader().readSongFromCsv(path);
        } catch (IOException e) {
            throw new DataProcessingException("Failed to process song metadata file: " + path, e);
        }

        if (song == null) {
            throw new IllegalArgumentException("Invalid song metadata file: " + path);
        }

        // Case when hitting a ".csv" or "(1).csv" file from GPM.
        if ( song.getTitle().isBlank()) {
            logger.warn("Found invalid song metadata file: " + path + ". Ignoring...");
            return null;
        }

        song = decodeData(song);
        if (songService.songExists(song)) {
            logger.info("Song '" + song.getTitle() + "' by '" + song.getArtist()
                    + "' on album '" + song.getAlbum() + "' already exists... Skipping.");
        } else {
            song = songService.saveSong(song);
        }

        logger.info("Processed song metadata file: " + song.getTitle() + " by: " + song.getArtist());
        return song;
    }

    /**
     * Decode values of an input song instance and return a decoded song instance.
     * @param song An encoded song instance.
     * @return A decoded song instance.
     */
    private Song decodeData(Song song) {
        return Song.builder()
                .title(dataStringDecoder.decodeDataString(song.getTitle()))
                .artist(dataStringDecoder.decodeDataString(song.getArtist()))
                .album(dataStringDecoder.decodeDataString(song.getAlbum()))
                .durationMs(song.getDurationMs())
                .playCount(song.getPlayCount())
                .playlistIndex(song.getPlaylistIndex())
                .rating(song.getRating())
                .removed(song.isRemoved())
                .build();
    }

}
