package com.knapptown.gpmdataexplorer.services;

import java.util.List;

import javax.transaction.Transactional;

import com.knapptown.gpmdataexplorer.entities.Playlist;
import com.knapptown.gpmdataexplorer.repositories.PlaylistRepository;

import org.springframework.stereotype.Service;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Transactional
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    @Transactional
    public Playlist getPlaylist(Long id) {
        return playlistRepository.getOne(id);
    }

    @Transactional
    public Playlist savePlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }
    
}
