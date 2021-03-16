package com.knapptown.gpmdataexplorer.controllers;

import com.knapptown.gpmdataexplorer.models.Playlist;
import com.knapptown.gpmdataexplorer.services.PlaylistService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public List<Playlist> getAllPlaylists() {
        return playlistService.getAllPlaylists();
    }

    @GetMapping("/{id}")
    public Playlist getPlaylist(@PathVariable Long id) {
        return playlistService.getPlaylist(id);
    }

    @PostMapping
    public Playlist createPlaylist(@RequestBody Playlist playlist) {
        return playlistService.createPlaylist(playlist);
    }

    @PutMapping("/{id}")
    public Playlist updatePlaylist(@PathVariable Long id, @RequestBody Playlist playlist) {
        Playlist original = playlistService.getPlaylist(id);

        // TODO revisit ability to edit title and owner attributes.
        original.setShared(playlist.isShared());
        original.setDeleted(playlist.isDeleted());
        original.setDescription(playlist.getDescription());

        return playlistService.updatePlaylist(original);
    }

}
