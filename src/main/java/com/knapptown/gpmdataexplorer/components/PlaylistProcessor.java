package com.knapptown.gpmdataexplorer.components;

import com.knapptown.gpmdataexplorer.exceptions.DataProcessingException;
import com.knapptown.gpmdataexplorer.models.Playlist;
import com.knapptown.gpmdataexplorer.services.PlaylistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
public class PlaylistProcessor {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistProcessor.class);

    private final static String CSV_EXTENSION = ".csv";
    private final static String METADATA_FILE = "metadata" + CSV_EXTENSION;
    private final static String TRACK_DIRECTORY = "Tracks";

    private final DataCsvReader dataCsvReader;
    private final SongProcessor songProcessor;
    private final PlaylistService playlistService;

    public PlaylistProcessor(DataCsvReader dataCsvReader,
                             SongProcessor songProcessor,
                             PlaylistService playlistService) {
        this.dataCsvReader = dataCsvReader;
        this.songProcessor = songProcessor;
        this.playlistService = playlistService;
    }

    public void processPlaylistDirectory(Path playlistDirectoryPath) {
        logger.info("Processing Playlist Directory: " + playlistDirectoryPath);
        try (Stream<Path> walk = Files.walk(playlistDirectoryPath, 1)) {
            walk.forEach(path -> {
                if (path.equals(playlistDirectoryPath)) {
                    return;
                }

                if (path.endsWith(METADATA_FILE)) {
                    try {
                        processPlaylistMetadataFile(path);
                    } catch (IOException e) {
                        throw new DataProcessingException("Error processing Playlist Metadata File: " + path, e);
                    }
                }

                if (path.endsWith(TRACK_DIRECTORY)) {
                    songProcessor.processTracksDirectory(path);
                }
            });
        } catch (IOException e) {
            throw new DataProcessingException("Error processing Playlist Directory: " + playlistDirectoryPath, e);
        }
        logger.info("Processed playlist directory: " + playlistDirectoryPath);
    }

    private void processPlaylistMetadataFile(Path metadataFile) throws IOException {
        logger.info("Processing playlist metadata file: " + metadataFile);
        Playlist playlist  = dataCsvReader.readPlaylistFromCsv(metadataFile);

        if (playlist == null) {
            throw new IllegalArgumentException("Invalid playlist metadata file: " + metadataFile);
        }

        playlistService.savePlaylist(playlist);
        logger.info("Processed metadata file for playlist: " + playlist.getTitle());
    }

}
