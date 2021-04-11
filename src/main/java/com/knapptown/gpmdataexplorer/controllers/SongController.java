package com.knapptown.gpmdataexplorer.controllers;

import com.knapptown.gpmdataexplorer.models.Song;
import com.knapptown.gpmdataexplorer.services.SongService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController implements CrudController<Song>{

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @Override
    @GetMapping
    public @ResponseBody List<Song> getAll() {
        return songService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public @ResponseBody Song getById(@PathVariable Long id) {
        return songService.getById(id);
    }

    @Override
    @PostMapping
    public @ResponseBody Song create(@RequestBody Song song) {
        return songService.create(song);
    }

    @Override
    @PutMapping("/{id}")
    public @ResponseBody Song update(@PathVariable Long id, @RequestBody Song song) {
        return songService.update(id, song);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        songService.delete(id);
    }

}
