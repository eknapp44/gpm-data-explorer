package com.knapptown.gpmdataexplorer.components;

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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Order(1)
public class DataLoader implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${gpm.data.explorer.data.directory}")
    private String dataDirectory;

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        loadData();
    }

    public void loadData() {
        logger.info("Found data directory: " + dataDirectory);

        List<String> playlists = getDataDirectoryPlaylists();
        for (String playlist : playlists) {
            logger.info("Found playlist directory: " + playlist);
        }
    }

    private List<String> getDataDirectoryPlaylists() {
        try (Stream<Path> walk = Files.walk(Paths.get(dataDirectory), 1)) {
            return walk.filter(Files::isDirectory).map(Path::toString).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
