package com.knapptown.gpmdataexplorer.components;

import com.knapptown.gpmdataexplorer.exceptions.DataProcessingException;
import com.knapptown.gpmdataexplorer.models.PlaylistEntry;
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

import static com.knapptown.gpmdataexplorer.ApplicationConstants.CSV_EXTENSION;

/**
 * A Data Processor for track directories.. A tracks directory contains song metadata CSV files
 * to add to a playlist.
 */
@Component
public class TracksDirectoryProcessor extends DirectoryProcessor<List<PlaylistEntry>> {

    private static final Logger logger = LoggerFactory.getLogger(TracksDirectoryProcessor.class);

    private final PlaylistEntryDataProcessor playlistEntryProcessor;

    /**
     * Create a Tracks Directory processor using a Playlist Entry Metadata Processor instance.
     * @param playlistEntryProcessor A playlist entry metadata processor instance.
     */
    public TracksDirectoryProcessor(PlaylistEntryDataProcessor playlistEntryProcessor) {
        this.playlistEntryProcessor = playlistEntryProcessor;
    }

    /**
     * Process a tracks directory given a track directory path.
     * @param tracksDirectory A tracks directory path.
     * @return A list of playlist entries.
     */
    @Override
    List<PlaylistEntry> processDirectory(Path tracksDirectory) {
        logger.info("Processing tracks directory: " + tracksDirectory);
        List<PlaylistEntry> playlistEntries;
        try (Stream<Path> walk = Files.walk(tracksDirectory, 1)) {
            playlistEntries = walk.map(path -> {
                if (path.equals(tracksDirectory) || path.getFileName().toString().equals(CSV_EXTENSION)) {
                    return null;
                }

                if (path.toFile().isFile() && path.toFile().getName().endsWith(CSV_EXTENSION)) {
                    return playlistEntryProcessor.process(path);
                }
                return null;
            }).filter(Objects::nonNull).collect(Collectors.toList());
        } catch (IOException e) {
            throw new DataProcessingException("Error Processing tracks Directory: " + tracksDirectory, e);
        }

        logger.info("Processed: " + playlistEntries.size() + " playlist entries in tracks directory.");
        logger.info("Processed tracks directory: " + tracksDirectory);
        return playlistEntries;
    }

}
