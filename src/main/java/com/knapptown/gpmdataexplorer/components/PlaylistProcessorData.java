package com.knapptown.gpmdataexplorer.components;

import com.knapptown.gpmdataexplorer.exceptions.DataProcessingException;
import com.knapptown.gpmdataexplorer.models.Playlist;
import com.knapptown.gpmdataexplorer.services.PlaylistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Data Processor for Playlist Metadata CSV Files.
 */
@Component
public class PlaylistProcessorData extends DataCsvProcessor<Playlist> {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistProcessorData.class);

    private final PlaylistService playlistService;

    /**
     * Create a Playlist Metadata CSV  Processor using a Data CSV Reader instance and
     * a Playlist Service instance.
     * @param dataCsvReader A data CSV reader instance.
     * @param playlistService A playlist service instance.
     */
    public PlaylistProcessorData(DataCsvReader dataCsvReader,
                                 PlaylistService playlistService) {
        super(dataCsvReader);
        this.playlistService = playlistService;
    }

    /**
     * Process a Playlist Metadata CSV File path.
     * @param path A CSV metadata file path.
     * @return A playlist instance.
     */
    @Override
    Playlist processCsvFile(Path path) {
        logger.info("Processing playlist metadata file: " + path);
        Playlist playlist;
        try {
            playlist = getDataCsvReader().readPlaylistFromCsv(path);
        } catch (IOException e) {
            throw new DataProcessingException("Error processing Playlist Metadata File: " + path, e);
        }

        if (playlist == null) {
            throw new IllegalArgumentException("Invalid playlist metadata file: " + path);
        }

        playlist = playlistService.savePlaylist(playlist);

        logger.info("Processed metadata file for playlist: " + playlist.getTitle());
        return  playlist;
    }
}
