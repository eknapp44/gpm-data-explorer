package com.knapptown.gpmdataexplorer.controllers;

import com.knapptown.gpmdataexplorer.entities.SongId;
import com.knapptown.gpmdataexplorer.exceptions.SongNotFoundException;
import com.knapptown.gpmdataexplorer.models.Song;
import com.knapptown.gpmdataexplorer.services.SongService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    @GetMapping("/id")
    public Song getSong(@NotNull @RequestParam(name = "title") String title,
                        @NotNull @RequestParam(name = "artist") String artist,
                        @NotNull @RequestParam(name = "album") String album) {
        SongId id = SongId.builder()
                .title(title)
                .artist(artist)
                .album(album)
                .build();
        return songService.getSong(id);
    }

    @PostMapping
    public Song createSong(@RequestBody Song song) {
        return songService.saveSong(song);
    }

    @PutMapping
    public Song updateSong(@NotNull @RequestParam(name = "title") String title,
                           @NotNull @RequestParam(name = "artist") String artist,
                           @NotNull @RequestParam(name = "album") String album,
                           @RequestBody Song song) {
        SongId id = SongId.builder()
                .title(title)
                .artist(artist)
                .album(album)
                .build();
        Song original = songService.getSong(id);

        if (original == null) {
            throw new SongNotFoundException(id);
        }

        original.setTitle(song.getTitle());
        original.setAlbum(song.getAlbum());
        original.setArtist(song.getArtist());
        original.setDurationMs(song.getDurationMs());
        original.setPlayCount(song.getPlayCount());
        original.setRating(song.getRating());
        original.setRemoved(song.isRemoved());
        original.setPlaylistIndex(song.getPlaylistIndex());
        return songService.saveSong(original);
    }

}
