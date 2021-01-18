package com.knapptown.gpmdataexplorer.components;

import com.knapptown.gpmdataexplorer.exceptions.DataProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
@Order(1)
public class DataDirectoryProcessor implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${gpm.data.explorer.data.directory}")
    private String dataDirectory;

    private final PlaylistProcessor playlistProcessor;

    private static final Logger logger = LoggerFactory.getLogger(DataDirectoryProcessor.class);

    public DataDirectoryProcessor(PlaylistProcessor playlistProcessor) {
        this.playlistProcessor = playlistProcessor;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        processDataDirectory(dataDirectory);
    }

    private void processDataDirectory(String dataDirectory) {
        Path dataDirectoryPath = Paths.get(dataDirectory);

        // Check data directory validity.
        checkValidDataDirectory(dataDirectoryPath);

        logger.info("Processing data directory: " + dataDirectory);
        try (Stream<Path> walk = Files.walk(dataDirectoryPath, 1)) {
            walk.forEach(path -> {
                if (path.equals(dataDirectoryPath)) {
                    return;
                }

                playlistProcessor.processPlaylistDirectory(path);
            });
        } catch (IOException e) {
            throw new DataProcessingException("Error processing Data Directory: " + dataDirectory, e);
        }
    }

    private void checkValidDataDirectory(Path dataDirectoryPath) {
        if (!Files.exists(dataDirectoryPath)) {
            throw new IllegalArgumentException("Invalid data directory: " + dataDirectoryPath + ". Doesn't exist.");
        }
        if (!Files.isDirectory(dataDirectoryPath)) {
            throw new IllegalArgumentException("Invalid data directory: " + dataDirectoryPath + ". Is not a directory.");
        }
    }

}
