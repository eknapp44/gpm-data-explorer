package com.knapptown.gpmdataexplorer.components;

import com.knapptown.gpmdataexplorer.exceptions.DataProcessingException;
import com.knapptown.gpmdataexplorer.models.Playlist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A Data Directory Processor. This is the root data processing instance which triggers
 * processing of playlist(s) directories which triggers processing of songs/tracks
 * directories.
 */
@Component
public class DataDirectoryProcessor extends  DirectoryProcessor<List<Playlist>> {

    private final PlaylistDirectoryProcessor playlistDirectoryProcessor;

    private static final String THUMBS_UP_PLAYLIST = "Thumbs Up";

    private static final Logger logger = LoggerFactory.getLogger(DataDirectoryProcessor.class);

    /**
     * Create a Data Directory Processor using a Playlist Directory Processor instance.
     * @param playlistDirectoryProcessor A Playlist Directory Processor instance.
     */
    public DataDirectoryProcessor(PlaylistDirectoryProcessor playlistDirectoryProcessor) {
        this.playlistDirectoryProcessor = playlistDirectoryProcessor;
    }

    /**
     * Process the root Data Directory.
     * @param dataDirectory A path to the base Application Data Directory.
     * @return A List of Playlists successfully processed.
     */
    @Override
     List<Playlist> processDirectory(Path dataDirectory) {
        logger.info("Processing data directory: " + dataDirectory);

        List<Playlist> playlists;
        try (Stream<Path> walk = Files.walk(dataDirectory, 1)) {
            playlists = walk.map(path -> {
                if (path.equals(dataDirectory)) {
                    return null;
                }

                if (path.endsWith(THUMBS_UP_PLAYLIST)) {
                    return null;
                }

                return playlistDirectoryProcessor.process(path);
            }).filter(Objects::nonNull).collect(Collectors.toList());
        } catch (IOException e) {
            throw new DataProcessingException("Error processing Data Directory: " + dataDirectory, e);
        }

        logger.info("Processed " + playlists.size() + " playlists.");
        logger.info("Processed data directory: " + dataDirectory);
        return playlists;
    }

}
