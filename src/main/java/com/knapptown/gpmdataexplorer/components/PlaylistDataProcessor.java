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
public class PlaylistDataProcessor extends DataCsvProcessor<Playlist> {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistDataProcessor.class);

    private final PlaylistService playlistService;
    private final DataStringDecoder dataStringDecoder;

    /**
     * Create a Playlist Metadata CSV  Processor using a Data CSV Reader instance and
     * a Playlist Service instance.
     * @param dataCsvReader A data CSV reader instance.
     * @param playlistService A playlist service instance.
     */
    public PlaylistDataProcessor(DataCsvReader dataCsvReader,
                                 DataStringDecoder dataStringDecoder,
                                 PlaylistService playlistService) {
        super(dataCsvReader);
        this.dataStringDecoder = dataStringDecoder;
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

        playlist = decodeData(playlist);

        Playlist existingPlaylist = playlistService.getByTitleAndOwner(playlist.getTitle(), playlist.getOwner());
        playlist = existingPlaylist == null ? playlistService.create(playlist) : existingPlaylist;

        logger.info("Processed metadata file for playlist: " + playlist.getTitle());
        return  playlist;
    }

    /**
     * Decode any data strings in the input playlist and use as
     * an output playlist.
     * @param playlist A playlist instance to be decoded.
     * @return A decoded playlist instance.
     */
    private Playlist decodeData(Playlist playlist) {
        return Playlist.builder()
                .id(playlist.getId())
                .title(dataStringDecoder.decodeDataString(playlist.getTitle()))
                .description(dataStringDecoder.decodeDataString(playlist.getDescription()))
                .owner(dataStringDecoder.decodeDataString(playlist.getOwner()))
                .deleted(playlist.isDeleted())
                .shared(playlist.isShared())
                .build();
    }

}
