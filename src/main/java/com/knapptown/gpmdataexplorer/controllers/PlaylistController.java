package com.knapptown.gpmdataexplorer.controllers;

import com.knapptown.gpmdataexplorer.exceptions.PlaylistNotFoundException;
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

    @GetMapping("/owner/{owner}/title/{title}")
    public Playlist getPlaylist(@PathVariable String owner, @PathVariable String title) {
        return playlistService.getPlaylist(owner, title);
    }

    @PostMapping
    public Playlist createPlaylist(@RequestBody Playlist playlist) {
        return playlistService.savePlaylist(playlist);
    }

    @PutMapping("/owner/{owner}/title/{title}")
    public Playlist updatePlaylist(@PathVariable String owner, @PathVariable String title, @RequestBody Playlist playlist) {
        Playlist original = playlistService.getPlaylist(owner, title);

        if (original == null) {
            throw new PlaylistNotFoundException(owner, title);
        }

        original.setTitle(playlist.getTitle());
        original.setShared(playlist.isShared());
        original.setDeleted(playlist.isDeleted());
        original.setDescription(playlist.getDescription());

        return playlistService.savePlaylist(original);
    }

}
