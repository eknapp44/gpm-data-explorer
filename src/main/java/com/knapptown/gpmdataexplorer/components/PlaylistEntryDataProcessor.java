package com.knapptown.gpmdataexplorer.components;

import com.knapptown.gpmdataexplorer.exceptions.DataProcessingException;
import com.knapptown.gpmdataexplorer.models.PlaylistEntry;
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
public class PlaylistEntryDataProcessor extends DataCsvProcessor<PlaylistEntry> {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistEntryDataProcessor.class);

    private final SongService songService;
    private final DataStringDecoder dataStringDecoder;

    /**
     * Create a Playlist Entry Metadata Processor given a Data CSV Reader.
     * @param dataCsvReader A data CSV Reader instance.
     * @param songService A Song Service Instance..
     */
    public PlaylistEntryDataProcessor(DataCsvReader dataCsvReader,
                                      DataStringDecoder dataStringDecoder,
                                      SongService songService) {
        super(dataCsvReader);
        this.dataStringDecoder = dataStringDecoder;
        this.songService = songService;
    }

    /**
     * Process a Playlist Entry Metadata CSV File path.
     * @param path A CSV metadata file path.
     * @return A Playlist Entry instance.
     */
    PlaylistEntry processCsvFile(Path path) {
        logger.info("Processing playlist entry metadata file: " + path);
        PlaylistEntry playlistEntry;
        try {
            playlistEntry = getDataCsvReader().readPlaylistEntryFromCsv(path);
        } catch (IOException e) {
            throw new DataProcessingException("Failed to process playlist entry metadata file: " + path, e);
        }

        if (playlistEntry == null) {
            throw new IllegalArgumentException("Invalid playlist entry metadata file: " + path);
        }

        Song song = playlistEntry.getSong();

        // Case when hitting a ".csv" or "(1).csv" file from GPM.
        if (song.getTitle().isBlank()) {
            logger.warn("Found invalid playlist entry metadata file: " + path + ". Ignoring...");
            return null;
        }

        song = decodeData(song);
        // TODO check for duplicates.
        song = songService.saveSong(song);

        playlistEntry.setSong(song);

        logger.info("Processed playlist entry metadata file: " + song.getTitle() + " by: " + song.getArtist());
        return playlistEntry;
    }

    /**
     * Decode values of an input song instance and return a decoded song instance.
     * @param song An encoded song instance.
     * @return A decoded song instance.
     */
    private Song decodeData(Song song) {
        return Song.builder()
                .id(song.getId())
                .title(dataStringDecoder.decodeDataString(song.getTitle()))
                .artist(dataStringDecoder.decodeDataString(song.getArtist()))
                .album(dataStringDecoder.decodeDataString(song.getAlbum()))
                .durationMs(song.getDurationMs())
                .playCount(song.getPlayCount())
                .rating(song.getRating())
                .removed(song.isRemoved())
                .build();
    }

}
