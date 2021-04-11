package com.knapptown.gpmdataexplorer.controllers;

import com.knapptown.gpmdataexplorer.models.Playlist;
import com.knapptown.gpmdataexplorer.services.PlaylistService;
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
@RequestMapping("api/playlists")
public class PlaylistController implements CrudController<Playlist> {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    @GetMapping
    public @ResponseBody List<Playlist> getAll() {
        return playlistService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public @ResponseBody Playlist getById(@PathVariable Long id) {
        return playlistService.getById(id);
    }

    @Override
    @PostMapping
    public @ResponseBody Playlist create(@RequestBody Playlist playlist) {
        return playlistService.create(playlist);
    }

    @Override
    @PutMapping("/{id}")
    public @ResponseBody Playlist update(@PathVariable Long id, @RequestBody Playlist playlist) {
        return playlistService.update(id, playlist);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        playlistService.delete(id);
    }

}
