package com.knapptown.gpmdataexplorer.controllers;

import com.knapptown.gpmdataexplorer.models.Song;
import com.knapptown.gpmdataexplorer.services.SongService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public @ResponseBody List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    @GetMapping("/{id}")
    public @ResponseBody Song getSong(@PathVariable @Positive Long id) {
        return songService.getSong(id);
    }

    @PostMapping
    public @ResponseBody Song createSong(@RequestBody @Valid Song song) {
        return songService.createSong(song);
    }

    @PutMapping("/{id}")
    public @ResponseBody Song updateSong(@PathVariable @Positive Long id, @RequestBody @Valid Song song) {
        Song original = songService.getSong(id);

        // TODO revisit ability to update title, artist, and album.
        original.setDurationMs(song.getDurationMs());
        original.setPlayCount(song.getPlayCount());
        original.setRating(song.getRating());
        original.setRemoved(song.isRemoved());

        return songService.updateSong(original);
    }

}
