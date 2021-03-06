package com.knapptown.gpmdataexplorer.controllers;

import com.knapptown.gpmdataexplorer.exceptions.SongNotFoundException;
import com.knapptown.gpmdataexplorer.models.Song;
import com.knapptown.gpmdataexplorer.services.SongService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/artist/{artist}/album/{album}/title/{title}")
    public Song getSong(@PathVariable String artist, @PathVariable String album, @PathVariable String title) {
        return songService.getSong(title, artist, album);
    }

    @PostMapping
    public Song createSong(@RequestBody Song song) {
        return songService.saveSong(song);
    }

    @PutMapping("/artist/{artist}/album/{album}/title/{title}")
    public Song updateSong(@PathVariable String artist, @PathVariable String album, @PathVariable String title, @RequestBody Song song) {
        Song original = songService.getSong(title, artist, album);

        if (original == null) {
            throw new SongNotFoundException(title, artist, album);
        }

        original.setTitle(song.getTitle());
        original.setAlbum(song.getAlbum());
        original.setArtist(song.getArtist());
        original.setDurationMs(song.getDurationMs());
        original.setPlayCount(song.getPlayCount());
        original.setRating(song.getRating());
        original.setRemoved(song.isRemoved());
        return songService.saveSong(original);
    }

}
