package com.knapptown.gpmdataexplorer.components;

import com.knapptown.gpmdataexplorer.exceptions.DataProcessingException;
import com.knapptown.gpmdataexplorer.models.Song;
import com.knapptown.gpmdataexplorer.services.SongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
public class SongProcessor {

    private final static String CSV_EXTENSION = ".csv";

    private static final Logger logger = LoggerFactory.getLogger(SongProcessor.class);

    private final DataCsvReader dataCsvReader;
    private final SongService songService;

    public SongProcessor(DataCsvReader dataCsvReader,
                         SongService songService) {
        this.dataCsvReader = dataCsvReader;
        this.songService = songService;
    }

    public void processTracksDirectory(Path tracksDirectory) {
        logger.info("Processing tracks directory: " + tracksDirectory);
        try (Stream<Path> walk = Files.walk(tracksDirectory, 1)) {
            walk.forEach(path -> {
                if (path.equals(tracksDirectory) || path.getFileName().toString().equals(CSV_EXTENSION)) {
                    return;
                }

                if (path.toFile().isFile() && path.toFile().getName().endsWith(CSV_EXTENSION)) {
                    try {
                        processSongCsvFile(path);
                    } catch (IOException e) {
                       throw new DataProcessingException("Error Processing Song CSV File: " + path, e);
                    }
                }
            });
        } catch (IOException e) {
           throw new DataProcessingException("Error Processing Track Directory: " + tracksDirectory, e);
        }
        logger.info("Processed tracks directory: " + tracksDirectory);
    }

    private void processSongCsvFile(Path path) throws IOException {
        logger.info("Processing metadata for song file: " + path);
        Song song = dataCsvReader.readSongFromCsv(path);

        if (song == null) {
            throw new IllegalArgumentException("Invalid song metadata file: " + path);
        }

        // Case when hitting a ".csv" or "(1).csv" file from GPM.
        if (song.getTitle().isBlank()) {
            logger.info("Found invalid song metadata file: " + path + ". Ignoring...");
            return;
        }

        songService.saveSong(song);
        logger.info("Processed metadata file for song: " + song.getTitle() + " by: " + song.getArtist());
    }

}
