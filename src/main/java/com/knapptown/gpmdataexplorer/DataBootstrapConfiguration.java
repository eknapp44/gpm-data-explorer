package com.knapptown.gpmdataexplorer;

import java.util.Set;

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
        Song song = new Song();
        song.setAlbum("Enema of the State");
        song.setArtist("Blink 182");
        song.setTitle("All the Small Things");
        song.setDurationMs(40000);
        song.setPlayCount(400);
        song.setPlaylistIndex(1);
        song.setRating(5);
        song.setRemoved(false);
        return song;
    }

    private Playlist createPlaylist() {
        Playlist playlist = new Playlist();
        playlist.setOwner("Evan Knapp");
        playlist.setTitle("Punk Rock");
        playlist.setDeleted(false);
        playlist.setDescriptions("Punk Rock Playlist!");
        playlist.setShared(false);
        return playlist;
    }
    
}
