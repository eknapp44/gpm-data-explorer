package com.knapptown.gpmdataexplorer.components;

import com.knapptown.gpmdataexplorer.models.Playlist;
import com.knapptown.gpmdataexplorer.models.Song;
import com.knapptown.gpmdataexplorer.services.PlaylistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.knapptown.gpmdataexplorer.ApplicationConstants.METADATA_FILE;
import static com.knapptown.gpmdataexplorer.ApplicationConstants.TRACK_DIRECTORY;

/**
 * A Data Processor for Playlist Directories.
 */
@Component
public class PlaylistDirectoryProcessor extends DirectoryProcessor<Playlist> {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistDirectoryProcessor.class);

    private final TracksDirectoryProcessor tracksDirectoryProcessor;
    private final PlaylistProcessorData playlistMetadataProcessor;
    private final PlaylistService playlistService;

    /**
     * Create a Playlist Directory Processor using a Playlist Metadata processor,
     * a Tracks Directory processor, and a Playlist Service instance.
     * @param tracksDirectoryProcessor A Track Directory Processor instance.
     * @param playlistMetadataProcessor A Playlist Metadata Processor instance.
     * @param playlistService A playlist service instance.
     */
    public PlaylistDirectoryProcessor(TracksDirectoryProcessor tracksDirectoryProcessor,
                                      PlaylistProcessorData playlistMetadataProcessor,
                                      PlaylistService playlistService) {
        this.tracksDirectoryProcessor = tracksDirectoryProcessor;
        this.playlistMetadataProcessor = playlistMetadataProcessor;
        this.playlistService = playlistService;
    }

    /**
     * Process a Playlist Directory using a playlist directory path.
     * @param path The input path to the data to be processed.
     * @return A playlist instance.
     */
    @Override
    Playlist processDirectory(Path path) {
        logger.info("Processing Playlist Directory: " + path);

        Path metadataFile = Paths.get(path.toString(), METADATA_FILE);
        Playlist playlist =playlistMetadataProcessor.process(metadataFile);

        Path tracksDirectory = Paths.get(path.toString(), TRACK_DIRECTORY);
        List<Song> songs = tracksDirectoryProcessor.process(tracksDirectory);

        if (songs != null && !songs.isEmpty()) {
            logger.info("Adding " + songs.size() + " songs to playlist: " + playlist.getTitle());
            playlist = playlistService.addSongsToPlaylist(playlist, songs);
        }

        logger.info("Processed playlist directory: " + path);
        return playlist;
    }

}
