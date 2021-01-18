package com.knapptown.gpmdataexplorer;

import java.util.Set;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.knapptown.gpmdataexplorer.entities.Playlist;
import com.knapptown.gpmdataexplorer.entities.Song;
import com.knapptown.gpmdataexplorer.services.PlaylistService;
import com.knapptown.gpmdataexplorer.services.SongService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBootstrapConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DataBootstrapConfiguration.class);

    @Bean
    public CsvMapper csvMapper() {
        return (CsvMapper) new CsvMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    @Bean
    public CsvSchema csvSchema() {
        return CsvSchema.emptySchema().withHeader();
    }

    @Bean
    public CommandLineRunner bootstrapData(PlaylistService playlistService,
                                           SongService songService) {
        return (args) -> {
            // Create and save a new song.
            songService.saveSong(createSong());

            for(Song song : songService.getAllSongs()) {
                log.info("Created Song with Title: '{}' By: '{}'", song.getTitle(), song.getArtist());
            }

            // Create a save a new playlist.
            playlistService.savePlaylist(createPlaylist());

            for(Playlist playlist : playlistService.getAllPlaylists()) {
                log.info("Created Playlist with Title: '{}' Owner: '{}'", playlist.getTitle(), playlist.getOwner());
            }

            // Try to add the song to the playlist.
            Song song = songService.getAllSongs().get(0);
            Playlist playlist = playlistService.getAllPlaylists().get(0);

            Set<Song> songs = playlist.getSongs();
            songs.add(song);
            playlist.setSongs(songs);
            log.info("Adding Song: '{}' to Playlist: '{}'", song.getTitle(), playlist.getTitle());
            playlistService.savePlaylist(playlist);

            for (Song playlistSong : playlist.getSongs()) {
                log.info("Playlist: '{}' Song: '{}'", playlist.getTitle(), playlistSong.getTitle());
            }
        };
    }

    private Song createSong() {
        return Song.builder()
                .album("Enema of the State")
                .artist("Blink 182")
                .title("All the Small Things")
                .durationMs(40000)
                .playCount(400)
                .playlistIndex(1)
                .rating(5)
                .removed(false)
                .build();
    }

    private Playlist createPlaylist() {
        return Playlist.builder()
                .owner("Evan Knapp")
                .title("Punk Rock")
                .deleted(false)
                .descriptions("Punk Rock Playlist!")
                .shared(false)
                .build();
    }
    
}
