package com.knapptown.gpmdataexplorer.components;

import com.knapptown.gpmdataexplorer.exceptions.DataProcessingException;
import com.knapptown.gpmdataexplorer.models.Song;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.knapptown.gpmdataexplorer.ApplicationConstants.CSV_EXTENSION;

/**
 * A Data Processor for track directories.. A tracks directory contains song metadata CSV files
 * to add to a playlist.
 */
@Component
public class TracksDirectoryProcessor extends DirectoryProcessor<List<Song>> {

    private static final Logger logger = LoggerFactory.getLogger(TracksDirectoryProcessor.class);

    private final SongDataProcessor songProcessor;

    /**
     * Create a Tracks Directory processor using a Song Metadata Processor instance.
     * @param songProcessor A song metadata processor instance.
     */
    public TracksDirectoryProcessor(SongDataProcessor songProcessor) {
        this.songProcessor = songProcessor;
    }

    /**
     * Process a tracks directory given a track directory path.
     * @param tracksDirectory A tracks directory path.
     * @return A list of songs.
     */
    @Override
    List<Song> processDirectory(Path tracksDirectory) {
        logger.info("Processing tracks directory: " + tracksDirectory);
        List<Song> songs;
        try (Stream<Path> walk = Files.walk(tracksDirectory, 1)) {
            songs = walk.map(path -> {
                if (path.equals(tracksDirectory) || path.getFileName().toString().equals(CSV_EXTENSION)) {
                    return null;
                }

                if (path.toFile().isFile() && path.toFile().getName().endsWith(CSV_EXTENSION)) {
                    return songProcessor.process(path);
                }
                return null;
            }).filter(Objects::nonNull).collect(Collectors.toList());
        } catch (IOException e) {
            throw new DataProcessingException("Error Processing tracks Directory: " + tracksDirectory, e);
        }

        boolean equals = songs.get(1).equals(songs.get(2));

        logger.info("Size of playlist: " + songs.size());
        Set<Song> songSet = new HashSet<>(songs);
        songs = new ArrayList<>(songSet);

        logger.info("Processed: " + songs.size() + " songs in tracks directory.");
        logger.info("Processed tracks directory: " + tracksDirectory);
        return songs;
    }

}
