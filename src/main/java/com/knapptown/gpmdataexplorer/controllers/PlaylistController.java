package com.knapptown.gpmdataexplorer.controllers;

import com.knapptown.gpmdataexplorer.entities.Playlist;
import com.knapptown.gpmdataexplorer.services.PlaylistService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public Playlist createPlaylist(Playlist playlist) {
        return playlistService.savePlaylist(playlist);
    }

    @PutMapping("/{id}")
    public Playlist updatePlaylist(Long id, Playlist playlist) {
        Playlist original = playlistService.getPlaylist(id);
        if (original != null) {
            original.setTitle(playlist.getTitle());
            original.setShared(playlist.isShared());
            original.setDeleted(playlist.isDeleted());
            original.setDescriptions(playlist.getDescriptions());

            return playlistService.savePlaylist(original);
        }
        return null;
    }

}
