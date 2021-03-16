package com.knapptown.gpmdataexplorer.controllers;

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

    @GetMapping("/{id}")
    public Song getSong(@PathVariable Long id) {
        return songService.getSong(id);
    }

    @PostMapping
    public Song createSong(@RequestBody Song song) {
        return songService.createSong(song);
    }

    @PutMapping("/{id}")
    public Song updateSong(@PathVariable Long id, @RequestBody Song song) {
        Song original = songService.getSong(id);

        // TODO revisit ability to update title, artist, and album.
        original.setDurationMs(song.getDurationMs());
        original.setPlayCount(song.getPlayCount());
        original.setRating(song.getRating());
        original.setRemoved(song.isRemoved());

        return songService.updateSong(original);
    }

}
